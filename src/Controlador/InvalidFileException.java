package Controlador;

@SuppressWarnings("serial")
/**
 * Clase InvalidFileException, la cual lanzara excepciones si un archivo no es valido
 * @author Alan, Nico y Mati
 */
public class InvalidFileException extends Exception{
	
	/**
	 * Llama a la clase Exception con un mensaje de error
	 * @param msj Mensaje de error
	 */
	public InvalidFileException(String msj) {
		super(msj);
	}
}
