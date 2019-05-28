package TDAArbol;

import TDALista.ListaDE;
import TDALista.Position;
import TDALista.PositionList;

/**
 * Clase TNodo, los cuales almacenar un elemento, una referencia a su padre y una lista de sus hijos
 * @author Nico, Alan y Mati
 * @param <E> Tipo de elemento
 */
public class TNodo<E> implements Position<E>{

	protected E elemento; // Elemento a guardar
	protected TNodo<E> padre; // Nodo padre
	protected PositionList<TNodo<E>> hijos; // Lista de hijos
	
	/**
	 * Inserta un elemento y un padre al nodo
	 * @param elem Elemento del TNodo
	 * @param t Padre del TNodo
	 */
	public TNodo(E elem, TNodo<E> t) {
		elemento = elem;
		padre = t;
		hijos = new ListaDE<TNodo<E>>();
	}
	
	/**
	 * Inserta un elemento al nodo
	 * @param elem Elemento del nodo
	 */
	public TNodo(E elem) { this(elem,null); }
	
	/**
	 * Retorna una lista de los hijos del nodo
	 * @return Lista con los hijos
	 */
	public PositionList<TNodo<E>> getHijos(){
		return hijos;
	}
	
	/**
	 * Retorna el nodo padre
	 * @return Nodo padre
	 */
	public TNodo<E> getPadre(){
		return padre;
	}
	
	/**
	 * Asigna un elemento al nodo
	 * @param elem Elemento a asignar en el nodo
	 */
	public void setElemento(E elem) {
		elemento = elem;
	}
	
	/**
	 * Asigna un padre al nodo
	 * @param t Nodo padre
	 */
	public void setPadre(TNodo<E> t) {
		padre = t;
	}
	
	/**
	 * Retorna el elemento almacenado en el nodo
	 * @return Elemento del nodo
	 */
	public E element()  {
		return elemento;
	}
}
