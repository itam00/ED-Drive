package TDALista;

import java.util.Iterator;

public class ListaSE<E> implements PositionList<E> {
	protected Nodo<E> head;
	protected int longitud;
	
	public ListaSE () {
		head=null;
		longitud=0;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return longitud;
	}

	@Override
	public boolean isEmpty() {
		return longitud==0;
	}

	@Override
	public Position<E> first() throws EmptyListException {
		if (head==null)
			throw new EmptyListException("La lista está vacía (como la cabeza de meli)");
		return head;
	}

	@Override
	public Position<E> last() throws EmptyListException {
		Nodo<E>p=head;
		if(isEmpty()) throw new EmptyListException("");
		while (p.getSiguiente()!=null)
			p=p.getSiguiente();
		return p;
	}

	@SuppressWarnings("unused")
	private Nodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		try {
			if (p==null) 
				throw new InvalidPositionException ("posición nula");
			return (Nodo<E>) p;
		}
		catch(ClassCastException e) {
			throw new InvalidPositionException ("...");
		}

	}
	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Nodo<E> n=checkPosition(p);
		if (n.getSiguiente()==null)
			throw new BoundaryViolationException("Next: siguiente de útilmo");
		return n.getSiguiente();
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Nodo<E> n= checkPosition(p);
		try {
			if (n==first())
				throw new BoundaryViolationException("Prev: anterior del primero");
			Nodo<E> aux=head;
			while (aux.getSiguiente() != n && aux.getSiguiente()!=null)
				aux=aux.getSiguiente();
			if (aux.getSiguiente() == null)
				throw new BoundaryViolationException("Prev: Posición no pertenece a la lista");
			return aux;
		}
		catch (EmptyListException e) {
			System.out.println(e.getMessage());
		}
		return n;
	}

	@Override
	public void addFirst(E element) {
		Nodo<E> nuevo= new Nodo<E>(element,head);
		head=nuevo;
		longitud++;
	}

	@Override
	public void addLast(E element) {
		if (isEmpty())
			addFirst(element);
		else {
			Nodo<E> n=head;
			while(n.getSiguiente()!=null)
				n=n.getSiguiente();
			n.setSiguiente(new Nodo<E> (element));
			longitud++;
		}
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		Nodo<E> n= checkPosition(p);
		try {
			if (n==last())
				n.setSiguiente(new Nodo<E>(element));
			else
				n.setSiguiente(new Nodo<E>(element,n.getSiguiente()));
		longitud++;
		}
		catch (EmptyListException e) {
			System.out.println(e.getMessage());
		}	
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		Nodo<E> n= checkPosition(p);
		Nodo<E> anterior;
		try {
			if(n==first())
				head= new Nodo<E>(element, head);
			else {
				anterior= (Nodo<E>)prev(p);
				anterior.setSiguiente(new Nodo<E>(element,n));
			}
			longitud++;
		}
		catch (EmptyListException e) {
			System.out.println (e.getMessage());
		}
		catch (BoundaryViolationException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		if (isEmpty())
			throw new InvalidPositionException ("Remove: lista vacía");
		Nodo<E> n= checkPosition(p);
		E resultado=n.getElemento();
		try {
			if (n==first()) {
				head=n.getSiguiente();
				n.setSiguiente(null);
			}
			else
				if (n==last()) {
					Nodo<E> anterior =(Nodo<E>) prev(p);
					anterior.setSiguiente(null);
				}
				else {
					Nodo<E> anterior=(Nodo<E>) prev(p);
					anterior.setSiguiente((Nodo<E>) next(p));
					n.setSiguiente(null);
				}
			longitud--;
			return resultado;
		}
		catch (BoundaryViolationException e) {
			System.out.println(e.getMessage());
		}
		catch (EmptyListException e) {
			System.out.println(e.getMessage());
		}
		return resultado;
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		if (isEmpty())
			throw new InvalidPositionException("vacia");
		Nodo<E> n= checkPosition(p);
		E resultado= n.getElemento();
		n.setElemento(element);
		return resultado;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> l= new ListaSE<Position<E>>();
		if (!isEmpty()) {
			Position<E> p;
			try {
				p=first();
				while (p!=last()) {
					l.addLast(p);
					p=next(p);
				}
				l.addLast(p);
			}
			catch (EmptyListException|BoundaryViolationException|InvalidPositionException e) {
				System.out.println(e.getMessage());
			}
		}
		return l;
	}
}
