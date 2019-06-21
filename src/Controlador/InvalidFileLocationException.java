package Controlador;


/**
 * Clase InvalidFileLocation, modela una excepcion que refleja si la ubicacion no es valida.
 * @author Alan, Mati y Nico
 *
 */
public class InvalidFileLocationException extends Exception{
	
	/**
	 * Constructor de la clase.
	 * @param msg Mensaje de error
	 */
	public InvalidFileLocationException (String msg) {
		super(msg);
	}

}
