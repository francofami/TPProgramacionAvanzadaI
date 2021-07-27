package Servicios;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Anotaciones.Columna;
import Anotaciones.Id;
import Anotaciones.Tabla;
import Utilidades.UBeaan;
import Utilidades.UConexion;


public class Consultas {

	/**
	 * Insert
	 * @param o
	 */
	public static void guardar(Object o) {		
		
				
		try {
			UConexion uc = UConexion.conectarBD();
			Connection c = uc.getConnection();
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
			UConexion uc = UConexion.conectarBD();
			Connection c = uc.getConnection();
			Statement st = c.createStatement();
			
			String tabla = o.getClass().getAnnotation(Tabla.class).nombre();
			
			String nombres = "";
			String modificacion = "";
			
			String id = UBeaan.ejecutarGet(o, "id").toString();
			
			for(Field f: UBeaan.obtenerAtributos(o)) {
				
				String nombre = f.getAnnotation(Columna.class).nombre();
				
				if (!nombre.equalsIgnoreCase(id)) {					
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
		
		try {
			UConexion uc = UConexion.conectarBD();
			Connection c = uc.getConnection();
			Statement st = c.createStatement();
			
			String tabla = o.getClass().getAnnotation(Tabla.class).nombre();
			
			String idABorrar = "";
						
			for(Field f: UBeaan.obtenerAtributos(o)) {
				
				String id = f.getAnnotation(Columna.class).nombre();
				
				if (id.equalsIgnoreCase("id")) {
					idABorrar = UBeaan.ejecutarGet(o, f.getName()).toString();
				}
													
			}
			
			st.execute("delete from "+tabla+" where id = "+idABorrar);
			
			c.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param o
	 */
	public static void obtenerTodos(Object o) {
		
		try {
			UConexion uc = UConexion.conectarBD();
			Connection c = uc.getConnection();
			Statement st = c.createStatement();
			
			String tabla = o.getClass().getAnnotation(Tabla.class).nombre();			
			
			st.execute("select * from "+tabla);
			
			c.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
	/**
	 * 
	 * @param c Clase del objeto a obtener
	 * @param id Id del objeto que queremos obtener
	 * @return el objeto encontrado
	 */
	public static Object obtenerPorId(Class<?> c, Object id) {
		
		Object retorno = null;
		
		for(Constructor<?> constructor: c.getConstructors()) {
			
			if(constructor.getParameterCount()==0) {
			
				try {
					retorno = constructor.newInstance();
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}		
		}
		
		String nombres = "";
		
		for(Field f: c.getDeclaredFields()) {
			nombres += "" + f.getName() + ",";	
		}
		
		nombres = nombres.substring(0, nombres.length() - 1);	
		
		String tabla = id.getClass().getAnnotation(Tabla.class).nombre();
		
		String idString = UBeaan.ejecutarGet(id, "Id").toString();
		
		
		try {
			UConexion uc = UConexion.conectarBD();
			Connection con = uc.getConnection();
			Statement st = con.createStatement();
			
			ResultSet rs;
			rs = st.executeQuery("select " + nombres + " from " + tabla + " where id=" + idString);
			
			while(rs.next()) {
				
				for(Field atributo: UBeaan.obtenerAtributos(retorno)) {
					
					String tipoAtributo = atributo.getType().getSimpleName();
					
					if (tipoAtributo.equals("Long")) {
						UBeaan.ejecutarSet(retorno, atributo.getAnnotation(Columna.class).nombre(), rs.getLong(atributo.getAnnotation(Columna.class).nombre()));
					} else if (tipoAtributo.equals("String")) {
						UBeaan.ejecutarSet(retorno, atributo.getAnnotation(Columna.class).nombre(), rs.getString(atributo.getAnnotation(Columna.class).nombre()));
					} else if (tipoAtributo.equals("Integer")) {
						UBeaan.ejecutarSet(retorno, atributo.getAnnotation(Columna.class).nombre(), rs.getInt(atributo.getAnnotation(Columna.class).nombre()));
					}
				}
			}
			
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		System.out.println("Retorno: " + retorno.toString());
		return retorno;
		
	}
	
}
