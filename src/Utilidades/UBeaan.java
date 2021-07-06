package Utilidades;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class UBeaan {
	
	/**
	 * Devuelve un ArrayList<Field> con todos los atributos que posee el par�metro Object
	 * @param o: objeto
	 * @return retorno: ArrayList<Field> con todos los atributos que posee el param o
	 */
	public static List<Field> obtenerAtributos(Object o) {
		
		List<Field> retorno = new ArrayList<>();
		
		for(Field f: o.getClass().getDeclaredFields()) {
			retorno.add(f);
		}
		
		return retorno; 
		
	}
	
	/**
	 * Se debe ejecutar el m�todo Setter del String dentro del Object
	 * @param o
	 * @param att
	 * @param valor
	 */
	public static void ejecutarSet(Object o, String att, Object valor) {
		
		for (Field f: o.getClass().getDeclaredFields()) {
			
			try {
				f.set(valor, att);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	/**
	 * Devolver� el valor del atributo pasado por par�metro, ejecutando el getter dentro del objeto
	 * @param o
	 * @param att
	 */
	public static Object ejecutarGet(Object o, String att) {
		
		Object retorno = null;
		
		for(Method m: o.getClass().getDeclaredMethods()) {
			
			if(m.getName().equalsIgnoreCase("get" + att)) {
				try {
					try {
						retorno = m.invoke(o, null);
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IllegalAccessException | IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		return retorno;

	}

}