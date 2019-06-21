package TDAPila;

/**
 * Clase EmptyStackException, modela una excepcion que refleja una pila vacia.
 * @author Alan, Nico y Mati
 *
 */
public class EmptyStackException extends Exception {
	
	/**
	 * Constructor de la clase EmptyStackException.
	 * @param msg Mensaje de error.
	 */
	public EmptyStackException (String msg) {
		super(msg);
	}
}
