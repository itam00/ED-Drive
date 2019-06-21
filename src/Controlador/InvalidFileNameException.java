package Controlador;


/**
 * Clase InvalidFileNameException, modela una excepcion que refleja si el nombre del archivo no es valido.
 * @author Nico, Alan y Mati
 *
 */
public class InvalidFileNameException extends Exception{
	
	/**
	 * Constructor de la clase.
	 * @param msj Mensaje de error
	 */
	public InvalidFileNameException(String msj) {
		super(msj);
	}
}
