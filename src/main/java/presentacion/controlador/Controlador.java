package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import Excepciones.ExcepcionDuplicado;
import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaLocalidad;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import dto.DireccionDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;

public class Controlador implements ActionListener {
	private Vista vista;
	private Vista vistaLocalidad;
	private List<PersonaDTO> personasEnTabla;
	private List<LocalidadDTO> localidadesEnTabla;
	private VentanaLocalidad ventanaLocalidad;
	private VentanaPersona ventanaPersona;
	private Agenda agenda;

	public Controlador(Vista vista, Vista vistaLocalidad, Agenda agenda) {
		this.vista = vista;
		this.vistaLocalidad = vistaLocalidad;

		this.vista.getBtnAgregar().addActionListener(a -> ventanaAgregarPersona(a));
		this.vista.getBtnEditar().addActionListener(a -> ventanaEditarPersona(a));
		this.vista.getBtnBorrar().addActionListener(s -> borrarPersona(s));
		this.vista.getBtnReporte().addActionListener(r -> mostrarReporte(r));
		this.vista.getBtnLocalidad().addActionListener(r -> mostrarLocalidad(r));

		this.vistaLocalidad.getBtnAgregar().addActionListener(b -> ventanaAgregarLocalidad(b));
		this.vistaLocalidad.getBtnEditar().addActionListener(c -> ventanaEditarLocalidad(c));
		this.vistaLocalidad.getBtnBorrar().addActionListener(d -> borrarLocalidad(d));

		this.ventanaPersona = VentanaPersona.getInstance();
		this.ventanaLocalidad = VentanaLocalidad.getInstance();
		this.ventanaPersona.getBtnAgregarPersona().addActionListener(
				p -> guardarPersona(p, this.ventanaPersona.getBtnAgregarPersona().getLabel().equals("Editar")));
		this.ventanaLocalidad.getBtnAgregarLocalidad().addActionListener(
				q -> guardarLocalidad(q, this.ventanaLocalidad.getBtnAgregarLocalidad().getLabel().equals("Editar")));

		this.agenda = agenda;
	}

	private void ventanaAgregarPersona(ActionEvent a) {
		this.ventanaPersona.cerrar();
		this.ventanaPersona.mostrarVentana(agenda.obtenerLocalidades());
	}

	private void ventanaAgregarLocalidad(ActionEvent a) {
		this.ventanaLocalidad.cerrar();
		this.ventanaLocalidad.mostrarVentana();
	}

	private void ventanaEditarPersona(ActionEvent a) {

		this.ventanaPersona.cerrar();
		int[] filasSeleccionadas = this.vista.getTabla().getSelectedRows();
		if (filasSeleccionadas.length > 0) {
			PersonaDTO personDtoEdit = this.personasEnTabla.get(filasSeleccionadas[0]);
			this.ventanaPersona.setearAtributos(personDtoEdit, agenda.obtenerLocalidades());
			this.ventanaPersona.mostrarVentana();
		} else {
			this.vista.mostrarPopup();
		}
	}

	private void ventanaEditarLocalidad(ActionEvent a) {

		this.ventanaLocalidad.cerrar();
		int[] filasSeleccionadas = this.vistaLocalidad.getTabla().getSelectedRows();
		if (filasSeleccionadas.length > 0) {
			LocalidadDTO localidadDtoEdit = this.localidadesEnTabla.get(filasSeleccionadas[0]);
			this.ventanaLocalidad.setearAtributos(localidadDtoEdit);
			this.ventanaLocalidad.mostrarVentana();
		} else {
			this.vistaLocalidad.mostrarPopup();
		}

	}

