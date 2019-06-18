package TDAPila;

public class PilaConArreglo<E> implements Stack<E> {

	private int t; //tamaño de la pila
	private E[] A; //arreglo donde se almacenan los elementos
	
	@SuppressWarnings("unchecked")
	/**
	 * Crea una nueva pila con max como capacidad
	 * @param max capacidad inicial de la pila
	 */
	public PilaConArreglo(int max) {
		t=0;
		A= (E[]) new Object[max];
	}
	
	/**
	 * Crea una nueva pila
	 */
	public PilaConArreglo() {this(20);}
	
	@Override
	public int size() {
		return t;
	}

	@Override
	public boolean isEmpty() {
		return t==0;
	}

	@Override
	public E top() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException ("esta vacia");
		else
			return A[t];
	}

	@Override
	public void push(E element) {
		A[t]=element;
		t++;
	}

	@Override
	public E pop() throws EmptyStackException {
		E e;
		if (isEmpty())
			throw new EmptyStackException("esta vacia");
		else
		{
			e=A[t];
			A[t]=null;
			t--;
		}
		return e;
	}
	
}
