package TDAArbol;

import java.util.Iterator;

import TDACola.ColaConArregloCircular;
import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.Position;
import TDALista.PositionList;
import TDAPila.EmptyStackException;
import TDAPila.PilaConEnlaces;
import TDAPila.Stack;

public class Arbol<E> implements Tree<E> {
	
	protected TNodo<E> raiz;
	protected Integer tamaño;

	public Arbol() {
		raiz = null;
		tamaño = 0;
	}

	public boolean isEmpty() {
		return raiz == null;
	}
	
	public int size() {
		return tamaño;
	}
	
	public void createRoot(E e) throws InvalidOperationException{
		if(!isEmpty()) 
			throw new InvalidOperationException("Arbol no vacio");
		raiz = new TNodo<E>(e);
		tamaño = 1;
	}
	
	public Position<E> root() throws EmptyTreeException{
		if(isEmpty())
			throw new EmptyTreeException("Arbol vacio");
		return raiz;
	}
	
	public boolean isRoot(Position<E> v) throws InvalidPositionException{
		if (v == null)
			throw new InvalidPositionException("Posicion nula");
		return raiz == v;
	}
	
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException{
		TNodo<E> nodo = checkPosition(v);
		if (nodo == raiz)
			throw new BoundaryViolationException("La posicion raiz no tiene padre");
		return nodo.getPadre();
	}
	
	public E replace(Position<E> v, E e) throws InvalidPositionException{
		if(isEmpty())
			throw new InvalidPositionException("Arbol vacio");
		TNodo<E> nodo = checkPosition(v);
		E aux = nodo.element();
		nodo.setElemento(e);
		return aux;
	}
	
	public boolean isExternal(Position<E> v) throws InvalidPositionException{
		TNodo<E> nodo = checkPosition(v);
		return nodo.getHijos().isEmpty();
	}
	
	public boolean isInternal(Position<E> v) throws InvalidPositionException{
		TNodo<E> nodo = checkPosition(v);
		return !nodo.getHijos().isEmpty();
	}
	
