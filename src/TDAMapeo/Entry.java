package TDAMapeo;

/**
 * Interfaz Entry que posee un clave y un valor 
 * @author mati, Nico y Alan
 * @param <K> tipo de clave a asignar
 * @param <V> tipo de valor a guardar
 */
public interface Entry<K,V> {
	
	/**
	 * metodo para obtener la clave de la entrada.
	 * @return clave de la entrada.
	 */
	public K getKey();
	
	/**
	 * metodo para obtener el valor de la entrada.
	 * @return valor de la entrada.
	 */
	public V getValue();
}
