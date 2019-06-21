package TDAMapeo;

import TDALista.ListaDE;
import TDALista.PositionList;
/**
 * Clase MapeoHashCerrado.
 * @author Alan,Nico y Mati.
 *
 * @param <K> Tipo de las claves del mapeo.
 * @param <V> Tipo de los valores del mapeo.
 */
public class MapeoHashCerrado<K,V> implements Map<K,V> {

	protected Entrada<K,V>[] arreglo; //Arreglo donde se almacenan entradas
	protected int cant; //Cantidad de entradas.
	protected int primo; //tamaño del arreglo.
	
	protected final Entrada<K,V> disponible= new Entrada<K,V>(null,null); // entrada constante que refleja cuando un lugar en el arreglo esta libre.
	
	/**
	 * Constructor de la clase MapeoHashCerrado.
	 * Crea un nuevo mapeo.
	 */
	public MapeoHashCerrado() {
		arreglo=  (Entrada<K,V>[]) new Entrada[5519];
		cant=0;
		primo=5519;
	}
	
	/**
	 * Metodo auxiliar que calcula el codigo hash de la clave pasada por parametro.
	 * @param key clave de la que se calculara el codigo hash.
	 * @return codigo hash de la clave pasada por parametro.
	 */
	protected int hash(K key) {
		return (Math.abs(key.hashCode()) % primo);
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
			throw new InvalidKeyException("clave invalida");
		V toreturn=null;
		int indice= hash(key);
		int recorridos=0;
		boolean encontre=false;
		while (arreglo[indice]!=null && recorridos<primo && !encontre) {
			if (arreglo[indice]!=disponible && arreglo[indice].getKey().equals(key)) {
				encontre=true;
				toreturn=arreglo[indice].getValue();
			}
			else {
				if (indice==(primo-1))
					indice=0;
				else
					indice++;
				recorridos++;
			}
		}
		return toreturn;
	}

	/**
	 * Metodo auxiliar que valida si un entero n pasado por parametro es primo o no.
	 * @param n numero a verificar si es primo o no.
	 * @return true en caso de que n sea primo y false en caso contrario.
	 */
	private boolean es_primo(int n) {
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
	
	/**
	 * Metodo auxiliar que calcula el proximo primo mayor al entero c pasado por parametro.
	 * @param c entero a partir del cual se calculara el proximo primo
	 * @return proximo primo mayor al entero pasado por parametro.
	 */
	private int proximo_primo(int c) {
		int n=c;
		if (n%2==0)
			n++;
		else
			n+=2;
		while (!es_primo(n))
			n+=2;
		return n;
	}
	
	
	@SuppressWarnings("unchecked")
	protected void redimensionar() {
		Entrada<K,V>[] aux= arreglo;
		primo=proximo_primo(primo*2);
		arreglo= (Entrada<K,V>[]) new Entrada[primo];
		cant=0;
		for (int i=0;i<aux.length;i++) {
			if (aux[i]!=null && aux[i]!=disponible)
				try {
					this.put(aux[i].getKey(),aux[i].getValue());
				}
				catch (InvalidKeyException e) {
					System.out.println(e.getMessage());
				}
		}
	}
	
	
	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if (key==null)
			throw new InvalidKeyException("clave invalida");
		if ((((float)cant)/primo) > 0.9)
			redimensionar();
		boolean encontre=false;
		V toreturn=null;
		int indice=hash(key);
		while (arreglo[indice]!=null && !encontre) {
			if (arreglo[indice]!=disponible && arreglo[indice].getKey().equals(key)) {
				encontre=true;
				toreturn=arreglo[indice].getValue();
				arreglo[indice].setValue(value);
			}
			else
				if (indice==primo-1)
					indice=0;
				else
					indice++;
		}
		if (!encontre) {
			indice=hash(key);
			while (arreglo[indice]!=null && arreglo[indice]!=disponible)
				if (indice==primo-1)
					indice=0;
				else
					indice++;
			arreglo[indice]=new Entrada<K,V>(key,value);
			cant++;
		}
		return toreturn;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		if (key==null)
			throw new InvalidKeyException("clave invalida");
		boolean removi=false;
		V toreturn=null;
		int indice= hash(key);
		int recorridos=0;
		while (arreglo[indice]!=null && !removi && recorridos<primo) {
			if (arreglo[indice]!=disponible && arreglo[indice].getKey().equals(key)) {
				toreturn=arreglo[indice].getValue();
				arreglo[indice]=disponible;
				removi=true;
				cant--;
			}
			else {
				if (indice==primo-1)
					indice=0;
				else
					indice++;
				recorridos++;
			}
		}
		return toreturn;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> toreturn= new ListaDE<K>();
		for (int i=0; i<arreglo.length;i++)
			if (arreglo[i] !=null && arreglo[i]!=disponible)
				toreturn.addLast(arreglo[i].getKey());
		return toreturn;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> toreturn= new ListaDE<V>();
		for (int i=0; i<arreglo.length;i++)
			if (arreglo[i]!=null && arreglo[i]!=disponible)
				toreturn.addLast(arreglo[i].getValue());
		return toreturn;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> toreturn= new ListaDE<Entry<K,V>>();
		for (int i=0; i<arreglo.length;i++)
			if (arreglo[i]!=null && arreglo[i]!=disponible)
				toreturn.addLast(arreglo[i]);
		return toreturn;
	}

}
