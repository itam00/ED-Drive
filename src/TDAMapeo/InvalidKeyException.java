package TDAMapeo;


/**
 * Clase InvalidKeyException, modela una excepcion que refleja una clave invalida.
 * @author Mati, Alan y Nico
 *
 */
public class InvalidKeyException extends Exception {
	
	/**
	 * Constructor de la clase InvalidKeyException.
	 * @param msg Mensaje de error.
	 */
	public InvalidKeyException (String msg) {
		super(msg);
	}
}
