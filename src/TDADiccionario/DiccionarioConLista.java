package TDADiccionario;

import java.util.Iterator;

import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.ListaDE;
import TDALista.Position;
import TDALista.PositionList;

public class DiccionarioConLista<K,V> implements Dictionary<K,V> {

	protected PositionList<Entry<K,V>> lista;
	
	public DiccionarioConLista() {
		lista= new ListaDE<Entry<K,V>>();
	}
	
	@Override
	public int size() {
		return lista.size();
	}

	@Override
	public boolean isEmpty() {
		return lista.isEmpty();
	}

	protected void checkKey(K key) throws InvalidKeyException{
		if (key==null)
			throw new InvalidKeyException ("clave invalida");
	}
	
	@Override
	public Entry<K, V> find(K key) throws InvalidKeyException {
		checkKey(key);
		Entry<K,V> toreturn=null;
		boolean encontre=false;
		Iterator<Entry<K,V>> it= lista.iterator();
		while (it.hasNext() && !encontre) {
			toreturn=it.next();
			if (toreturn.getKey().equals(key))
				encontre=true;
		}
		return toreturn;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		checkKey(key);
		PositionList<Entry<K,V>> toreturn= new ListaDE<Entry<K,V>>();
		Iterator<Entry<K,V>> it= lista.iterator();
		Entry<K,V> e;
		while (it.hasNext()) {
			e=it.next();
			if (e.getKey().equals(key))
				toreturn.addLast(e);
		}
		return toreturn;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		checkKey(key);
		Entry<K,V> toreturn= new Entrada<K,V>(key,value);
		lista.addLast(toreturn);
		return toreturn;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		if (e==null)
			throw new InvalidEntryException ("entrada invalida");
		Entry<K,V> toreturn=null;
		boolean removi=false;
		try {
			checkKey(e.getKey());
			Position<Entry<K,V>> posicion;
			if (!lista.isEmpty()) {
				posicion=lista.first();
				while (posicion!=lista.last() && !removi) 
					if (posicion.element().equals(e)) {
						removi=true;
						lista.remove(posicion);
					}
					else
						posicion=lista.next(posicion);
				if ( !removi && posicion.element().equals(e)) {
					removi=true;
					lista.remove(posicion);
				}
			}
		}
		catch (InvalidKeyException | EmptyListException | BoundaryViolationException | InvalidPositionException x) {
			throw new InvalidEntryException ("entrada invalida");
		}
		if (!removi)
			throw new InvalidEntryException ("entrada invalida");
		return toreturn;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		return lista;
	}

}
