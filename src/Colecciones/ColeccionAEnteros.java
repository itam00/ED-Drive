package Colecciones;

public class ColeccionAEnteros implements ColeccionEnteros{
int[] a;
int cant;
	public ColeccionAEnteros(int c) {
		a= new int[c];
		cant=0;
	}
	
	public void insertar(int e) {
		a[cant]=e;
		cant++;
	}
	
	public void eliminar(int e) {
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
	
	public boolean pertenece(int e) {
		boolean pertenece=false;
		for (int i=0;i<cant && !pertenece;i++)
			if (a[i]==e)
				pertenece=true;
		return pertenece;
	}
}
