package TDAArbol;

import TDALista.ListaDE;
import TDALista.Position;
import TDALista.PositionList;

public class TNode<E> implements Position<E> {

	protected E elemento;
	protected TNode<E> padre;
	protected PositionList<TNode<E>> hijos;
	
	@SuppressWarnings("unchecked")
	public TNode(E elem, TNode<E> p) {
		elemento=elem;
		hijos= (PositionList<TNode<E>>) new ListaDE<E>();
		padre=p;
	}
	
	@Override
	public E element() {
		return elemento;
	}
	
	public PositionList<TNode<E>> getHijos(){
		return hijos;
	}
	
	public TNode<E> getPadre(){
		return padre;
	}
	
	public void setElement(E elem) {
		elemento=elem;
	}
	
	public void setPadre(TNode<E> p) {
		padre=p;
	}

}
