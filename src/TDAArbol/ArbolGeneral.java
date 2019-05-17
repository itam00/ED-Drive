package TDAArbol;

import java.util.Iterator;

import TDALista.BoundaryViolationException;
import TDALista.InvalidPositionException;
import TDALista.ListaDE;
import TDALista.Position;
import TDALista.PositionList;

public class ArbolGeneral<E> implements Tree<E> {

	protected TNode<E> root;
	protected int size;
	
	public ArbolGeneral() {
		root=null;
		size=0;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public Iterator<E> iterator() {
		return null;
	}

	@Override
	public Iterable<Position<E>> positions() {
		return null;
	}

	protected TNode<E> checkPosition(Position<E> p) throws InvalidPositionException{
		if (p==null)
			throw new InvalidPositionException ("posicion invalida");
		return (TNode<E>)p;
	}
	
	private TNode<E> buscar (Position<E> v, TNode<E> inicio) throws InvalidPositionException {
		TNode<E> nodo= checkPosition(v);
		TNode<E> toreturn=null;
		boolean encontrado=false;
		if (inicio.equals(nodo)) {
			toreturn=nodo;;
		}
		else
			if (isExternal(inicio))
				toreturn=null;
			else {
				Iterator<TNode<E>> it= inicio.getHijos().iterator();
				while (it.hasNext() && !encontrado) {
					toreturn=buscar(v,it.next());
					if (toreturn!=null) 
						encontrado=true;
				}
			}
		return toreturn;
	}
	
	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		TNode<E> nodo= checkPosition(v);
		return null;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if (isEmpty())
			throw new EmptyTreeException ("arbol vacio");
		return root;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TNode<E> nodo= checkPosition(v);
		if (isRoot(v))
			throw new BoundaryViolationException("pide el padre a la raiz");
		return nodo.getPadre();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TNode<E> nodo =checkPosition(v);
		PositionList<Position<E>> toreturn= new ListaDE<Position<E>>();
		for (TNode<E> n:nodo.getHijos())
			toreturn.addLast(n);
		return toreturn;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNode<E> nodo= checkPosition(v);
		return (!nodo.getHijos().isEmpty())
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TNode<E> nodo= checkPosition(v);
		return (nodo.getHijos().isEmpty());
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNode<E> n= checkPosition(v);
		return n.equals(v);
	}

	@Override
	public void createRoot(E e) throws InvalidOperationException {
		if (root!=null)
			throw new InvalidOperationException ("operación invalida, el arbol ya tiene raiz");
		root= new TNode<E>(e,null);
	}

	@Override
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		TNode<E> nodo= checkPosition(p);
		TNode<E> toreturn= new TNode<E>(e,nodo);
		nodo.getHijos().addFirst(toreturn);
		return toreturn;
	}

	@Override
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		TNode<E> nodo= checkPosition(p);
		TNode<E> toreturn=new TNode<E>(e,nodo);
		nodo.getHijos().addLast(toreturn);
		return toreturn;
	}

	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		if (isEmpty())
			throw new InvalidPositionException ("posicion invalida");
		TNode<E> nodo=checkPosition(p);
		TNode<E> RB=checkPosition(rb);
		@SuppressWarnings("unchecked")
		Position<TNode<E>> hermanoDerecho= (Position<TNode<E>>) rb;
		if (!RB.getPadre().equals(nodo))
			throw new InvalidPositionException("posicion invalida");
		TNode<E> toreturn=new TNode<E>(e,nodo);
		nodo.getHijos().addBefore(hermanoDerecho, toreturn);
		return p;
	}

	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		if (isEmpty())
			throw new InvalidPositionException("posicion invalida");
		TNode<E> nodo= checkPosition(p);
		TNode<E> LB= checkPosition(lb);
		@SuppressWarnings("unchecked")
		Position<TNode<E>> hermanoIzquierdo= (Position<TNode<E>>) lb;
		if (!LB.getPadre().equals(nodo))
			throw new InvalidPositionException("posicion invalida");
		TNode<E> toreturn= new TNode<E>(e,nodo);
		nodo.getHijos().addBefore(hermanoIzquierdo, toreturn);
		return toreturn;
	}

	@Override
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		if (isEmpty())
			throw new InvalidPositionException("posicion invalida");
		TNode<E> nodo= checkPosition(p);
		boolean removi=false;
		if (isExternal(p)) {
			
			removi=true;
			Position<TNode<E>> remover=(Position<TNode<E>>) p;
			nodo.getPadre().getHijos().remove(remover);
		}
		if (!removi)
			throw new InvalidPositionException(" posicion invalida");
	}

	@Override
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		if (isEmpty())
			throw new InvalidPositionException("posicion invalida");
		if(!isInternal(p)) 
			throw new InvalidPositionException("posicion invalida");
		TNode<E> nodo= checkPosition(p);
		
	}

	@Override
	public void removeNode(Position<E> p) throws InvalidPositionException {
		TNode<E> nodo= checkPosition(p);
		
	}

}
