package TDAPila;

@SuppressWarnings("serial")

/**
 * Clase EmptyStackException, que sera lanzadas en situaciones que la pila este vacia
 * @author Alan, Nico y Mati
 *
 */
public class EmptyStackException extends Exception {
	
	/**
	 * Llama a la clase Exception con el mensaje de error
	 * @param msg Mensaje con la excepcion
	 */
	public EmptyStackException (String msg) {
		super(msg);
	}
}
