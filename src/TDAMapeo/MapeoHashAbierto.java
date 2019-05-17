package TDAMapeo;

import TDALista.*;

public class MapeoHashAbierto <K,V> implements Map<K,V> {

	protected PositionList<Entrada<K,V>> [] arreglo;
	protected int cant;
	protected int primo;
	
	@SuppressWarnings("unchecked")
	public MapeoHashAbierto() {
		arreglo= (ListaDE<Entrada<K,V>>[]) new ListaDE[5519];
		for (int i=0; i<5519;i++)
			arreglo[i]= new ListaDE<Entrada<K,V>>();
		cant=0;
		primo=5519;
	}
	
	protected int hash(K key) {
		return Math.abs(key.hashCode()) % primo;
	}
	
	private boolean esPrimo(int n) {
		boolean es=true;
		int d;
		if (n<2)
			es=false;
		else {
			d=3;
			while (es && d<=Math.sqrt(n))
				if(n%d==0)
					es=false;
				else
					d++;
		}
		return es;
	}
		
	private int proximo_primo(int c) {
		if (c % 2 ==0)
			c=c+1;
		else
			c=c+2;
		while (!esPrimo(c))
			c+=2;
		return c;
	}
	
	@SuppressWarnings("unchecked")
	protected void redimensionar() {
		primo= proximo_primo(cant*2);
		PositionList<Entrada<K,V>>[] aux = arreglo;
		arreglo= (PositionList<Entrada<K, V>>[]) new ListaDE[primo];
		for (int i=0; i<primo;i++)
			arreglo[i]= new ListaDE<Entrada<K,V>>();
		for (int i=0; i<aux.length;i++)
			for (Entrada<K,V> e: aux[i]) {
				arreglo[hash(e.getKey())].addLast(e);;
			}
	}
	
	@Override
	public int size() {
		return cant;
	}

	@Override
	public boolean isEmpty() {
		return cant==0;
	}

	@Override
	public V get(K key) throws InvalidKeyException {
		if (key==null)
			throw new InvalidKeyException("La clave es invalida");
		V toreturn=null;
		boolean encontre=false;
		int pos=hash(key);
		if (!arreglo[pos].isEmpty()) {
			try {
				Position<Entrada<K,V>> posicion=arreglo[pos].first();
				while (posicion!=arreglo[pos].last() && !encontre) {
					if (posicion.element().getKey().equals(key)) {
						toreturn=posicion.element().getValue();
						encontre=true;
					}
					else
						posicion=arreglo[pos].next(posicion);
				}
				if (posicion.element().getKey().equals(key)){
					toreturn=posicion.element().getValue();
				}
			}
			catch (EmptyListException|BoundaryViolationException|InvalidPositionException e) {
				System.out.println (e.getMessage());
			}
		}
		return toreturn;
	}
	
	@Override
	public V put(K key, V value) throws InvalidKeyException {
		int indice;
		Position<Entrada<K,V>> posicion;
		V toreturn=null;
		boolean esta=false;
		if (key==null)
			throw new InvalidKeyException("clave nula");
		if (((float)cant)/arreglo.length>0.9)
			redimensionar();
		indice=hash(key);
		if (!arreglo[indice].isEmpty()) {
			try {
				posicion=arreglo[indice].first();
				while (posicion!=arreglo[indice].last() && !esta) {
					if (posicion.element().getKey().equals(key)) {
						toreturn=posicion.element().getValue();
						posicion.element().setValue(value);
						esta=true;
					}
					else
						posicion=arreglo[indice].next(posicion);
				}
				if (posicion.element().getKey().equals(key)) {
					toreturn=posicion.element().getValue();
					posicion.element().setValue(value);
					esta=true;
				}
			}
			catch (EmptyListException | InvalidPositionException|BoundaryViolationException e) {
				System.out.println (e.getMessage());
			}
		}
		if (!esta)
			arreglo[indice].addLast(new Entrada<K,V>(key,value));
		cant++;
		return toreturn;
		}

	@Override
	public V remove(K key) throws InvalidKeyException {
		if (key==null)
			throw new InvalidKeyException("clave invalida");
		int indice=hash(key);
		boolean encontre=false;
		V toreturn=null;
		PositionList<Entrada<K,V>> lista=arreglo[indice];
		Position<Entrada<K,V>> posicion;
		if (!lista.isEmpty()) {
			try {
				posicion=lista.first();
				while (posicion!=lista.last() && !encontre) {
					if (posicion.element().getKey().equals(key)) {
						toreturn=lista.remove(posicion).getValue();
						encontre=true;
					}
					else
						posicion=lista.next(posicion);
				}
				if (posicion.element().getKey().equals(key) && !encontre) {
					toreturn=posicion.element().getValue();
					lista.remove(posicion);
					encontre=true;
				}
			}
			catch (EmptyListException|BoundaryViolationException|InvalidPositionException e) {
				System.out.println(e.getMessage());
			}
		}
		if (encontre)
			cant--;
		return toreturn;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> it= new ListaDE<K>();
		for (int i=0; i<arreglo.length;i++) {
			for (Entrada<K,V> e:arreglo[i]) {
				it.addLast(e.getKey());
			}
		}
		return it;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> it= new ListaDE<V>();
		for (int i=0; i<arreglo.length;i++) {
			for (Entrada<K,V> e:arreglo[i]) {
				it.addLast(e.getValue());
			}
		}
		return it;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> it= new ListaDE<Entry<K,V>>();
		for (int i=0; i<arreglo.length;i++) {
			for (Entrada<K,V> e:arreglo[i]) {
				it.addLast(e);
			}
		}
		return it;
	}
}