package Servicios;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import Anotaciones.Columna;
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
			
			String nombre = "";
			String values = "";
			
			for(Field f: UBeaan.obtenerAtributos(o)) {
				
				nombre += f.getAnnotation(Columna.class).nombre() + ",";
				values += "'" + UBeaan.ejecutarGet(o, f.getName()).toString() + "',";
																				
			}
			
			nombre = nombre.substring(0, nombre.length() - 1);
			values = values.substring(0, values.length() - 1);
			
			st.execute("insert into "+tabla+" ("+nombre+") values ("+values+")");
			
			c.close();
			System.out.println("Me conecté a la base de datos");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
	
		
		// Nombre
		
		
		//Valor
		for(Field f: UBeaan.obtenerAtributos(o)) {
			// f.getGenericType().getTypeName();
			
		}
		
		
		
	}
	
	/**
	 * Update
	 * @param o
	 */
	public static void modificar(Object o) {
		
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
