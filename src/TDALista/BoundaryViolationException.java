package TDALista;

/**
 * Clase BoundaryViolationException, modela una excepcion que refleja una violacion de los limites de la ED.
 * @author Alan, Nico y Mati
 *
 */
@SuppressWarnings("serial")
public class BoundaryViolationException extends Exception {

	/**
	 * Constructor de la clase BoundaryViolationException.
	 * @param msg Mensaje de error
	 */
	public BoundaryViolationException(String msg) {
		super(msg);
	}
}
