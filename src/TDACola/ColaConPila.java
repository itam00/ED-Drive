package TDACola;

import TDAPila.EmptyStackException;
import TDAPila.PilaEnlazada;
import TDAPila.Stack;

public class ColaConPila<E> implements Queue<E> {
	Stack<E> pila;
	E fondo;
	E tail;
	
	public ColaConPila() {
		pila= new PilaEnlazada<E>();
		fondo=null;
		tail=null;
	}
	
	@Override
	public int size() {
		return pila.size();
	}

	@Override
	public boolean isEmpty() {
		return pila.isEmpty();
	}

	@Override
	public E front() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("Cola vacía");
		return fondo;
	}

	@Override
	public void enqueue(E element) {
		
		if (isEmpty())
			fondo=element;
		pila.push(element);
		tail=element;
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		E ret;
		Stack<E> pila2= new PilaEnlazada<E>();
		ret=null;
		if (isEmpty())
			throw new EmptyQueueException("Cola vacía");
		try {
		while (size() != 1) {
			pila2.push(pila.pop());
		}
		ret=pila.pop();
		fondo=pila2.pop();
		enqueue(fondo);
		while (!pila2.isEmpty())
			pila.push(pila2.pop());
		} catch (EmptyStackException e)
		{
			System.out.println(e.getMessage());
		}
		
		return ret;
	}
	
}
