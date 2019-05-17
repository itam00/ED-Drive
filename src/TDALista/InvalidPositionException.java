package TDALista;

/**
 * Clase InvalidPositionException, que sera lanzada en situaciones donde la posicion no sea valida
 * @author Alan, Nico, Mati
 *
 */
@SuppressWarnings("serial")
public class InvalidPositionException extends Exception {

	/**
	 * Llama a la clase Exception con el mensaje de error
	 * @param msg Mensaje con la excepcion
	 */
	public InvalidPositionException(String msg) {
		super(msg);
	}
}
