package presentacion.vista;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import java.awt.Color;


public class VentanaLocalidad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//Textfields
	private JTextField txtPais;
	private JTextField txtProvincia;
	private JTextField txtLocalidad;
	private int id;
	private JLabel lblMensajes;
	private JButton btnAgregarLocalidad;
	private static VentanaLocalidad INSTANCE;

	/**
	 * Create the application.
	 */
	public static VentanaLocalidad getInstance() {
		
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaLocalidad(); 	
			return new VentanaLocalidad();
		}
		else
			return INSTANCE;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private VentanaLocalidad() {
		super();
		setTitle("Agenda");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 420);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(48, 35, 700, 335);

		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPais = new JLabel("Pais:");
		lblPais.setBounds(52, 22, 90, 14);
		panel.add(lblPais);
		
		JLabel lblProvincia = new JLabel("Provincia:");
		lblProvincia.setBounds(52, 72, 90, 14);
		panel.add(lblProvincia);
		
		JLabel lblLocalidad = new JLabel("Localidad:");
		lblLocalidad.setBounds(52, 122, 90, 14);
		panel.add(lblLocalidad);
		
		txtPais = new JTextField();
		txtPais.setBounds(125, 22, 471, 20);

		panel.add(txtPais);
		txtPais.setColumns(10);
		
		txtProvincia = new JTextField();
		txtProvincia.setBounds(125, 72, 471, 20);

		panel.add(txtProvincia);
		txtProvincia.setColumns(10);
		
		txtLocalidad = new JTextField();
		txtLocalidad.setBounds(125, 122, 471, 20);

		panel.add(txtLocalidad);
		txtLocalidad.setColumns(10);
		
		btnAgregarLocalidad= new JButton("Agregar");

		btnAgregarLocalidad.setBounds(308, 283, 89, 23);
		panel.add(btnAgregarLocalidad);
		
		lblMensajes = new JLabel();
		lblMensajes.setBounds(125, 201, 471, 16);
		panel.add(lblMensajes);
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setBounds(640, 22, 31, 16);
		panel.add(label);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(640, 72, 31, 16);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setBounds(640, 122, 31, 16);
		panel.add(label_2);
		
		JLabel lblCamposObligatorio = new JLabel("(*) Campos obligatorio");
		lblCamposObligatorio.setBounds(52, 240, 142, 16);

		panel.add(lblCamposObligatorio);
		
		this.setVisible(false);
	}
	public void mostrarVentana()
	{
		this.setVisible(true);
	}

	public JTextField getTxtPais() {
		return txtPais;
	}

	public void setTxtPais(JTextField txtPais) {
		this.txtPais = txtPais;
	}

	public JTextField getTxtProvincia() {
		return txtProvincia;
	}

	public void setTxtProvincia(JTextField txtProvincia) {
		this.txtProvincia = txtProvincia;
	}

	public JTextField getTxtLocalidad() {
		return txtLocalidad;
	}

	public void setTxtLocalidad(JTextField txtLocalidad) {
		this.txtLocalidad = txtLocalidad;
	}

	public JButton getBtnAgregarLocalidad() {
		return btnAgregarLocalidad;
	}

	public void setBtnAgregarLocalidad(JButton btnAgregarLocalidad) {
		this.btnAgregarLocalidad = btnAgregarLocalidad;
	}
	
	public void cerrar()
	{
		this.txtPais.setText(null);
		this.txtProvincia.setText(null);
		this.txtLocalidad.setText(null);
		btnAgregarLocalidad.setLabel("Agregar");
		this.lblMensajes.setText("");
		this.dispose();
	}
	
	public void setearAtributos(LocalidadDTO localidadDto) {
		this.txtPais.setText(localidadDto.getPais());
		this.txtProvincia.setText(localidadDto.getProvincia());
		this.txtLocalidad.setText(localidadDto.getLocalidad());
		this.id = localidadDto.getId();
		this.btnAgregarLocalidad.setLabel("Editar");
	}	
	
	public int getId() {
		return this.id;
	}
	
	public boolean inputsCorrectos() {
		boolean resultado = true;
		
		if(this.txtProvincia.getText().length() > 45 || this.txtLocalidad.getText().length() > 45) {
			this.lblMensajes.setText("La localidad y la provincia no puede tener mas de 45 caracteres");
			resultado = false;
		}
		
		if(this.txtPais.getText().length() > 30 ) {
			this.lblMensajes.setText("El pais no puede tener mas de 30 caracteres");
			resultado = false;
		}
		if(this.txtPais.getText().equals("") || this.txtProvincia.getText().equals("") || this.txtLocalidad.getText().equals("")) {
			this.lblMensajes.setText("La localidad,provincia y pais son obligatorios");
			resultado = false;
		}
		
		return resultado;
	}
	
	public void setearError(String mensaje) {
		this.lblMensajes.setText(mensaje);

	}
}
