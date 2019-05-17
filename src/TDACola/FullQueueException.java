package TDACola;

/**
 * NO COPIAR EN EL PROYECTO
 * NO COPIAR EN EL PROYECTO
 * NO COPIAR EN EL PROYECTO
 * NO COPIAR EN EL PROYECTO
 * NO COPIAR EN EL PROYECTO
 * NO COPIAR EN EL PROYECTO
 * NO COPIAR EN EL PROYECTO
 * NO COPIAR EN EL PROYECTO
 */

/**
 * Clase FullQueueExeption, sera lanzada en situaciones donde la cola no deba estar llena
 * @author Alan, Nico y Mati
 *
 */
@SuppressWarnings("serial")
public class FullQueueException extends Exception {
	
	public FullQueueException(String msg) {
		
		/**
	     * Llama a la clase Exception con el mensaje de error
	     * @param msg Mensaje con la excepcion
		 */
		super(msg);
	}
}
