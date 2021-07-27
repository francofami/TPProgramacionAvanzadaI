import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import Servicios.Consultas;
import Utilidades.UConexion;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UConexion.conectarBD();		

		Persona per = new Persona((long) 1, "Franco", 4546555);
		Persona perModificada = new Persona((long) 1, "Franco A", 4546555);
		
		//Consultas.guardar(per);
		//Consultas.modificar(perModificada);
		//Consultas.eliminar(perModificada);
		Consultas.obtenerPorId(per.getClass(), per);
		//Consultas.obtenerTodos();
		
	}

}