	protected TNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		if (p == null) {
			throw new InvalidPositionException("Posicion nula");
		}
		try {
			TNodo<E> aux = (TNodo<E>) p;
			if(aux == null || aux.element() == null)
				throw new InvalidPositionException("El nodo de la posicion es nulo o el elemento es nulo");
			return aux;
		}catch(ClassCastException e) {
			throw new InvalidPositionException("Posicion no es de un tipo valido para la lista");
		}
	}
	
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException{
		if(isEmpty())
			throw new InvalidPositionException("Arbol vacio");
		TNodo<E> padre = checkPosition(p);
		TNodo<E> nodo = new TNodo<E>(e,padre);
		padre.getHijos().addFirst(nodo);
		tamaño++;
		return nodo;
	}
	
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException{
		if(isEmpty())
			throw new InvalidPositionException("Arbol vacio");
		TNodo<E> padre = checkPosition(p);
		TNodo<E> nodo = new TNodo<E>(e,padre);
		padre.getHijos().addLast(nodo);
		tamaño++;
		return nodo;
	}
	
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException{
		if(isEmpty())
			throw new InvalidPositionException("Arbol vacio");
		TNodo<E> padre = checkPosition(p);
		TNodo<E> hermanoDerecho = checkPosition(rb);
		TNodo<E> nuevo = new TNodo<E>(e,padre);
		PositionList<TNodo<E>> hijosPadre = padre.getHijos();
		boolean encontre = false;
		Position<TNodo<E>> pp = null;
		try {
			pp = hijosPadre.first();
		} catch (EmptyListException e2) {
			e2.printStackTrace();
		}
		while(pp != null && !encontre) {
			if(hermanoDerecho == pp.element()) 
				encontre = true;
			else
				try {
					pp = (pp != hijosPadre.last() ? hijosPadre.next(pp) : null);
				} catch (EmptyListException e1) {
					e1.printStackTrace();
				} catch (BoundaryViolationException e1) {
					e1.printStackTrace();
				}
		}
		if(!encontre) 
			throw new InvalidPositionException("p no es padre de rb");
		hijosPadre.addBefore(pp, nuevo);
		tamaño++;
		return nuevo;
	}
	
	public Position<E> addAfter (Position<E> p, Position<E> lb, E e) throws InvalidPositionException{
		if(isEmpty())
			throw new InvalidPositionException("Arbol vacio");
		TNodo<E> padre = checkPosition(p);
		TNodo<E> hermanoIzquierdo = checkPosition(lb);
		TNodo<E> nuevo = new TNodo<E>(e,padre);
		PositionList<TNodo<E>> hijosPadre = padre.getHijos();
		boolean encontre = false;
		Position<TNodo<E>> pp = null;
		try {
			pp = hijosPadre.first();
		} catch (EmptyListException e2) {
			e2.printStackTrace();
		}
		while(pp != null && !encontre) {
			if(hermanoIzquierdo == pp.element()) 
				encontre = true;
			else
				try {
					pp = (pp != hijosPadre.last() ? hijosPadre.next(pp) : null);
				} catch (EmptyListException e1) {
					e1.printStackTrace();
				} catch (BoundaryViolationException e1) {
					e1.printStackTrace();
				}
		}
		if(!encontre)
			throw new InvalidPositionException("p no es padre d lb");
		hijosPadre.addAfter(pp, nuevo);
		tamaño++;
		return nuevo;
 	}
	
	public void removeExternalNode (Position<E> p) throws InvalidPositionException{
		if(isEmpty())
			throw new InvalidPositionException("Arbol vacio");
		TNodo<E> n = checkPosition(p);
		if(!n.getHijos().isEmpty())
			throw new InvalidPositionException("p no es hoja");
		if(!isRoot(p)) {
			TNodo<E> padre = n.getPadre();
			PositionList<TNodo<E>> hijosPadre = padre.getHijos();
			boolean encontre = false;
			Position<TNodo<E>> pp = null;
			Iterable<Position<TNodo<E>>> posiciones = hijosPadre.positions();
			Iterator<Position<TNodo<E>>> it = posiciones.iterator();
			while(it.hasNext() && !encontre) {
				pp = it.next();
				if(pp.element() == n)
					encontre = true;
			}
			if(!encontre)
				throw new InvalidPositionException("p no pertenece a la lista de hijos de su padre");
			hijosPadre.remove(pp);
			}
		else {
			if(n.getHijos().isEmpty())
				raiz = null;
			else
				throw new InvalidPositionException("El nodo es la raiz y no puede ser eliminado si tiene hijos");
		}
		tamaño--;
	}
	
	public void removeInternalNode (Position<E> p) throws InvalidPositionException{
		if(isEmpty())
			throw new InvalidPositionException("Arbol vacio");
		if(isExternal(p))
			throw new InvalidPositionException("El nodo no puede ser externo");
		TNodo<E> n = checkPosition(p);
		if(isRoot(p)) {
			PositionList<TNodo<E>> hijos = n.getHijos();
			if(hijos.size() == 1) {
					TNodo<E> hijo = null;
					try {
						hijo = hijos.remove(hijos.first());
					} catch (EmptyListException e) {
						e.printStackTrace();
					}
					hijo.setPadre(null);
					raiz = hijo;
					
			}
			else
				throw new InvalidPositionException("Si el nodo es la raiz solo puede tener un hijo para ser eliminado");
		}
		else {
			TNodo<E> padre = n.getPadre();
			PositionList<TNodo<E>> hermanos = padre.getHijos();
			PositionList<TNodo<E>> hijos = n.getHijos();
			Position<TNodo<E>> posListaHermanos = null;
			try {
				posListaHermanos = hermanos.isEmpty() ? null : hermanos.first();
			} catch (EmptyListException e) {
				e.printStackTrace();
			}
			while(posListaHermanos != null && posListaHermanos.element() != n)
				try {
					posListaHermanos = (hermanos.last() == posListaHermanos) ? null : hermanos.next(posListaHermanos);
				} catch (EmptyListException | BoundaryViolationException e) {
					e.printStackTrace();
				}
			if(posListaHermanos == null)
				throw new InvalidPositionException("La posicion no es hijo del padre");
			while(!hijos.isEmpty()) {
				TNodo<E> hijo = null;
				try {
					hijo = hijos.remove(hijos.first());
				} catch (EmptyListException e) {
					e.printStackTrace();
				}
				hijo.setPadre(padre);
				hermanos.addBefore(posListaHermanos, hijo);
			}
			hermanos.remove(posListaHermanos);
		}
		n.setPadre(null);
		n.setElemento(null);
		tamaño--;
	}
	
	public void removeNode (Position<E> p) throws InvalidPositionException{
		if(isExternal(p))
			removeExternalNode(p);
		else
			removeInternalNode(p);
	}
	
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException{
		TNodo<E> p = checkPosition(v);
		PositionList<Position<E>> iterable = new ListaDoblementeEnlazada<Position<E>>();
		for(TNodo<E> n : p.getHijos())
			iterable.addLast(n);
		return iterable;
	}
	
	public Iterable<Position<E>> positions(){
		PositionList<Position<E>> iterable = new ListaDoblementeEnlazada<Position<E>>();
		if(!isEmpty())
			pre(iterable,raiz);
		return iterable;
	}
	
	private void pre(PositionList<Position<E>> iterable, TNodo<E> r) {
		iterable.addLast(r);
		for(TNodo<E> h : r.getHijos())
			pre(iterable,h);
	}
	
	public Iterator<E> iterator(){
		PositionList<E> l = new ListaDoblementeEnlazada<E>();
		for(Position<E> p : positions())
			try {
				l.addLast(p.element());
			} catch (InvalidPositionException e) {
				e.printStackTrace();
			}
		return l.iterator();
	}
	
	
	public static boolean archivoValido(Stack<String> c) {
		String aux = "";
		try {
			aux = c.pop();
		} catch (EmptyStackException e) {
			e.printStackTrace();
		}
		String abre = "<archivo>";
		String cierra = "</archivo>";
		String nombre = "";
		int i = 0;
		int j = 0;
		boolean toReturn = true;
		while(toReturn && i < 9) {
			if(aux.charAt(i) == abre.charAt(i))
				i++;
			else
				toReturn = false;
		}
		while(toReturn && i < aux.length() && aux.charAt(i) != '<') {
			nombre += aux.charAt(i);
			i++;
		}
		if(nombre == "")
			toReturn = false;
		while(toReturn && j < 10) {
			if(aux.charAt(i) == cierra.charAt(j)) {
				i++;
				j++;
			}
			else
				toReturn = false;
		}
		return toReturn;
	}
	
	
}