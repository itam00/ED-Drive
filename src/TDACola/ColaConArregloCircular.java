package TDACola;
/**
 * Clase ColaConArregloCircular.
 * 
 * @author Alan, Nico y Mati
 *
 * @param <E> Tipo de dato de los elementos a almacenar en la cola
 */

public class ColaConArregloCircular<E> implements Queue<E> {

	protected int f,t; //f: Posicion del proximo elemento a eliminar
					   //t: Posicion del proximo elemento a insertar
	protected E[] A; //Arreglo donde se almacenaran los elementos
	protected int tama�o; //Cantidad de elementos
	
	/**
	 * Crea un nueva cola con capacidad max, que se puede ampliar
	 * @param max capacidad con la que se creara la cola
	 */
	public ColaConArregloCircular (int max){
		A= (E[]) new Object[max];
		f=0;
		t=0;
		tama�o=0;
	}
	
	/**
	 * Crea una nueva cola
	 */
	public ColaConArregloCircular() {this(20);}
	
	public int size() {
		return ((A.length-f+t) % A.length);
	}

	public boolean isEmpty() {
		return tama�o==0;
	}

	public E front() throws EmptyQueueException {
		if (tama�o==0)
			throw new EmptyQueueException("Cola vacia");
		return A[f];
	}

	/**
	 * Metodo auxiliar del metodo enqueue el cual agrandara la cola de ser necesario
	 */
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
			throw new EmptyQueueException("Cola vacia");
		E pri=A[f];
		A[f]=null;
		f=((f+1) % A.length);
		tama�o--;
		return pri;
	}
	
}
