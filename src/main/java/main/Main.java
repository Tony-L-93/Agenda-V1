package main;

import modelo.Agenda;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.Vista;

public class Main 
{
	public static void main(String[] args) 
	{
		Vista vista = new Vista(false);
		Vista vistaLocalidad = new Vista(true);
		Agenda modelo = new Agenda(new DAOSQLFactory());
		Controlador controlador = new Controlador(vista,vistaLocalidad, modelo);
		controlador.inicializar();
	}
}
