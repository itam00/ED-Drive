package Controlador;

@SuppressWarnings("serial")
/**
 * Clase InvalidFileLocation, la cual lanzara excepciones en casos de que la ubicacion no sea valida
 * @author Alan, Mati y Nico
 *
 */
public class InvalidFileLocationException extends Exception{
	
	/**
	 * Llama a la clase exception con el mensaje de error
	 * @param msg Mensaje de error
	 */
	public InvalidFileLocationException (String msg) {
		super(msg);
	}

}
