package TDALista;

public class Punto5{
	
	public static <E> void Invertir(PositionList<E> L){
		PositionList<E> aux= new ListaSE<E>();

		try{
			while (!L.isEmpty()) {
				aux.addLast(L.remove(L.first()));
			}
			while (!aux.isEmpty()){
				L.addFirst(aux.remove(aux.first()));
			}
		}
		catch (EmptyListException | InvalidPositionException e){
			System.out.println (e.getMessage());
		}
	}
	
	public static <E> void InvertirRec(PositionList<E> L){
		if (!L.isEmpty())
			try{
				E elem;
				elem=L.remove(L.first());
				InvertirRec(L);
				L.addLast(elem);
			}
			catch (EmptyListException | InvalidPositionException e){
				System.out.println(e.getMessage());
			}
	}
	
	public static void main (String[] args) {
		PositionList<Integer> l= new ListaSE<Integer>();
		for (int i=10; i>0;i--) {
			l.addFirst(i);
		}
		for (Integer i:l)
			System.out.print(i);
		System.out.println();
		InvertirRec(l);
		
		for (Integer i:l)
			System.out.print(i);
	}
}