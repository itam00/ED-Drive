package TDAPila;

public class PilaConArreglo<E> implements Stack<E> {

	private int t;
	private E[] A;
	
	@SuppressWarnings("unchecked")
	public PilaConArreglo(int max) {
		t=0;
		A= (E[]) new Object[max];
	}
	
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
			throw new EmptyStackException ("está vacia");
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
			throw new EmptyStackException("está vacía");
		else
		{
			e=A[t];
			A[t]=null;
			t--;
		}
		return e;
	}
	
	public void invertir() {
		E elem;
		int i=0;
		int j=t;
		while (i!=j && i+1!=j) {
			elem=A[i];
			A[i]=A[j];
			A[j]=elem;
		}	
	}
	
}
