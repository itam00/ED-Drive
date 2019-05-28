package TDAMapeo;

/**
 * Interfaz Entry que posee un clave y un valor 
 * @author mati, Nico y Alan
 * @param <K> tipo de clave a asignar
 * @param <V> tipo de valor a guardar
 */
public interface Entry<K,V> {
	public K getKey();
	public V getValue();
}
