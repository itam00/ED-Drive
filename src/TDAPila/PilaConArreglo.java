package TDAPila;
/**
 * Clase PilaConArreglo.
 * @author Alan, Nico y Mati.
 *
 * @param <E> Tipo de los elementos a almacenar en la pila.
 */
public class PilaConArreglo<E> implements Stack<E> {

	private int t; //tamaño de la pila
	private E[] A; //arreglo donde se almacenan los elementos
	

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
		return A[t-1];
	}

	@Override
	public void push(E element) {
		if (t==A.length)
			redimensionar();
		A[t]=element;
		t++;
	}

	@Override
	public E pop() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException("esta vacia");
		E e=A[t-1];
		A[t-1]=null;
		t--;
		return e;
	}
	
	/**
	 * Método auxiliar que se ejecuta en caso de que se quiera insertar algo cuando la pila esté llena.
	 * Este metodo aumenta el tamaño de la pila al doble del actual.
	 */
	@SuppressWarnings("unchecked")
	private void redimensionar() {
		E[] aux = A;
		A = (E[]) new Object[t*2];
		for (int i=0; i<aux.length;i++)
			A[i]=aux[i];
	}
}
