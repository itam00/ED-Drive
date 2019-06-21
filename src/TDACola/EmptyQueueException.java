package TDACola;

/**
 * Clase EmptyQueueExeption, modela una excepcion que refleja si la cola esta vacia
 * @author Alan, Nico y Mati
 *
 */
public class EmptyQueueException extends Exception{


	/**
     * Contructor de la clase EmptyQueueException
     * @param msg Mensaje con la excepcion
	 */
	public EmptyQueueException(String msg) {
		super(msg);
	}
}
