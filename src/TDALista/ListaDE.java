package TDALista;

import java.util.Iterator;

public class ListaDE<E> implements PositionList<E> {

	protected int tama�o;
	protected NodoDE<E> head;
	protected NodoDE<E> tail;
	
	public ListaDE () {
		tama�o=0;
		head=new NodoDE<E>(null);
		tail=new NodoDE<E>(null,null,head);
		head.setSiguiente(tail);
	}
	
	
	@Override
	public int size() {
		return tama�o;
	}

	@Override
	public boolean isEmpty() {
		return size()==0;
	}

	
	private NodoDE<E> checkPosition(Position<E> p) throws InvalidPositionException{
		try {
			if (p==null) 
				throw new InvalidPositionException ("posici�n nula");
			return (NodoDE<E>) p;
		}
		catch(ClassCastException e) {
			throw new InvalidPositionException ("...");
		}
	}
		
	@Override
	public Position<E> first() throws EmptyListException {
		if (size()==0)
			throw new EmptyListException("lista vac�a");
		return head.getSiguiente();
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if (size()==0)
			throw new EmptyListException("lista vac�a");
		return tail.getAnterior();
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		NodoDE<E> n=checkPosition(p);
		if (n.getSiguiente()==tail)
			throw new BoundaryViolationException("Siguiente al �ltimo");
		return n.getSiguiente();
	}	

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		NodoDE<E> n=checkPosition(p);
		if (n.getAnterior()==head)
			throw new BoundaryViolationException ("Anterior al primero");
		return n.getAnterior();
	}

	@Override
	public void addFirst(E element) {
		NodoDE<E> nuevo= new NodoDE<E>(element,head.getSiguiente(),head);
		head.getSiguiente().setAnterior(nuevo);
		head.setSiguiente(nuevo);
		tama�o++;
	}

	@Override
	public void addLast(E element) {
		NodoDE<E> nuevo= new NodoDE<E>(element,tail,tail.getAnterior());
		tail.getAnterior().setSiguiente(nuevo);
		tail.setAnterior(nuevo);
		tama�o++;
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		NodoDE<E> n=checkPosition(p);
		NodoDE<E> nuevo= new NodoDE<E>(element,n.getSiguiente(),n);
		n.getSiguiente().setAnterior(nuevo);
		n.setSiguiente(nuevo);
		tama�o++;
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		NodoDE<E> n=checkPosition(p);
		NodoDE<E> nuevo= new NodoDE<E>(element,n,n.getAnterior());
		n.getAnterior().setSiguiente(nuevo);
		n.setAnterior(nuevo);
		tama�o++;
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		if (isEmpty()) 
			throw new InvalidPositionException("lista vac�a");
		NodoDE<E> n=checkPosition(p);
		E elem=n.element();
		n.getAnterior().setSiguiente(n.getSiguiente());
		n.getSiguiente().setAnterior(n.getAnterior());
		tama�o--;
		return elem;
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		if (isEmpty())
			throw new InvalidPositionException("lista vac�a");
		NodoDE<E> n=checkPosition(p);
		E elem= n.element();
		n.setElemento(element);
		return elem;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> lista= new ListaDE<Position<E>>();
		if (!this.isEmpty()) {
			Position<E> p;
			try {
				p=head.getSiguiente();
				while(p!=tail.getAnterior()) {
					lista.addLast(p);
					p=next(p);
				}
				lista.addLast(p);
			}
			catch (BoundaryViolationException|InvalidPositionException e) {
				System.out.println(e.getMessage());
			}
		}
		return lista;
	}

}