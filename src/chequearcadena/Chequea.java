package chequearcadena;

import TDACola.Queue;
import TDACola.ColaConLista;
import TDACola.EmptyQueueException;
import TDAPila.PilaEnlazada;
import TDAPila.Stack;
import TDAPila.EmptyStackException;

public class Chequea {

	public boolean Chequeador(Queue<Character> cadena, char X) {
		boolean cumple,termino_a;
		char c,c_pila,c_cola;
		Stack<Character> pila= new PilaEnlazada <Character>();
		Queue<Character> cola= new ColaConLista<Character>();
		cumple= !cadena.isEmpty();
		while (cumple && !cadena.isEmpty())
		{
			termino_a=false;
			while (cumple && !termino_a)
			{
				if (!cadena.isEmpty())
				{
					try {
						c=cadena.dequeue();
						if (c==X)
							termino_a=true;
						else {
							pila.push(c);
							cola.enqueue(c);
						}
					}
					catch (EmptyQueueException e){
						System.out.println(e.getMessage());
					}
				}
				else 
					cumple=false;
			}
			while (cumple && !pila.isEmpty()) {
				if (!cadena.isEmpty()) {
					try {
						c_pila=pila.pop();
						if (c_pila!=cadena.dequeue())
							cumple=false;
					}
					catch(EmptyStackException e) {
						System.out.println(e.getMessage());
					}
					catch (EmptyQueueException e) {
						System.out.println (e.getMessage());
					}
				}
				else
					cumple=false;
			}
			while (cumple && !cola.isEmpty()) {
				if (!cadena.isEmpty())
					try {
						c_cola=cola.dequeue();
						if (c_cola!= cadena.dequeue())
							cumple=false;
					}
					catch (EmptyQueueException e) {
						System.out.println(e.getMessage());
					}
				else
					cumple=false;
			}
		}
		return cumple && cadena.isEmpty();
	}
	

}