package Colecciones;

public class ColeccionGenerica<K> implements interfacecolecciongenerica <K> {
	protected K[] a;
	protected int cant;
	
	@SuppressWarnings("unchecked")
	public ColeccionGenerica(int c) {
		a= (K[])new Object[c];
		cant=0;
	}
	
	public void insertar(K e) {
		a[cant]=e;
		cant++;
	}
	
	public void eliminar(K e) {
		boolean eliminado=false;
		for (int i=0; i<cant && !eliminado;i++) {
			if (a[i]==e)
				for (int j=cant;j>i;j--)
					a[j-1]=a[j];
			cant--;
		}
	}

	public int cantelementos() {
		return cant;
	}
	
	public boolean pertenece(K e) throws ElementException {
		boolean pertenece=false;
		for (int i=0;i<cant && !pertenece;i++)
			if (a[i]==e)
				pertenece=true;
		if (!pertenece)
			throw new ElementException ("El elemento no pertenece");
		return pertenece;
	}
}
