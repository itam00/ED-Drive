package TDACola;

public class ColaConLista<E> implements Queue<E>{
	
	protected Nodo <E> head,tail;
	protected int tama�o;
	
	public void enqueue(E elem) {
		Nodo<E> nodo= new Nodo<E>(elem); // se crea un nodo que contiene a elem y su siguiente es null.
		if (tama�o==0) 
			head= nodo;
		else
			tail.setSiguiente(nodo);
		tail=nodo;
		tama�o++;
	}

	@Override
	public int size() {
		return tama�o;
	}

	@Override
	public boolean isEmpty() {
		return tama�o==0;
	}

	@Override
	public E front() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("Cola vac�a");
		return head.getElemento();
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("Cola vac�a");
		E ret= head.getElemento();
		head=head.getSiguiente();
		tama�o--;
		if (tama�o==0)
			tail=null;
		return ret;
	}
	
}
