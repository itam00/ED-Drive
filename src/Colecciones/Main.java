package Colecciones;

public class Main {
	public static void main (String [] args) {
		ColeccionGenerica<Float> c =new ColeccionGenerica<Float> (5);
		c.insertar(new Float(1.4f));
		c.insertar(new Float (1.5f));
		c.insertar(new Float (1.6f));
		c.insertar(new Float (1.7f));
		c.insertar(new Float (1.8f));
		
		System.out.println (c.cantelementos());
		try {
		if ((c.pertenece(new Float (1.4f))))
			System.out.println("pertenece");
				;
		System.out.println(c.pertenece(new Float(1.2f)));} catch (ElementException e) {
			System.out.println (e.getMessage());
		}
		
		
	}
		
}
