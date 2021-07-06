import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Servicios.Consultas;
import Utilidades.UConexion;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//ResourceBundle rb = ResourceBundle.getBundle("framework.properties");
		//System.out.println(rb.getString("ubicacionBD"));
		
		UConexion.conectarBD();		

		Persona per = new Persona((long) 1, "Franco", 4546555);
		
		Consultas.guardar(per);
	}

}