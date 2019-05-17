package TDALista;

/**
 * Clase EmptyListException, que sera lanzada en situaciones donde la lista no deba estar vacia
 * @author Alan, Nico, Mati
 *
 */
@SuppressWarnings("serial")
public class EmptyListException extends Exception {

	/**
	 * Llama a la clase Exception con el mensaje de error
	 * @param msg Mensaje con la excepcion
	 */
	public EmptyListException(String msg) {
		super(msg);
	}
}
