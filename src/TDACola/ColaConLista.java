package TDACola;

public class ColaConLista<E> implements Queue<E>{
	
	protected Nodo <E> head,tail;
	protected int tamaño;
	
	public void enqueue(E elem) {
		Nodo<E> nodo= new Nodo<E>(elem); // se crea un nodo que contiene a elem y su siguiente es null.
		if (tamaño==0) 
			head= nodo;
		else
			tail.setSiguiente(nodo);
		tail=nodo;
		tamaño++;
	}

	@Override
	public int size() {
		return tamaño;
	}

	@Override
	public boolean isEmpty() {
		return tamaño==0;
	}

	@Override
	public E front() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("Cola vacía");
		return head.getElemento();
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("Cola vacía");
		E ret= head.getElemento();
		head=head.getSiguiente();
		tamaño--;
		if (tamaño==0)
			tail=null;
		return ret;
	}
	
}
