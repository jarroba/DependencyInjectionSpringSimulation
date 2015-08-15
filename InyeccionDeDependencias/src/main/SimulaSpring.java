package main;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import anotations.Autowired;
import proyecto_spring.NecesitaObjetoInyectado;

public class SimulaSpring {

	public static void main(String[] args) {		
		NecesitaObjetoInyectado necesitaInyeccion = new NecesitaObjetoInyectado();
		
		//Srping facilita un poco m�s al instanciar los objetos y no necesitar pedir la inyecci�n a un objeto propiamente, por simplificar pediremos la inyecci�n directamente
		inyectorDeObjetos (necesitaInyeccion);
		
		necesitaInyeccion.usarObjetoInyectadoYMostrarPorPantalla();
	}
	
	/**
	 * Inyecta un objeto cualquiera que est� anotado con @Autowired (Este m�todo simula al Framework Spring)
	 * 
	 * @param objetoNecesitaInyeccion objeto que requiere ser inyectado
	 */
	public static void inyectorDeObjetos (final Object objetoNecesitaInyeccion) {
		Class<?> claseObjetoNecesitaInyeccion = objetoNecesitaInyeccion.getClass();

		final Field[] variables = claseObjetoNecesitaInyeccion.getDeclaredFields();
		for (final Field variable : variables) {
			final Annotation anotacionObtenida = variable.getAnnotation(Autowired.class);

			if (anotacionObtenida != null && anotacionObtenida instanceof Autowired) {				
				Class<?> claseInyectar = variable.getType(); //Obtiene el tipo de clase a inyectar, independientemente del tipo
				
				try {
					Object nuevoObjetoDeMiClase = claseInyectar.getConstructor().newInstance(); //Instanciamos el objeto a inyectar
					
					variable.setAccessible(true);
					variable.set(objetoNecesitaInyeccion, nuevoObjetoDeMiClase); //Inyectamos el objeto en la variable del objeto que requiere la inyecci�n
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}

	}

}