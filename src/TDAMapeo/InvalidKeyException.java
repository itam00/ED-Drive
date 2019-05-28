package TDAMapeo;

@SuppressWarnings("serial")
/**
 * Clase InvalidKeyException, que lanzara excepciones en caso de que la clave no sea valida
 * @author Mati, Alan y Nico
 *
 */
public class InvalidKeyException extends Exception {
	
	/**
	 * Llama a la clase Exception con un mensaje de error
	 * @param msg Mensaje con la excepcion
	 */
	public InvalidKeyException (String msg) {
		super(msg);
	}
}
