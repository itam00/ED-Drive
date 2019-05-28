package TDAArbol;

@SuppressWarnings("serial")
/**
 * Clase InvalidOperationException, la cual lanzara excepcion cuando se intente ejecutar una operacion no valida
 * @author Mati, Nico y Alan
 *
 */
public class InvalidOperationException extends Exception {
	
	/**
	 * Llama a la clase Exception con el mensaje de error
	 * @param msg Mensaje de error
	 */
	public InvalidOperationException (String msg) {
		super(msg);
	}
}