	private void guardarPersona(ActionEvent p, boolean editarPersona) {

			if (this.ventanaPersona.inputsCorrectos()) {
			String nombre = this.ventanaPersona.getTxtNombre().getText();
			String apellido = this.ventanaPersona.getTxtApellido().getText();
			String tel = ventanaPersona.getTxtTelefono().getText();
			String email = this.ventanaPersona.getTxtEmail().getText();
			DireccionDTO direccion = null;

			if (!this.ventanaPersona.getTextCalle().getText().equals("")) {
				String calle = this.ventanaPersona.getTextCalle().getText();
				String altura = this.ventanaPersona.getTextAltura().getText();
				String piso = this.ventanaPersona.getTextPiso().getText();
				String dpto = this.ventanaPersona.getTextDpto().getText();
				direccion = new DireccionDTO(calle, Integer.parseInt(altura), piso, dpto);
			}

			Date cumple = this.ventanaPersona.getDateCumple();
			int localidad = this.ventanaPersona.getIdLocalidad();
			String contacto = this.ventanaPersona.getTxtTipoContacto().getText();
			LocalidadDTO localidadDto = new LocalidadDTO(localidad);

			PersonaDTO persona = new PersonaDTO(0, nombre, apellido, tel, email, cumple, direccion, localidadDto,
					contacto);

			try {
				if (editarPersona) {
					persona.setIdPersona(this.ventanaPersona.getIdPersona());
					this.agenda.editarPersona(persona);

				} else {
					this.agenda.agregarPersona(persona);
				}

				this.refrescarTabla();
				this.ventanaPersona.cerrar();

			} catch (ExcepcionDuplicado e) {
				// TODO Auto-generated catch block
				this.ventanaPersona.setearError(e.getMessage());
			}

		}

	}

	private void guardarLocalidad(ActionEvent p, boolean editarLocalidad) {

		if (this.ventanaLocalidad.inputsCorrectos()) {
			String localidad = this.ventanaLocalidad.getTxtLocalidad().getText();
			String provincia = this.ventanaLocalidad.getTxtProvincia().getText();
			String pais = this.ventanaLocalidad.getTxtPais().getText();

			LocalidadDTO localidadDto = new LocalidadDTO(0, localidad, provincia, pais);

			try {
				if (editarLocalidad) {
					localidadDto.setId(this.ventanaLocalidad.getId());
					this.agenda.editarLocalidad(localidadDto);

				} else {
					this.agenda.agregarLocalidad(localidadDto);
				}

				this.refrescarTablaLocalidad();
				this.ventanaLocalidad.cerrar();

			} catch (ExcepcionDuplicado e) { // TODO Auto-generated catch block
				this.ventanaLocalidad.setearError(e.getMessage());
			}

		}

	}

	private void mostrarReporte(ActionEvent r) {
		ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
		reporte.mostrar();
	}

	private void mostrarLocalidad(ActionEvent r) {
		this.refrescarTablaLocalidad();
		this.vistaLocalidad.show();
	}

	public void borrarPersona(ActionEvent s) {
		int[] filasSeleccionadas = this.vista.getTabla().getSelectedRows();
		if (filasSeleccionadas.length == 0) {
			this.vista.mostrarPopup();

		} else {
			for (int fila : filasSeleccionadas) {
				this.agenda.borrarPersona(this.personasEnTabla.get(fila));

			}
			this.refrescarTabla();
		}

	}

	public void borrarLocalidad(ActionEvent s) {
		int[] filasSeleccionadas = this.vistaLocalidad.getTabla().getSelectedRows();
		if (filasSeleccionadas.length == 0) {
			this.vista.mostrarPopup();

		} else {
			for (int fila : filasSeleccionadas) {
				this.agenda.borrarLocalidad(this.localidadesEnTabla.get(fila));

			}
			this.refrescarTablaLocalidad();
		}

	}

	public void inicializar() {
		this.refrescarTabla();
		this.vista.show();

	}

	private void refrescarTabla() {
		this.personasEnTabla = agenda.obtenerPersonas();
		this.vista.llenarTabla(this.personasEnTabla);
	}

	private void refrescarTablaLocalidad() {
		this.localidadesEnTabla = agenda.obtenerLocalidades();
		this.vistaLocalidad.llenarTablaLocalidad(this.localidadesEnTabla);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
