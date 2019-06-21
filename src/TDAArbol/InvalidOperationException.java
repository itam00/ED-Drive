package TDAArbol;


/**
 * Clase InvalidOperationException, modela una excepcion que refleja una operacion invalida
 * @author Mati, Nico y Alan
 *
 */
public class InvalidOperationException extends Exception {
	
	/**
	 * Contructor de la clase InvalidOperationException
	 * @param msg Mensaje de error
	 */
	public InvalidOperationException (String msg) {
		super(msg);
	}
}
