package proyecto_spring;

import anotations.Autowired;

public class NecesitaObjetoInyectado {

	@Autowired
	private Inyectar miObjetoInyectado;

	public void usarObjetoInyectadoYMostrarPorPantalla() {		
		String textoObtenidoDeObjetoInyectado = miObjetoInyectado.metodoDeObjetoInyectado();		
		System.out.println("Lo que me devuelve el método del objeto que me han inyectado: " + textoObtenidoDeObjetoInyectado);
	}

}