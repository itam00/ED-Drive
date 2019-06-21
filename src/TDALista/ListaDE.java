package TDALista;

import java.util.Iterator;
/**
 * Clase ListaDoblementeEnlazada.
 * @author Alan, Nico y Mati.
 *
 * @param <E> Tipo de dato a almacenar en la lista.
 */
public class ListaDE<E> implements PositionList<E> {

	protected int tamaño; //Cantidad de elementos
	protected NodoDE<E> head;
	protected NodoDE<E> tail;
	
	/**
	 * Crea una nueva lista
	 */
	public ListaDE () {
		tamaño=0;
		head=new NodoDE<E>(null);
		tail=new NodoDE<E>(null,null,head);
		head.setSiguiente(tail);
	}
	
	
	@Override
	public int size() {
		return tamaño;
	}

	@Override
	public boolean isEmpty() {
		return size()==0;
	}

	/**
	 * Metodo auxiliar que valida una posicion pasada por parametro.
	 * @param p posicion a validar.
	 * @return nodo correspondiente a la posicion validada.
	 * @throws InvalidPositionException en caso de que la posicion sea invalida.
	 */
	private NodoDE<E> checkPosition(Position<E> p) throws InvalidPositionException{
		try {
			if (p==null) 
				throw new InvalidPositionException ("posicion nula");
			return (NodoDE<E>) p;
		}
		catch(ClassCastException e) {
			throw new InvalidPositionException ("...");
		}
	}
		
	@Override
	public Position<E> first() throws EmptyListException {
		if (size()==0)
			throw new EmptyListException("lista vacia");
		return head.getSiguiente();
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if (size()==0)
			throw new EmptyListException("lista vacia");
		return tail.getAnterior();
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		NodoDE<E> n=checkPosition(p);
		if (n.getSiguiente()==tail)
			throw new BoundaryViolationException("Siguiente al ultimo");
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
		tamaño++;
	}

	@Override
	public void addLast(E element) {
		NodoDE<E> nuevo= new NodoDE<E>(element,tail,tail.getAnterior());
		tail.getAnterior().setSiguiente(nuevo);
		tail.setAnterior(nuevo);
		tamaño++;
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		NodoDE<E> n=checkPosition(p);
		NodoDE<E> nuevo= new NodoDE<E>(element,n.getSiguiente(),n);
		n.getSiguiente().setAnterior(nuevo);
		n.setSiguiente(nuevo);
		tamaño++;
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		NodoDE<E> n=checkPosition(p);
		NodoDE<E> nuevo= new NodoDE<E>(element,n,n.getAnterior());
		n.getAnterior().setSiguiente(nuevo);
		n.setAnterior(nuevo);
		tamaño++;
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		if (isEmpty()) 
			throw new InvalidPositionException("lista vacia");
		NodoDE<E> n=checkPosition(p);
		E elem=n.element();
		n.getAnterior().setSiguiente(n.getSiguiente());
		n.getSiguiente().setAnterior(n.getAnterior());
		n.setElemento(null);
		n.setAnterior(null);
		n.setSiguiente(null);
		tamaño--;
		return elem;
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		if (isEmpty())
			throw new InvalidPositionException("lista vacia");
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
