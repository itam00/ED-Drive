package TDALista;

/**
 * Clase BoundaryViolationException, la excepcion se lanzara cuando se excede el limite de la estructura de datos.
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
