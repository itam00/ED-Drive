package TDAMapeo;

import java.util.Iterator;

import TDALista.*;

public class MapeoConLista<K,V> implements Map<K,V> {

	protected PositionList<Entrada<K,V>> lista;
	
	public MapeoConLista () {
		lista= new ListaDE<Entrada<K,V>>();
	}
	
	@Override
	public int size() {
		return lista.size();
	}

	@Override
	public boolean isEmpty() {
		return lista.isEmpty();
	}

	@Override
	public V get(K key) throws InvalidKeyException {
		if (key==null)
			throw new InvalidKeyException (" Invalid key");
		for ( Position<Entrada<K,V>> p: lista.positions()) {
			if (p.element().getKey()==key)
				return p.element().getValue();
		}
		return null;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		V aux=null;
		boolean está=false;
		if (key==null)
			throw new InvalidKeyException(" Invalid key");
		for ( Position<Entrada<K,V>> p: lista.positions())
				if (p.element().getKey().equals(key)) {
					aux=p.element().getValue();
					p.element().setValue(value);
					está=true;
				}
		if (!está)
			lista.addLast(new Entrada <K,V>(key,value));
		return aux;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		if (key==null) {
			throw new InvalidKeyException(" Invalid key");
		}
		for ( Position<Entrada<K,V>> p: lista.positions())
				if (p.element().getKey()==key) {
					try {
						V aux= p.element().getValue();
						lista.remove(p);
						return aux;
					}
					catch (InvalidPositionException e) {
						System.out.println (e.getMessage());
					}
				}
		return null;
	}

	@Override
	public Iterable<K> keys() {
		Iterator<Entrada<K,V>> it= lista.iterator();
		PositionList<K> ret= new ListaDE<K>();
		while (it.hasNext()) {
			ret.addLast(it.next().getKey());
		}	
		return ret;
	}

	@Override
	public Iterable<V> values() {
		Iterator<Entrada<K,V>> it= lista.iterator();
		PositionList<V> ret= new ListaDE<V>();
		while (it.hasNext()) {
			ret.addLast(it.next().getValue());
		}
		return ret;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> aux= new ListaDE<Entry<K,V>>();
		Iterator<Entrada<K, V>> it= lista.iterator();
		
		while (it.hasNext())
			aux.addLast(it.next());
		return aux;
	}

}
