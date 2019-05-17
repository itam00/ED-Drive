package TDACola;

public class Nodo<E> {
	
	private E elemento;
	private Nodo<E> siguiente;
	
	/**
	 * inserta item en el nodo como elemento y le asigna el nodo siguiente.
	 * 
	 * @param item
	 * @param sig
	 */
	public Nodo( E item, Nodo<E> sig ) {
		elemento=item; siguiente=sig; 
	}
	/**
	 * inserta item como elemento del nodo.
	 * @param item
	 */
	public Nodo( E item ) {
		this(item,null); 
	}
	/**
	 * asigna el elemento al nodo.
	 * @param e
	 */
	public void setElemento(E e) {
		elemento=e;
	}

	/**
	 * Asigna el nodo s como siguiente del que recibe el mensaje.
	 * 
	 * @param s
	 */
	public void setSiguiente(Nodo<E> s) {
		siguiente=s;
	}
	/**
	 * devuelve el elemento almacenado en el nodo.
	 * @return
	 */
	public E getElemento() {
		return elemento;
	}
	/**
	 * devuelve el nodo siguiente.
	 * 
	 * @return 
	 */
	public Nodo<E> getSiguiente(){
		return siguiente;
	}
}
