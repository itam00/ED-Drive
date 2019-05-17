package TDALista;

public class Nodo<E> implements Position<E>{
	
	private E elemento;
	private Nodo<E> siguiente;
	
	/**
	 * inserta item en el nodo y le asigna al nodo el siguiente.
	 * 
	 * @param item
	 * @param sig
	 */
	public Nodo( E item, Nodo<E> sig ) {
		elemento=item; siguiente=sig; 
	}
	/**
	 * inserta item en el fondo de la pila
	 * @param item
	 */
	public Nodo( E item ) {
		this(item,null); 
	}
	/**
	 * asigna el elemento a la pila.
	 * @param e
	 */
	public void setElemento(E e) {
		elemento=e;
	}

	/**
	 * Asigna el nodo s como siguiente elemento de la pila.
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
	 * devuelve el siguiente nodo en la pila.
	 * 
	 * @return 
	 */
	public Nodo<E> getSiguiente(){
		return siguiente;
	}
	@Override
	public E element() {
		
		return elemento;
	}
}
