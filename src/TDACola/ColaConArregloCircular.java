package TDACola;

@SuppressWarnings("unchecked")
public class ColaConArregloCircular<E> implements Queue<E> {

	protected int f,t;
	protected E[] A;
	protected int tama�o;
	
	public ColaConArregloCircular (int max){
		A= (E[]) new Object[max];
		f=0;
		t=0;
		tama�o=0;
	}
	
	public int size() {
		return ((A.length-f+t) % A.length);
	}

	public boolean isEmpty() {
		return tama�o==0;
	}

	public E front() throws EmptyQueueException {
		if (tama�o==0)
			throw new EmptyQueueException("Cola vac�a");
		return A[f];
	}

	
	private void agrandar() {
		E[] aux= (E[]) new Object[tama�o*2];
		int i=0;
		int j=f;
		while (i<tama�o) {
			aux[i]=A[j];
			i++;
			j=((j+1)%A.length);
		}
		f=0;
		t=A.length;
		A=aux;
	}
	
	public void enqueue(E element) {
		if (tama�o==A.length) {
			agrandar();
		}
			
		A[t]=element;
		t=((t+1)%A.length);
		tama�o++;
		
	}

	public E dequeue() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("Cola vac�a");
		E pri=A[f];
		A[f]=null;
		f=((f+1) % A.length);
		tama�o--;
		return pri;
	}
	
}
