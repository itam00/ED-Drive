package TDALista;

/**
 * Clase BoundaryViolationException, la excepci�n se lanzar� cuando se excede el l�mite de la estructura de datos.
 * @author Alan, Nico y Mati
 *
 */
@SuppressWarnings("serial")
public class BoundaryViolationException extends Exception {

	/**
	 * Llama a la clase Exception con el mensaje de error
	 * @param msg Mensaje con la excepcion
	 */
	public BoundaryViolationException(String msg) {
		super(msg);
	}
}