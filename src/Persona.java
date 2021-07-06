import Anotaciones.Columna;
import Anotaciones.Id;
import Anotaciones.Tabla;

@Tabla(nombre="persona")
public class Persona {
	@Id
	@Columna(nombre="id")
	private Long id;
	@Columna(nombre="nombre")
	private String nombre;
	@Columna(nombre="dni")
	private Integer dni;
	
	public Persona(Long id, String nombre, Integer dni) {
		this.id = id;
		this.nombre = nombre;
		this.dni = dni;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Integer getDni() {
		return this.dni;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	
	public String toString() {
		String retorno;
		
		retorno = this.id + " " + this.nombre + " " + this.dni;
		
		return retorno;
	}

}
