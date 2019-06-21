package TDALista;

/**
 * Clase EmptyListException, modela una excepcion que refleja una lista vacia.
 * @author Alan, Nico, Mati
 *
 */
@SuppressWarnings("serial")
public class EmptyListException extends Exception {

	/**
	 * Constructor de la clase EmptyListException.
	 * @param msg Mensaje de error.
	 */
	public EmptyListException(String msg) {
		super(msg);
	}
}
