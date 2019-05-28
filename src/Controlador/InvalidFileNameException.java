package Controlador;

@SuppressWarnings("serial")
/**
 * Clase InvalidFileNameException, la cual lanzara excepciones en casos de que el nombre del archivo no sea valido
 * @author Nico, Alan y Mati
 *
 */
public class InvalidFileNameException extends Exception{
	
	/**
	 * Llama a la clase exception con el mensaje de error
	 * @param msj Mensaje de error
	 */
	public InvalidFileNameException(String msj) {
		super(msj);
	}
}
