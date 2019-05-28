package TDADiccionario;

/**
 * Interfaz Entry
 * @author Mati, Alan y Nico
 * @param <K> Tipo de clave
 * @param <V> Tipo de valor
 */
public interface Entry<K,V> {
	
	/**
	 * Retorna la clave guardada
	 * @return Clave guardada
	 */
	public K getKey();
	
	/**
	 * Retorna el valor guardado 
	 * @return Valor guardado
	 */
	public V getValue();
}
