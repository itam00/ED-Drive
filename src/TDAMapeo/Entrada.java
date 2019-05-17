package TDAMapeo;

public class Entrada <K,V> implements Entry<K,V> {

	protected K key;
	protected V value;
	
	public Entrada(K key2, V value2) {
		key=key2;
		value=value2;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	public void setKey(K k) {
		key=k;
	}
	
	public void setValue(V val) {
		value=val;
	}
	
	public String toString() {
		return "("+getKey()+","+getValue()+")";
	}
}
