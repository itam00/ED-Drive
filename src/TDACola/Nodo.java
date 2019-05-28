package TDACola;

/**
 * Clase Nodo, los cuales contendran un elemento y una referencia al siguiente
 * @author Alan, Nico y Mati
 * @param <E> Tipo de elemento a guardar
 */
public class Nodo<E> {
	
	private E elemento; // Elemento guardado en el nodo 
	private Nodo<E> siguiente; // Referencia al siguiente nodo
	
	/**
	 * inserta item en el nodo como elemento y le asigna el nodo siguiente.
	 * @param item Elemento a guardar
	 * @param sig Siguiente nodo
	 */
	public Nodo( E item, Nodo<E> sig ) {
		elemento=item; siguiente=sig; 
	}
	
	/**
	 * inserta item como elemento del nodo.
	 * @param item Elemento del nodo
	 */
	public Nodo( E item ) {
		this(item,null); 
	}
	
	/**
	 * asigna el elemento al nodo.
	 * @param e Elemento a asignar
	 */
	public void setElemento(E e) {
		elemento=e;
	}

	/**
	 * Asigna el nodo s como siguiente del que recibe el mensaje.
	 * @param s Nodo siguiente al actual
	 */
	public void setSiguiente(Nodo<E> s) {
		siguiente=s;
	}
	
	/**
	 * devuelve el elemento almacenado en el nodo.
	 * @return Elemento almacenado en el nodo
	 */
	public E getElemento() {
		return elemento;
	}
	
	/**
	 * devuelve el nodo siguiente.
	 * @return Nodo siguiente
	 */
	public Nodo<E> getSiguiente(){
		return siguiente;
	}
}
