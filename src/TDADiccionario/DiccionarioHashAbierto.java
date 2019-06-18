package TDADiccionario;

import java.util.Iterator;
import TDALista.*;

/**
 * Clase DiccionarioHashAbierto
 * @author mati, nico y alan
 *
 * @param <K> Tipo de dato de las claves
 * @param <V> Tipo de dato de los valores
 */
public class DiccionarioHashAbierto<K,V> implements Dictionary<K,V> {

	protected PositionList<Entry<K,V>>[] arreglo; //Arreglo donde se guardaran las entradas
	protected int cant; // Cantidad de elementos
	protected int primo; //Cantidad maxima de elementos del diccionario
	
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
	 * Metodo que valida la clave pasada por parametro.
	 * @param key clave a validar.
	 * @return codigo hash de la clave validada.
	 * @throws InvalidKeyException si la clave es nula.
	 */
	private int checkKey (K key) throws InvalidKeyException {
		if (key==null)
			throw new InvalidKeyException("clave invalida");
		return getHashCode(key);
	}
	/**
	 * Metodo auxiliar que calcula el lugar donde ira una entrada con dicha clave.
	 * @param k clave sobre la cual se calculara el indice.
	 * @return indice donde ira la entrada con dicha clave.
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

	private boolean es_primo(int n) {
		boolean toreturn=true;
		for (int i=3;i<Math.sqrt(n) && toreturn;i+=2)
			toreturn= (n%i!=0);
		return toreturn;
	}
	
	private int proximo_primo(int n) {
		n++;
		while (!es_primo(n))
			n+=2;
		return n;
	}
	
	
	
	@SuppressWarnings("unchecked")
	protected void redimensionar() {
		PositionList<Entry<K,V>>[] aux;
		primo=proximo_primo(primo*2);
		aux=(ListaDE<Entry<K,V>>[])new ListaDE[primo];
		for(int i=0;i<aux.length;i++)
			aux[i] = new ListaDE<Entry<K,V>>();
		for (int i=0; i<arreglo.length;i++) {
				for (Entry<K,V> e:arreglo[i]) {
						aux[getHashCode(e.getKey())].addLast(e);
				}
		}
		arreglo = aux;
	}
	
	
	@Override
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException {
		if ((float)cant/primo>0.9)
			redimensionar();
		int indice=checkKey(key);
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
