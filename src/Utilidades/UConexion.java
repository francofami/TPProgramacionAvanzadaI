package Utilidades;

import com.mysql.jdbc.Driver;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UConexion {
	
	ResourceBundle rb = ResourceBundle.getBundle("framework");
	
	static UConexion uc;
	private static Connection c;
	
	// Implementar Singleton para conexion a base de datos
	// El driver, la ubicación de la base de datos, el usuario y la pass
	// se obtienen por medio de un archivo framework.properties

	private UConexion() {
		
		try {
			Class.forName(rb.getString("driver"));
			
			c = DriverManager.getConnection(rb.getString("ubicacionBD"), rb.getString("usuario"), rb.getString("contraseña"));	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static UConexion conectarBD() {
		
		UConexion retorno = null;
		
		try {
			if (c == null) {
				return uc = new UConexion();
			} else if (c.isClosed()) {
				return uc = new UConexion();
			} else {
				return uc;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	public Connection getConnection() {
		return c;
	}
	
}
