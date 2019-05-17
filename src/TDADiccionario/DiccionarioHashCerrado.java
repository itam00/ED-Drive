package TDADiccionario;

import TDALista.ListaDE;
import TDALista.PositionList;

public class DiccionarioHashCerrado<K,V> implements Dictionary<K,V>{
	protected Entry<K,V>[] arreglo;
	protected int cant;
	protected int primo;
	
	final Entry<K,V> disponible= new Entrada<K,V>(null,null);
	
	@SuppressWarnings("unchecked")
	public DiccionarioHashCerrado() {
		primo=5519;
		arreglo= (Entry<K,V>[]) new Entry[primo];
		cant=0;
	}
	
	@Override
	public int size() {
		return cant;
	}

	@Override
	public boolean isEmpty() {
		return cant==0;
	}

	protected int checkKey(K key) throws InvalidKeyException {
		if (key==null)
			throw new InvalidKeyException("llave invalida");
		return key.hashCode()%primo;
	}
	@Override
	public Entry<K, V> find(K key) throws InvalidKeyException {
		int indice=checkKey(key);
		int recorridos=0;
		Entry<K,V> toreturn=null;
		while (arreglo[indice]!=null && toreturn==null && recorridos<primo) {
			if (arreglo[indice]!=disponible && arreglo[indice].getKey().equals(key))
				toreturn=arreglo[indice];
			else {
				recorridos++;
				if (indice==primo-1)
					indice=0;
				else
					indice++;
			}
		}
		return toreturn;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		int indice=checkKey(key);
		int recorridos=0;
		PositionList<Entry<K,V>> toreturn= new ListaDE<Entry<K,V>>();
		while (arreglo[indice]!=null && recorridos<primo) {
			if (arreglo[indice]!=disponible && arreglo[indice].getKey().equals(key))
				toreturn.addLast(arreglo[indice]);
			if (indice==primo-1)
				indice=0;
			else
				indice++;
			recorridos++;
		}
		return toreturn;
	}

	private boolean es_primo(int n) {
		boolean toreturn=true;
		for (int i=3;i<Math.sqrt(n) && toreturn;i++)
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
		Entry<K,V>[] aux=arreglo;
		primo=proximo_primo(primo*2);
		arreglo= (Entry<K,V>[]) new Entry[primo];
		cant=0;
		try {
			for (int i=0;i<aux.length;i++)
				insert(aux[i].getKey(),aux[i].getValue());
		}
		catch (InvalidKeyException e) {
		}
	}
	
	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		int indice=checkKey(key);
		if ((float)cant/primo>0.9)
			redimensionar();
		Entry<K,V> toreturn= new Entrada<K,V>(key,value);
		while (arreglo[indice]!=null && arreglo[indice]!=disponible)
			if (indice==primo-1)
				indice=0;
			else
				indice++;
		arreglo[indice]=toreturn;
		cant++;
		return toreturn;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		if (e==null)
			throw new InvalidEntryException("entrada invalida");
		Entry<K,V> toreturn= null;
		try {
			int indice= checkKey(e.getKey());
			int recorridos=0;
			while (arreglo[indice]!=null && toreturn==null && recorridos<primo) {
				if (arreglo[indice]!=disponible && arreglo[indice].equals(e)) {
					toreturn=e;
					arreglo[indice]=disponible;
				}
				else {
					if (indice==primo-1)
						indice=0;
					else
						indice++;
					recorridos++;
				}
			}
		}
		catch (InvalidKeyException x) {
			throw new InvalidEntryException("entrada invalida");
		}
		if (toreturn!=null)
			cant--;
	
		else
			throw new InvalidEntryException("entrada invalida");
		return toreturn;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> toreturn= new ListaDE<Entry<K,V>>();
		for (int i=0;i<primo;i++)
			if (arreglo[i]!=null && arreglo[i]!=disponible)
				toreturn.addLast(arreglo[i]);
		return toreturn;
	}

}
