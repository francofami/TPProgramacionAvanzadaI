package Servicios;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import Anotaciones.Columna;
import Anotaciones.Id;
import Anotaciones.Tabla;
import Utilidades.UBeaan;

public class Consultas {

	/**
	 * Insert
	 * @param o
	 */
	public static void guardar(Object o) {		
		
				
		try {
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
			Statement st = c.createStatement();
			
			String tabla = o.getClass().getAnnotation(Tabla.class).nombre();
			
			String nombres = "";
			String values = "";
			
			for(Field f: UBeaan.obtenerAtributos(o)) {
				
				String nombre = f.getAnnotation(Columna.class).nombre();
				
				nombres += nombre + ",";
				values += "'" + UBeaan.ejecutarGet(o, nombre).toString() + "',";
																				
			}
			
			nombres = nombres.substring(0, nombres.length() - 1);
			values = values.substring(0, values.length() - 1);
			
			st.execute("insert into "+tabla+" ("+nombres+") values ("+values+")");
			
			c.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * Update
	 * @param o
	 */
	public static void modificar(Object o) {
		
		try {
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
			Statement st = c.createStatement();
			
			String tabla = o.getClass().getAnnotation(Tabla.class).nombre();
			
			String nombres = "";
			String modificacion = "";
			
			String id = UBeaan.ejecutarGet(o, "id").toString();
			
			for(Field f: UBeaan.obtenerAtributos(o)) {
				
				String nombre = f.getAnnotation(Columna.class).nombre();
				
				if (!nombre.equalsIgnoreCase("id")) {					
					nombres += nombre + " = ";
					nombres += "'" + UBeaan.ejecutarGet(o, nombre).toString() + "',";	
				}
													
			}
			
			nombres = nombres.substring(0, nombres.length() - 1);
			
			st.execute("update "+tabla+" set "+nombres+" where id = "+id);
			
			c.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Delete
	 * @param o
	 */
	public static void eliminar(Object o) {
		
	}
	
	/**
	 * 
	 * @param o
	 */
	public static void obtenerTodos(Object o) {
		
	}
	
	/**
	 * Select where id=id
	 * @param o
	 */
	public static void obtenerPorId(Object o) {
		
	}
	
}
