package Principal;

public class Pair<K,V> {
	
	private K key;
	private V value;
	
	public Pair( K k, V val) {
		key=k;
		value=val;
	}

	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}
}
