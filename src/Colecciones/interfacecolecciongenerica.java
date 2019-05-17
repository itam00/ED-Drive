package Colecciones;

public interface interfacecolecciongenerica<K> {

	public void insertar(K e);
	public void eliminar (K e);
	public int cantelementos();
	public boolean pertenece(K e) throws ElementException;
}
