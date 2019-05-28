package Controlador;

/**
 * Clase Pair
 * @author Nico, Alan y Mati
 * @param <K> Tipo de elemento a usar como clave
 * @param <V> Tipo de elemento a usar como valor
 */
public class Pair<K,V> {
	
	private K key; // Clave almacenada en Pair
	private V value; // Valor almacenado en Pair
	
	/**
	 * Le asigna una clave y un valor
	 * @param k Clave a asignar
	 * @param val Valor a asignar
	 */
	public Pair( K k, V val) {
		key=k;
		value=val;
	}
	
	/**
	 * Asigna una clave
	 * @param k Clave a asignar
	 */
	public void setKey(K k) {
		key = k;
	}
	
	/**
	 * Asigna un valor
	 * @param val Valor a asignar
	 */
	public void setValue(V val) {
		value = val;
	}
	
	/** 
	 * Retorna la clave
	 * @return Clave a retornar
	 */
	public K getKey() {
		return key;
	}
	
	/**
	 * Retorna el valor 
	 * @return Valor a retornar
	 */
	public V getValue() {
		return value;
	}
}
