package TDADiccionario;

@SuppressWarnings("serial")
/**
 * Clase InvalidKeyException, la cual lanzara excepciones en caso de que la clave no sea valid
 * @author Alan, Mati y Nico
 *
 */
public class InvalidKeyException extends Exception {
	
	/**
	 * Llama a la clase exception con un mensaje de error
	 * @param msg Mensaje de error
	 */
	public InvalidKeyException (String msg) {
		super(msg);
	}
}
