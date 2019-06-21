package Controlador;


/**
 * Clase InvalidFileException, modela una excepcion que refleja si un archivo no es valido.
 * @author Alan, Nico y Mati
 */
public class InvalidFileException extends Exception{
	
	/**
	 * Constructor de la clase.
	 * @param msj Mensaje de error
	 */
	public InvalidFileException(String msj) {
		super(msj);
	}
}
