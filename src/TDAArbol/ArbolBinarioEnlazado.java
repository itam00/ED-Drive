package TDAArbol;

import TDALista.BoundaryViolationException;
import TDALista.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.Position;
import TDALista.PositionList;

public class ArbolBinarioEnlazado<E> implements BinaryTree<E> {

	protected BTPosition<E> raiz;
	protected int size;
	
	public ArbolBinarioEnlazado() {
		raiz = null;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		if(v == null)
			throw new InvalidPositionException("Posicion nula");
		return raiz == v;
	}
	
	@SuppressWarnings("unchecked")
	public void createRoot(E e) throws InvalidOperationException{
		if(!isEmpty())
			throw new InvalidOperationException("El arbol no esta vacio");
		raiz = new BTNode<E>(e, null, null, null);
		size = 1;
	}
	
	public Position<E> root() throws EmptyTreeException{
		if(isEmpty())
			throw new EmptyTreeException("Arbol vacio");
		return raiz;
	}
	
	public BTPosition<E> checkPosition(Position<E> v) throws InvalidPositionException{
		if(v == null)
			throw new InvalidPositionException("Posicion nula");
		try {
			BTPosition<E> aux = (BTPosition<E>) v;
			if(aux == null || aux.element() == null)
				throw new InvalidPositionException("El nodo de la posicion es nulo o el elemento es nulo");
			return aux;
		}catch(ClassCastException e) {
			throw new InvalidPositionException("Posicion no es de un tipo valido");
		}
	}
	
	public boolean hasLeft(Position<E> v) throws InvalidPositionException{
		BTPosition<E> n = checkPosition(v);
		return n.getLeft() != null;
	}
	
	public boolean hasRight(Position<E> v) throws InvalidPositionException{
		BTPosition<E> n = checkPosition(v);
		return n.getRight() != null;
	}
	
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException{
		BTPosition<E> n = checkPosition(v);
		if(n.getLeft() == null)
			throw new BoundaryViolationException("Posicion no tiene izquierda");
		return n.getLeft();
	}
	
	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException{
		BTPosition<E> n = checkPosition(v);
		if(n.getRight() == null)
			throw new BoundaryViolationException("Posicion no tiene derecha");
		return n.getRight();
	}
	
	public boolean isInternal(Position<E> v) throws InvalidPositionException{
		return hasLeft(v) || hasRight(v);
	}
	
	public boolean isExternal(Position<E> v) throws InvalidPositionException{
		return !hasLeft(v) && hasRight(v);
	}
	
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException{
		PositionList<Position<E>> iterable = new ListaDoblementeEnlazada<Position<E>>();
		if(hasLeft(v))
			try {
				iterable.addLast(left(v));
			} catch (BoundaryViolationException e1) {
				e1.printStackTrace();
			}
		if(hasRight(v))
			try {
				iterable.addLast(right(v));
			} catch (BoundaryViolationException e) {
				e.printStackTrace();
			}
		return iterable;
	}
	
}