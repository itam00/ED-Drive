package TDAPila;

public class PilaEnlazada<E> implements Stack<E> {
	
	protected Nodo<E> head; //Guarda la referencia al primer nodo de la lista.
	protected int tama�o; //Guarda la cantidad de elementos en la lista.
	
	public PilaEnlazada (){
		head=null;
		tama�o=0;
	}
	
	public void push(E item) {
		Nodo<E> aux= new Nodo<E>(item);
		aux.setSiguiente(head);
		head=aux;
		tama�o++;
	}

	public boolean isEmpty() {
		return head==null;
	}
	
	public E pop() throws EmptyStackException{
		E elem;
		if (isEmpty())
			throw new EmptyStackException("est� vac�a");
		else
		{
			elem=head.getElemento();
			head=head.getSiguiente();
			tama�o--;
		}
		return elem;
	}
	
	public E top() throws EmptyStackException{
		if (isEmpty())
			throw new EmptyStackException("Pila Vac�a");
		return head.getElemento();
	}

	public int size() {
		return tama�o;
	}
	
}
