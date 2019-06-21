package TDALista;

/**
 * Clase InvalidPositionException, modela una excepcion que refleja una posicion invalida.
 * @author Alan, Nico, Mati
 *
 */

public class InvalidPositionException extends Exception {

	/**
	 * Constructor de la clase InvalidPositionException.
	 * @param msg Mensaje de error.
	 */
	public InvalidPositionException(String msg) {
		super(msg);
	}
}
