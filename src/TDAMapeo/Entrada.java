package TDAMapeo;

public class Entrada <K,V> implements Entry<K,V> {

	protected K key; //clave 
	protected V value; //valor
	
	/**
	 * Crea una entrada con una clave y un valor
	 * @param key2 clave a asignar
	 * @param value2 valor a asignar
	 */
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

	/**
	 * Setea la clave
	 * @param k clave a asignar
	 */
	public void setKey(K k) {
		key=k;
	}
	
	/**
	 * Setea el valor
	 * @param val valor a asignar
	 */
	public void setValue(V val) {
		value=val;
	}
	
	/**
	 * Retorna un String 
	 * @return String con la forma (clave, valor)
	 */
	public String toString() {
		return "("+getKey()+","+getValue()+")";
	}
}
