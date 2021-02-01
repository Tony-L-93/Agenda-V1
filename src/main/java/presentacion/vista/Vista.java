package presentacion.vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import modelo.Agenda;

import javax.swing.JButton;
import javax.swing.JDialog;

import persistencia.conexion.Conexion;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

public class Vista  extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel panel;
	private JScrollPane spPersonas;
	private JTable tabla;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JButton btnReporte;
	private JButton btnLocalidad;
	private DefaultTableModel modelo;
	private  String[] nombreColumnas = {"Nombre", "Apellido", "Telefono", "Email", "Cumpleaños", "Dirección", "Pais","Provincia","Localidad", "Tipo de Contacto"};
	private  String[] nombreColumnasLocalidad = {"Pais", "Provincia", "Localidad"};
	private boolean esLocalidad;

	public Vista(boolean esLocalidad) 

	{
		super();
		this.esLocalidad = esLocalidad;
		initialize();
	}

	private void initialize() 
	{
		frame = new JFrame();
		spPersonas = new JScrollPane();
		panel = new JPanel();
				
		if(this.esLocalidad) {
			frame.setTitle("Agenda");
			frame.setBounds(100, 100, 800, 420);
			panel.setBounds(100, -5, 584, 411);
			frame.setLocationRelativeTo(null);
			spPersonas.setBounds(10, 11, 564, 339);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			frame.getContentPane().add(panel);
			panel.setLayout(null);
			panel.add(spPersonas);

		}else {

			frame.setTitle("Agenda");
			frame.setBounds(100, 100, 1100, 420);
			frame.setLocationRelativeTo(null);
			panel.setBounds(12, 2, 1600, 822);
			spPersonas.setBounds(0, 0, 1060, 339);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			frame.getContentPane().add(panel);
			panel.setLayout(null);
			panel.add(spPersonas);
		}
		
		
		if(this.esLocalidad) {
			modelo = new DefaultTableModel(null,nombreColumnasLocalidad);
		}
		else {
			modelo = new DefaultTableModel(null,nombreColumnas);
		}
		
		tabla = new JTable(modelo){
	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false;
	        }};
	        
		tabla.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tabla.getColumnModel().getColumn(0).setPreferredWidth(103);
		tabla.getColumnModel().getColumn(0).setResizable(false);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabla.getColumnModel().getColumn(1).setResizable(false);
		spPersonas.setViewportView(tabla);
	
		
		btnAgregar = new JButton("Agregar");
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBorrar = new JButton("Borrar");
		
		if(this.esLocalidad) {
			btnAgregar.setBounds(138, 350, 89, 23);
			btnEditar.setBounds(247, 350, 89, 23);
			btnBorrar.setBounds(346, 350, 89, 23);
			panel.add(btnAgregar);		
			panel.add(btnEditar);
			panel.add(btnBorrar);
		}
		else {
			btnAgregar.setBounds(275, 350, 89, 23);
			btnEditar.setBounds(375, 350, 89, 23);
			btnBorrar.setBounds(475, 350, 89, 23);
			panel.add(btnAgregar);		
			panel.add(btnEditar);
			panel.add(btnBorrar);
			}
		
		if(!this.esLocalidad) {
			btnReporte = new JButton("Reporte");
			btnReporte.setBounds(575, 350, 89, 23);
			panel.add(btnReporte);
			
			btnLocalidad = new JButton("Localidad");
			btnLocalidad.setBounds(675, 350, 89, 23);
			panel.add(btnLocalidad);
		}
	}

	public void show()
	{
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				if(this.esLocalidad) {
					this.frame.addWindowListener(new WindowAdapter() 
					{
						@Override
					    public void windowClosing(WindowEvent e) {
							frame.setVisible(false);
					    }
						
					});	

				}
				else {
					this.frame.addWindowListener(new WindowAdapter() 
					{
						@Override
					    public void windowClosing(WindowEvent e) {
								 int confirm = JOptionPane.showOptionDialog(
							             null, "¿Estás seguro que quieres salir de Agenda?", 
							             "Confirmación", JOptionPane.YES_NO_OPTION,
							             JOptionPane.QUESTION_MESSAGE, null, null, null);
							        if (confirm == 0) {
							        	Conexion.getConexion().cerrarConexion();
							           System.exit(0);
							        }
					    }
					});	
				}
				
				
				this.frame.setVisible(true);
	}
	
	public JButton getBtnAgregar() 
	{
		return btnAgregar;
	}
	
	public JButton getBtnEditar() 
	{
		return btnEditar;
	}

	public JButton getBtnBorrar() 
	{
		return btnBorrar;
	}
	
	public JButton getBtnReporte() 
	{
		return btnReporte;
	}
	public JButton getBtnLocalidad() 
	{
		return btnLocalidad;
	}
	
	public DefaultTableModel getModelo() 
	{
		return modelo;
	}
	
	public JTable getTabla()
	{
		return tabla;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}
	public String[] getNombreColumnasLocalida() 
	{
		return nombreColumnasLocalidad;
	}

	public void llenarTabla(List<PersonaDTO> personasEnTabla) {
		this.getModelo().setRowCount(0); //Para vaciar la tabla
		this.getModelo().setColumnCount(0);
		this.getModelo().setColumnIdentifiers(this.getNombreColumnas());

		for (PersonaDTO p : personasEnTabla)
		{
			String nombre = p.getNombre();
			String apellido= p.getApellido();
			String tel = p.getTelefono();
			String email=p.getEmail();
			Date cumple=p.getFechaCumpleanios();
			String direccion = "";
			String contacto=p.getTipoContacto();
			int id = p.getIdPersona();
			
			if(p.getDireccion().getIdDireccion() > 0) {	
				 direccion = p.getDireccion().getCalle()+" "+p.getDireccion().getAltura();
				 if(p.getDireccion().getPiso() != null) {
					 direccion = direccion +" "+ p.getDireccion().getPiso();
				 }
				 if(p.getDireccion().getDpto() != null) {
					 direccion = direccion +" "+ p.getDireccion().getDpto();
				 }
			}

			String localidad = p.getLocalidad().getLocalidad();
			String provincia = p.getLocalidad().getProvincia();
			String pais = p.getLocalidad().getPais();
			int idLocalidad = p.getLocalidad().getId();
			Object[] fila = {nombre, apellido, tel, email, cumple,direccion,pais,provincia,localidad,contacto,id,idLocalidad};//, cumple
			this.getModelo().addRow(fila);
		}
	}
	public void llenarTablaLocalidad(List<LocalidadDTO> localidadTabla) {
		this.getModelo().setRowCount(0); //Para vaciar la tabla
		this.getModelo().setColumnCount(0);
		this.getModelo().setColumnIdentifiers(this.getNombreColumnasLocalida());

		for (LocalidadDTO p : localidadTabla)
		{
			String localidad = p.getLocalidad();
			String provincia= p.getProvincia();
			String pais = p.getPais();
			int id = p.getId();
			Object[] fila = {pais, provincia, localidad,id};//, cumple
			this.getModelo().addRow(fila);
		}
	}
	
	public void mostrarPopup() {
		JOptionPane optionPane = new JOptionPane("Debe seleeccionar un contacto de la lista",JOptionPane.WARNING_MESSAGE);
		JDialog dialog = optionPane.createDialog("Warning!");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}
}
