package TDALista;
	/**
	 * clase nodo doblemente enlazado.
	 * @author Alan, nico y matias
	 * @param <E> tipo de dato del elemento a guarda en el nodo.
	 */
public class NodoDE <E> implements Position<E> {
	protected E elem;	//elemento del nodo
	protected NodoDE<E> anterior;	//referencia al nodo anterior
	protected NodoDE<E> siguiente;	//referencia al nodo siguiente
	
	/**
	 * inserta el elemento en nodo y le asigna las referencias al nodo anterior y al siguiente.
	 * @param elemento referencia al elemento a ser guardado en el nodo
	 * @param sig referencia al elemento sigueinte 
	 * @param ant referencia al elemento anterior
	 */
	public NodoDE (E elemento, NodoDE<E> sig, NodoDE<E> ant){
		elem=elemento;
		anterior=ant;
		siguiente=sig;
	}
	
	/**
	 * inserta el elemento en el nodo y deja nulas las referencias al anterior y al siguiente
	 * @param elemento referencia al elemento a ser guardado en eel nodo
	 */
	public NodoDE (E elemento) {
		elem=elemento;
		anterior=null;
		siguiente=null;
	}
	
	/**
	 * Reemplaza el elemento del nodo por "elemento".
	 * @param elemento Elemento que reemplazara al actual
	 */
	public void setElemento(E elemento) {
		elem=elemento;
	}
	
	/**
	 * reemplaza la referencia al nodo siguiente por n
	 * @param n referencia al nodo.
	 */
	public void setSiguiente (NodoDE<E> n) {
		siguiente=n;
	}
	
	/**
	 * reemplaza la referencia al nodo anterior por n
	 * @param n referencia al nodo.
	 */
	public void setAnterior(NodoDE<E> n) {
		anterior=n;
	}
	
	public E element() {
		return elem;
	}
	
	/**
	 * retorna la referencia al nodo siguiente
	 * @return referencia al nodo siguiente
	 */
	public NodoDE<E> getSiguiente() {
		return siguiente;
	}
	
	/**
	 * retorna la referencia al nodo siguiente 
	 * @returnr referencia al nodo siguiente
	 */
	public NodoDE<E> getAnterior(){
		return anterior;
	}

}
