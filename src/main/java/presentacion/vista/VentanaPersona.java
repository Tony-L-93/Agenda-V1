package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.toedter.calendar.JDateChooser;

import dto.TipoContacto;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;



public class VentanaPersona extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//Textfields
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtTipoContacto;
	private Date fCumple;
	private JDateChooser dateCumple;
	private List<LocalidadDTO> localidadesDTO;
	private Choice listContacto;
	private static VentanaPersona INSTANCE;
	private int idPersona;
	private int idDireccion;
	private int idLocalidad;
	private JTextField textPiso;
	private JTextField textDpto;
	private JTextField textAltura;
	private JTextField textCalle;
	private JLabel lblMensajes;
	private JComboBox comboBoxPais;
	private JComboBox comboBoxProvincia;
	//Add button
	private JButton btnAgregarPersona;
	private JComboBox comboBoxLocalidad;
	private JPanel panel;
	private boolean listenerActivado;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel lblCamposObligatorios;
	
	public static VentanaPersona getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaPersona(); 	
			return new VentanaPersona();
		}
		else
			return INSTANCE;
	}

	private VentanaPersona() 
	{
		super();
		setTitle("Agenda");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 420);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 784, 381);

		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(33, 25, 90, 14);
		panel.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(33, 65, 90, 14);
		panel.add(lblApellido);
		
		JLabel lblTelfono = new JLabel("Telefono:");
		lblTelfono.setBounds(33, 115, 90, 14);
		panel.add(lblTelfono);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(33, 165, 90, 14);
		panel.add(lblEmail);
		
		JLabel lblCumple = new JLabel("Fecha de Nacmiento:");
		lblCumple.setBounds(33, 215, 104, 14);
		panel.add(lblCumple);
		
		JLabel lblPais = new JLabel("Pais:");
		lblPais.setBounds(454, 190, 90, 14);
		panel.add(lblPais);
		
		JLabel lblProvincia = new JLabel("Provincia:");
		lblProvincia.setBounds(454, 230, 90, 14);

		panel.add(lblProvincia);
		
		
		JLabel lblLocalidad = new JLabel("Localidad:");
		lblLocalidad.setBounds(454, 270, 90, 14);
		panel.add(lblLocalidad);
		
		JLabel lblTipoContacto = new JLabel("Tipo de contacto:");
		lblTipoContacto.setBounds(33, 265, 104, 14);

		panel.add(lblTipoContacto);
			
		txtNombre = new JTextField();
		txtNombre.setBounds(140, 22, 210, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(140, 62, 210, 20);
		panel.add(txtApellido);
		txtApellido.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(140, 112, 210, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(140, 162, 210, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		dateCumple = new JDateChooser();
		dateCumple.setDateFormatString("yyyy-MM-dd");
		dateCumple.setBounds(140, 212, 210, 20);
		panel.add(dateCumple);
		
		JPanel panelDomicilio = new JPanel();
		panelDomicilio.setBounds(431, 0, 353, 179);
		panel.add(panelDomicilio);
		panelDomicilio.setLayout(null);
		
		JLabel lblCalle = new JLabel("Calle:");
		lblCalle.setBounds(25, 25, 90, 14);
		panelDomicilio.add(lblCalle);
		
		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setBounds(25, 65, 90, 14);
		panelDomicilio.add(lblNumero);
		
		JLabel lblDpto = new JLabel("Departamento:");
		lblDpto.setBounds(25, 105, 90, 14);
		panelDomicilio.add(lblDpto);
		
		JLabel lblPiso = new JLabel("Piso:");
		lblPiso.setBounds(25, 145, 90, 14);
		panelDomicilio.add(lblPiso);
		
		textPiso = new JTextField();
		textPiso.setColumns(10);
		textPiso.setBounds(134, 142, 210, 20);
		panelDomicilio.add(textPiso);
		
		textDpto = new JTextField();
		textDpto.setColumns(10);
		textDpto.setBounds(134, 102, 210, 20);
		panelDomicilio.add(textDpto);
		
		textAltura = new JTextField();
		textAltura.setColumns(10);
		textAltura.setBounds(134, 62, 210, 20);
		panelDomicilio.add(textAltura);
		
		textCalle = new JTextField();
		textCalle.setColumns(10);
		textCalle.setBounds(134, 22, 210, 20);
		panelDomicilio.add(textCalle);
		
		
		
		listContacto= new Choice();
		addEnumToTipoContacto(listContacto);
		listContacto.setBounds(140, 262, 210, 20);

		panel.add(listContacto);
		
				
		btnAgregarPersona = new JButton("Agregar");

		btnAgregarPersona.setBounds(360, 342, 89, 23);

		panel.add(btnAgregarPersona);
		
		lblMensajes = new JLabel();
		lblMensajes.setBounds(33, 302, 741, 16);
		panel.add(lblMensajes);
		
		this.comboBoxPais = new JComboBox();
		this.comboBoxPais.setBounds(564, 187, 210, 20);
		panel.add(this.comboBoxPais);
		
		this.comboBoxProvincia = new JComboBox();
		this.comboBoxProvincia.setBounds(564, 227, 210, 20);
		panel.add(this.comboBoxProvincia);
		
		this.comboBoxLocalidad = new JComboBox();
		this.comboBoxLocalidad.setBounds(564, 267, 210, 20);
		panel.add(this.comboBoxLocalidad);
		
		label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(360, 24, 61, 16);
		panel.add(label);
		
		label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(360, 115, 61, 16);
		panel.add(label_1);
		
		label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(360, 265, 61, 16);
		panel.add(label_2);
		
		lblCamposObligatorios = new JLabel("Campos obligatorios.");
		lblCamposObligatorios.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCamposObligatorios.setBounds(60, 329, 110, 16);
		panel.add(lblCamposObligatorios);
		
		JLabel lblCamposObligatorios_1 = new JLabel("(*)");
		lblCamposObligatorios_1.setForeground(Color.RED);
		lblCamposObligatorios_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCamposObligatorios_1.setBounds(33, 329, 19, 16);
		panel.add(lblCamposObligatorios_1);

		comboBoxPais.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent arg0) {
				   if(comboBoxPais.getSelectedItem()!=null && listenerActivado) {
					   cargarCambio(comboBoxPais.getSelectedItem().toString());
					   System.out.println("cambio");
				   }
				   
			   }
			});
		comboBoxProvincia.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent arg0) {
				   if(comboBoxPais.getSelectedItem()!=null && comboBoxProvincia.getSelectedItem()!=null && listenerActivado) {
					   cargarCambio(comboBoxPais.getSelectedItem().toString(),comboBoxProvincia.getSelectedItem().toString());

				   }
				   System.out.println("cambio2");
			   }
		});
		comboBoxLocalidad.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent arg0) {
				   if(comboBoxPais.getSelectedItem()!=null && comboBoxProvincia.getSelectedItem()!=null && comboBoxLocalidad.getSelectedItem()!=null && listenerActivado) {
					   asignarIdLocalidad();

				   
				   }
				   System.out.println("cambio");
			   }
			});
		
		this.setVisible(false);
	}
	private void inicializarCombox() {
		
		this.comboBoxPais = new JComboBox();
		this.comboBoxPais.setBounds(110, 304, 189, 22);
		this.panel.add(this.comboBoxPais);
		
		this.comboBoxProvincia = new JComboBox();
		this.comboBoxProvincia.setBounds(110, 352, 189, 22);
		this.panel.add(this.comboBoxProvincia);
		
		this.comboBoxLocalidad = new JComboBox();
		this.comboBoxLocalidad.setBounds(110, 385, 189, 22);
		this.panel.add(this.comboBoxLocalidad);
	}

	
	@SuppressWarnings("unchecked")
	public void mostrarVentana(List<LocalidadDTO> localidades)
	{
		//limpiarCombos();
		//inicializarCombox();
		cargarCombox(localidades);
		this.setVisible(true);
	}
	@SuppressWarnings("unchecked")
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	private void cargarCombox(List<LocalidadDTO> localidades) {
		this.listenerActivado = false;
		this.localidadesDTO = null;
		
		this.localidadesDTO = localidades;
		this.comboBoxProvincia.removeAllItems();
		this.comboBoxPais.removeAllItems();
		this.comboBoxLocalidad.removeAllItems();
		
		localidades.stream().
		  map(p-> p.getPais()).
		  distinct().
		  forEach(r->this.comboBoxPais.addItem(r.toString()));
		
		localidades.stream().
		  map(p-> p.getProvincia()).
		  distinct().
		  forEach(r->this.comboBoxProvincia.addItem(r.toString()));
		
		localidades.stream().
				  map(p-> p.getLocalidad()).
				  distinct().
				  forEach(r->this.comboBoxLocalidad.addItem(r.toString()));
		listenerActivado = true;	
	}
	
	private void asignarIdLocalidad() {
		
		System.out.println(comboBoxPais.getSelectedItem());
		System.out.println(comboBoxLocalidad.getSelectedItem());
		System.out.println(comboBoxProvincia.getSelectedItem());

		Optional<LocalidadDTO> localidad = this.localidadesDTO.stream().
							filter(p -> p.getPais().equals(this.comboBoxPais.getSelectedItem().toString())).
							filter(r -> r.getLocalidad().equals(this.comboBoxLocalidad.getSelectedItem().toString())).
							filter(s -> s.getProvincia().equals(this.comboBoxProvincia.getSelectedItem().toString())).findFirst();								   
		if(localidad.isPresent()) {
			this.idLocalidad = localidad.get().getId();

		}
		

	}
	
	public void cargarCambio(String pais) {
		
		this.comboBoxProvincia.removeAllItems();
		this.comboBoxLocalidad.removeAllItems();
		
		List<LocalidadDTO> listaTemporal = this.localidadesDTO.stream().
															   filter(p -> p.getPais().equals(pais)).
															   collect(Collectors.toList());	
		listaTemporal.stream().
					  map(p-> p.getProvincia()).
					  distinct().
					  forEach(r->this.comboBoxProvincia.addItem(r.toString()));
		
		listaTemporal.stream().
					  map(p-> p.getLocalidad()).
					  distinct().
					  forEach(r->this.comboBoxLocalidad.addItem(r.toString()));
	}
	
	public void cargarCambio(String pais, String provincia) {
		
		this.comboBoxLocalidad.removeAllItems();
		
		List<LocalidadDTO> listaTemporal = this.localidadesDTO.stream().
															   filter(p -> p.getPais().equals(pais) && p.getProvincia().equals(provincia)).
															   collect(Collectors.toList());	
		listaTemporal.stream().
					  map(p-> p.getLocalidad()).
					  distinct().
					  forEach(r->this.comboBoxLocalidad.addItem(r.toString()));
	
	}
	
	
	
	//Agrega lo que hay en Localidad enum
		public void addEnumToTipoContacto(Choice ch) {
			for(TipoContacto tc: TipoContacto.values()) {
				ch.add(tc.toString());
			}
		}
		
	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}
	
	public JTextField getTxtApellido() 
	{
		return txtApellido;
	}


	public JTextField getTxtTelefono() 
	{
		return txtTelefono;
	}
	
	public JTextField getTxtEmail() 
	{
		return txtEmail;
	}
	

	public JTextField getTextPiso() {
		return textPiso;
	}

	public JTextField getTextDpto() {
		return textDpto;
	}

	public JTextField getTextAltura() {
		return textAltura;
	}

	public JTextField getTextCalle() {
		return textCalle;
	}
	
	public int getIdLocalidad() {
		return this.idLocalidad;
	} 

	
	public JTextField getTxtTipoContacto() {
		JTextField contacto= new JTextField(getStringChoice(listContacto));
		//System.out.print("Dato:"+getTipoContacto(list));
		this.txtTipoContacto=contacto;	
		return txtTipoContacto;
	
	}
	
	public String getStringChoice(Choice cH) {
		String stringChoice=cH.getSelectedItem();
		return stringChoice;
	}
	
	//Toma la fecha de JCalendar y lo pasa a String
	public String getFecha(JDateChooser jD) {
		SimpleDateFormat Formato= new SimpleDateFormat("yyyy-MM-dd");
		if(jD.getDate()!=null) {
			return Formato.format(jD.getDate());
		}
		else {
			return null;
		}
	}
	
	//Transforma la fecha en string a date java
	public java.util.Date StringToDate(String Fecha){
		SimpleDateFormat FormatoTexto= new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date cumple=null;
		try {
			if(Fecha!= null) {
				cumple=FormatoTexto.parse(Fecha);
			}
			return cumple;
		} catch(ParseException ex){
			return null;
		}
	}
	
	//Obtiene el date sql. 
	public Date getDateCumple()
	{
		java.util.Date fecha=StringToDate(getFecha(dateCumple));

		if (fecha == null) {
			fCumple=null;
		} else {
		    fCumple= new java.sql.Date(fecha.getTime());
		}
		
		return fCumple;
	}

	public JButton getBtnAgregarPersona() 
	{
		return btnAgregarPersona;
	}

	public void limpiarCombos() {
		this.comboBoxLocalidad = null;
		this.comboBoxPais = null;
		this.comboBoxProvincia = null;
	}
	public void cerrar()
	{
		this.txtNombre.setText(null);
		this.txtApellido.setText(null);
		this.txtTelefono.setText(null);
		this.txtEmail.setText(null);
		btnAgregarPersona.setLabel("Agregar");
		this.dateCumple.setCalendar(null);
		this.textAltura.setText(null);
		this.textCalle.setText(null);
		this.textDpto.setText(null);
		this.textPiso.setText(null);
		this.lblMensajes.setText("");
		this.dispose();
	}
	
	public void setearAtributos(PersonaDTO personaDto, List<LocalidadDTO> localidades) {
		this.txtNombre.setText(personaDto.getNombre());
		this.txtApellido.setText(personaDto.getApellido());
		this.txtEmail.setText(personaDto.getEmail());
		this.txtTelefono.setText(personaDto.getTelefono());
		this.idPersona = personaDto.getIdPersona();
		cargarCombox(localidades);
		this.comboBoxPais.setSelectedItem(personaDto.getLocalidad().getPais());
		this.comboBoxProvincia.setSelectedItem(personaDto.getLocalidad().getProvincia());
		this.comboBoxLocalidad.setSelectedItem(personaDto.getLocalidad().getLocalidad());
		
		this.dateCumple.setDate(personaDto.getFechaCumpleanios());
		if(personaDto.getDireccion() != null) {
			if(personaDto.getDireccion().getPiso() != null) {
				this.textPiso.setText(personaDto.getDireccion().getPiso());
			}
			if(personaDto.getDireccion().getDpto() != null) {
				this.textDpto.setText(personaDto.getDireccion().getDpto());
			}
			this.textAltura.setText(Integer.toString(personaDto.getDireccion().getAltura()));
			this.textCalle.setText(personaDto.getDireccion().getCalle());
		}
		this.idDireccion = personaDto.getDireccion().getIdDireccion();
		btnAgregarPersona.setLabel("Editar");
		
	}

	public int getIdPersona() {
		return this.idPersona;
	}
	public int getIdDireccion() {
		return this.idDireccion;
	}
	
	public boolean inputsCorrectos() {
		boolean resultado = true;
		
		if(this.txtNombre.getText().length() > 45 || this.txtApellido.getText().length() > 45) {
			this.lblMensajes.setText("El nombre y apellido no puede tener mas de 45 caracteres");
			resultado = false;
		}
		
		if(!this.txtEmail.getText().equals("") && !this.txtEmail.getText().matches("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			this.lblMensajes.setText("El email tiene un formato incorrecto");
			resultado = false;
		}
		if(this.txtTelefono.getText().length() > 20) {
			this.lblMensajes.setText("El telefono no puede tener mas de 20 caracteres");
			resultado = false;
		}
		if(this.txtNombre.getText().equals("") || this.getTxtTelefono().getText().equals("")) {
			this.lblMensajes.setText("El nombre y el telefono son obligatorios");
			resultado = false;
		}
		if (!this.getTxtTelefono().getText().matches("[0-9]*")) {
			this.lblMensajes.setText("El telefono solo puede tener numeros");
			resultado = false;
		}
		if(dateCumple.getDate()!=null && getFecha(dateCumple) == null) {
			this.lblMensajes.setText("La fecha esta mal ingresada");
			resultado = false;
		}
		if(resultado) {
			resultado = inputsCorrectosDireccion();
		}
		
		return resultado;
	}
	
	public boolean inputsCorrectosDireccion() {
		boolean resultado = true;
		
		if(this.textCalle.getText().length() > 100) {
			this.lblMensajes.setText("La calle no puede tener mas de 100 caracteres");
			resultado = false;
		}
		if(!this.textCalle.getText().equals("") && this.textAltura.getText().equals("")) {
			this.lblMensajes.setText("Si se ingresa la calle la altura es obligatoria");
			resultado = false;
		}
		if(!this.textAltura.getText().matches("[0-9]*") || this.textAltura.getText().length() > 8) {
			this.lblMensajes.setText("El campo altura no puede tener mas de 8 caracteres y dichos caracteres solo puede ser numeros");
		}
		if(this.textDpto.getText().length() > 10 || this.textPiso.getText().length() > 10) {
			this.lblMensajes.setText("Los campos departamento y piso no pueden tener mas de 10 caracteres");
			resultado = false;
		}
		return resultado;
	}
	
	public void setearError(String mensaje) {
		this.lblMensajes.setText(mensaje);
	}

}

