package TDAArbol;

import TDALista.ListaDE;
import TDALista.Position;
import TDALista.PositionList;

public class TNodo<E> implements Position<E>{

	protected E elemento;
	protected TNodo<E> padre;
	protected PositionList<TNodo<E>> hijos;
	
	public TNodo(E elem, TNodo<E> t) {
		elemento = elem;
		padre = t;
		hijos = new ListaDE<TNodo<E>>();
	}
	
	public TNodo(E elem) { this(elem,null); }
	
	public PositionList<TNodo<E>> getHijos(){
		return hijos;
	}
	
	public TNodo<E> getPadre(){
		return padre;
	}
	
	public void setElemento(E elem) {
		elemento = elem;
	}
	
	public void setPadre(TNodo<E> t) {
		padre = t;
	}
	
	public E element()  {
		return elemento;
	}
}
