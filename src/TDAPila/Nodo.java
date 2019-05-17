package TDAPila;
	/**
	 * Clase nodo simplemente enlazado.
	 * 
	 * @author Alan, Nico y Rios.
	 *
	 * @param <E> tipo del elemento a guardar en el nodo.
	 */
public class Nodo<E> {
	
	private E elemento;
	private Nodo<E> siguiente;
	
	/**
	 * inserta item en el nodo y le asigna al nodo el siguiente.
	 * @param item Elemento a guardar en el nodo.
	 * @param sig Referencia al nodo siguiente.
	 */
	public Nodo( E item, Nodo<E> sig ) {
		elemento=item; siguiente=sig; 
	}
	/**
	 * inserta item en el nodo
	 * @param item Elemento a guardar en el nodo
	 */
	public Nodo( E item ) {
		this(item,null); 
	}
	/**
	 * reemplaza el elemento que había en el nodo por e.
	 * @param e elemento que se asignará al nodo.
	 */
	public void setElemento(E e) {
		elemento=e;
	}

	/**
	 * Reemplaza la referencia al nodo siguiente por s.
	 * @param s nodo a asignar.
	 */
	public void setSiguiente(Nodo<E> s) {
		siguiente=s;
	}
	/**
	 * devuelve el elemento almacenado en el nodo.
	 * @return elemento almacenado en el nodo.
	 */
	public E getElemento() {
		return elemento;
	}
	/**
	 * devuelve el siguiente nodo.
	 * @return referencia al nodo siguiente.
	 */
	public Nodo<E> getSiguiente(){
		return siguiente;
	}
}
