package TDADiccionario;

@SuppressWarnings("serial")
/**
 * Clase InvalidEntryException, modela una excepcion que refleja una entrada invalida.
 * @author Mati, Alan y Nico
 *
 */
public class InvalidEntryException extends Exception {
	
	/**
	 * Constructor de la clase InvalidEntryException.
	 * @param msg Mensaje de error
	 */
	public InvalidEntryException (String msg) {
		super(msg);
	}

}
