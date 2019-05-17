package chequearcadena;


import TDACola.ColaConLista;
import TDACola.Queue;

public class Main {
	public static void main (String [] args){
		Queue<Character> cadena= new ColaConLista<Character>(); 
		Chequea ch= new Chequea();
		cadena.enqueue('a');
		cadena.enqueue('b');
		cadena.enqueue('b');
		cadena.enqueue('x');
		cadena.enqueue('b');
		cadena.enqueue('b');
		cadena.enqueue('a');
		cadena.enqueue('a');
		cadena.enqueue('b');
		cadena.enqueue('b');
		
		System.out.println (ch.Chequeador(cadena, 'x'));
		System.out.println(Integer.compare(2,1));
	}
}
