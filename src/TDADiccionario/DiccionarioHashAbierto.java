package TDADiccionario;

import java.util.Iterator;
import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.ListaDE;
import TDALista.Position;
import TDALista.PositionList;

/**
 * Clase DiccionarioHashAbierto
 * @author mati, nico y alan
 *
 * @param <K> 
 * @param <V>
 */
public class DiccionarioHashAbierto<K,V> implements Dictionary<K,V> {

	protected PositionList<Entry<K,V>>[] arreglo;
	protected int cant;
	protected int primo;
	
	@SuppressWarnings("unchecked")
	/**
	 * Crea un nuevo diccionario
	 */
	public DiccionarioHashAbierto() {
		primo=3;
		cant=0;
		arreglo= (ListaDE<Entry<K,V>>[]) new ListaDE[primo];
		for (int i=0; i<primo;i++)
			arreglo[i]= new ListaDE<Entry<K,V>>();
	}

	@Override
	public int size() {
		return cant;
	}

	@Override
	public boolean isEmpty() {
		return cant==0;
	}

	/**
	 * Valida la clave pasada por parametro.
	 * @param key clave a validar.
	 * @return codigo hash de la clave pasada por parametro.
	 * @throws InvalidKeyException en caso de que la clave no sea valida.
	 */
	private int checkKey (K key) throws InvalidKeyException {
		if (key==null)
			throw new InvalidKeyException("clave invalida");
		return getHashCode(key);
	}
	
	/**
	 * Metodo auxiliar que calcula el codigo hash de una clave pasada por parametro.
	 * @param k clave de la cual se calculará el codigo hash.
	 * @return codigo hash de la clave pasada por parametro.
	 */
	private int getHashCode(K k) {
		return ((Math.abs(k.hashCode()))%primo);
	}
	
	@Override
	public Entry<K, V> find(K key) throws InvalidKeyException {
		int indice=checkKey(key);
		boolean encontre=false;
		Entry<K,V> toreturn=null,actual;
		Iterator<Entry<K,V>> it=arreglo[indice].iterator();
		while (it.hasNext() && !encontre) {
			actual=it.next();
			if (actual.getKey().equals(key)) {
				encontre=true;
				toreturn=actual;
			}
		}
		return toreturn;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		int indice=checkKey(key);
		PositionList<Entry<K,V>> toreturn= new ListaDE<Entry<K,V>>();
		for (Entry <K,V> e: arreglo[indice]) {
			if (e.getKey().equals(key))
				toreturn.addLast(e);
		}
		return toreturn;
	}

	/**
	 * Metodo auxiliar que verifica si un numero n pasado por parametro es primo.
	 * @param n numero del cual se quiere verificar si es primo.
	 * @return true en caso de que n sea primo, false en caso contrario.
	 */
	private boolean es_primo(int n) {
		boolean toreturn=true;
		for (int i=3;i<Math.sqrt(n) && toreturn;i+=2)
			toreturn= (n%i!=0);
		return toreturn;
	}
	
	/**
	 * Metodo auxiliar que calcula el proximo primo mayor a un n pasado por parametro.
	 * @param n numero del cual se calculará el proximo primo
	 * @return proximo primo mayor a n pasado por parametro.
	 */
	private int proximo_primo(int n) {
		int m=n;
		m++;
		while (!es_primo(m))
			m+=2;
		return m;
	}
	
	/**
	 * Metodo auxiliar que amplia el tamaño del arreglo de listas de entry donde se almacenan las entradas.
	 */
	
	@SuppressWarnings("unchecked")
	protected void redimensionar() {
		PositionList<Entry<K,V>>[] aux=arreglo;
		primo=proximo_primo(primo*2);
		arreglo=(ListaDE<Entry<K,V>>[])new ListaDE[primo];
		cant=0;
		for (int i=0; i<aux.length;i++) {
				for (Entry<K,V> e:aux[i]) {
						arreglo[getHashCode(e.getKey())].addLast(e);
				}
		}
	}
	
	@Override
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException {
		int indice=checkKey(key);
		if ((float)cant/primo>0.9)
			redimensionar();
		Entrada<K,V> entrada= new Entrada<K,V>(key,value);
		arreglo[indice].addLast(entrada);
		cant++;
		return entrada;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		if (e==null)
			throw new InvalidEntryException("entrada invalida");
		Entry<K,V> toreturn=null;
		boolean removi=false;
		try {
			Position<Entry<K, V>> pos;
			int indice=checkKey(e.getKey());
			if (!arreglo[indice].isEmpty()) {
				pos=arreglo[indice].first();
				while (pos!=arreglo[indice].last() && !removi) 
					if(pos.element().equals(e)) {
						toreturn=arreglo[indice].remove(pos);
						removi=true;
					}
					else
						pos=arreglo[indice].next(pos);
				if ( !removi && pos.element().equals(e)) {
					toreturn=arreglo[indice].remove(pos);
					removi=true;
				}
			}
		}
		catch (InvalidKeyException | InvalidPositionException | EmptyListException | BoundaryViolationException x) {
			throw new InvalidEntryException("entrada invalida");
		}
		if (!removi)
			throw new InvalidEntryException("La entrada no esta en el diccionario");
		cant--;
		return toreturn;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> toreturn= new ListaDE<Entry<K,V>>();
		for (int i=0; i<arreglo.length;i++)
			for (Entry<K,V> e:arreglo[i])
				toreturn.addLast(e);
		return toreturn;
	}
}
