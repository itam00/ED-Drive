package TDAArbol;

@SuppressWarnings("serial")
/**
 * Clase EmptyTreeException, el cual lanzara excepciones si el arbol esta vacio
 * @author Nico, Alan y Mati
 *
 */
public class EmptyTreeException extends Exception {

	/**
	 * Modela una excepcion cuando el arbol esta vacio
	 * @param msg Mensaje de error
	 */
	public EmptyTreeException(String msg) {
		super (msg);
	}
	
}
