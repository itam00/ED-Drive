package TDAPila;

public class AfueraPila<E> {

	public void InvertirPila(Stack<E> p) {
		
		Stack<E> p2= new PilaConArreglo<E>(p.size());
		
		Stack<E> p3= new PilaConArreglo<E>(p.size());
		
		try {
		while(!p.isEmpty()) 
			p2.push(p.pop());
		while(!p2.isEmpty()) 
			p3.push(p2.pop());
		while(!p3.isEmpty()) 
			p.push(p3.pop());
		}
		catch (EmptyStackException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
