package TDACola;

/**
 * Clase EmptyQueueExeption, que sera lanzada en situaciones donde la cola no deba estar vacia
 * @author Alan, Nico y Mati
 *
 */
@SuppressWarnings("serial")
public class EmptyQueueException extends Exception{


	/**
     * Llama a la clase Exception con el mensaje de error
     * @param msg Mensaje con la excepcion
	 */
	public EmptyQueueException(String msg) {
		super(msg);
	}
}
