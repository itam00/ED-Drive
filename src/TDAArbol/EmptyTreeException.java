package TDAArbol;


/**
 * Clase EmptyTreeException, modela una excepcion que refleja si el arbol esta vacio
 * @author Nico, Alan y Mati
 *
 */
public class EmptyTreeException extends Exception {

	/**
	 * Contructor de la clase EmptyTreeException
	 * @param msg Mensaje de error
	 */
	public EmptyTreeException(String msg) {
		super (msg);
	}
	
}
