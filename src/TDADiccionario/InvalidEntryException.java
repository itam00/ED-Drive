package TDADiccionario;

@SuppressWarnings("serial")
/**
 * Clase InvalidEntryException, lo cual lanzara una excepcion en caso de que la entrada no sea valida
 * @author Mati, Alan y Nico
 *
 */
public class InvalidEntryException extends Exception {
	
	/**
	 * LLama a la clase exception con un mensaje de error
	 * @param msg Mensaje de error
	 */
	public InvalidEntryException (String msg) {
		super(msg);
	}

}
