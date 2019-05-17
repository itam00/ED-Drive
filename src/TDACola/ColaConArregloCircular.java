package TDACola;

@SuppressWarnings("unchecked")
public class ColaConArregloCircular<E> implements Queue<E> {

	protected int f,t;
	protected E[] A;
	protected int tamaño;
	
	public ColaConArregloCircular (int max){
		A= (E[]) new Object[max];
		f=0;
		t=0;
		tamaño=0;
	}
	
	public int size() {
		return ((A.length-f+t) % A.length);
	}

	public boolean isEmpty() {
		return tamaño==0;
	}

	public E front() throws EmptyQueueException {
		if (tamaño==0)
			throw new EmptyQueueException("Cola vacía");
		return A[f];
	}

	
	private void agrandar() {
		E[] aux= (E[]) new Object[tamaño*2];
		int i=0;
		int j=f;
		while (i<tamaño) {
			aux[i]=A[j];
			i++;
			j=((j+1)%A.length);
		}
		f=0;
		t=A.length;
		A=aux;
	}
	
	public void enqueue(E element) {
		if (tamaño==A.length) {
			agrandar();
		}
			
		A[t]=element;
		t=((t+1)%A.length);
		tamaño++;
		
	}

	public E dequeue() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("Cola vacía");
		E pri=A[f];
		A[f]=null;
		f=((f+1) % A.length);
		tamaño--;
		return pri;
	}
	
}
