package TDALista;

/**
 * Clase InvalidPositionException, modela una excepcion que refleja una posicion invalida.
 * @author Alan, Nico, Mati
 *
 */
@SuppressWarnings("serial")
public class InvalidPositionException extends Exception {

	/**
	 * Constructor de la clase
	 * @param msg Mensaje de error.
	 */
	public InvalidPositionException(String msg) {
		super(msg);
	}
}
