package Utilidades;

import com.mysql.jdbc.Driver;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class UConexion {
	
	static UConexion uc;
	
	// Implementar Singleton para conexion a base de datos
	// El driver, la ubicación de la base de datos, el usuario y la pass
	// se obtienen por medio de un archivo framework.properties

	private UConexion() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
			//java.sql.PreparedStatement st = c.prepareStatement("select * from Tabla");
			//ResultSet rs = st.executeQuery();
			
			/*while (rs.next()) {
				System.out.println(rs.getString("nombre"));
				System.out.println(rs.getObject("id"));
			}*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static UConexion conectarBD() {
		
		if (uc != null) {
			return uc;
		} else {
			return uc = new UConexion();
		}
	}
	
}
