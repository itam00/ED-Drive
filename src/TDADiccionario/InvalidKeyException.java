package TDADiccionario;


/**
 * Clase InvalidKeyException, modela una excepcion que refleja una clave invalida.
 * @author Alan, Mati y Nico
 *
 */
public class InvalidKeyException extends Exception {
	
	/**
	 * Constructor de la clase InvalidKeyException.
	 * @param msg Mensaje de error
	 */
	public InvalidKeyException (String msg) {
		super(msg);
	}
}
