package TDALista;

public class punto4 {
//Asumo que las listas tienen los enteros ordenados de menor a mayor (first es el menor y last es el mayor).

public static PositionList<Integer> intercalaOrdenado (PositionList<Integer> l1, PositionList<Integer> l2){
	int leidol1,leidol2;
	PositionList<Integer> l3= new ListaSE<Integer>();
	Position<Integer> pl1=null;
	Position<Integer> pl2=null;
	Position<Integer> ultimol1=null;
	Position<Integer> ultimol2=null;
	
	boolean vaciol1=false;
	boolean vaciol2=false;
	try{
		pl1=l1.first();
		ultimol1=l1.last();
	}
	catch (EmptyListException e){
		System.out.println(e.getMessage());
		vaciol1=true;
	}
	try{
		pl2=l2.first();
		ultimol2=l2.last();
	}
	catch (EmptyListException e){
		System.out.println(e.getMessage());
		vaciol2=true;
	}
	if (!vaciol1 && !vaciol2)
		while (pl1!=ultimol1 && pl2!=ultimol2){
			leidol1=pl1.element();
			leidol2=pl2.element();
			try{
				if (leidol1<leidol2){
					l3.addLast(leidol1);
					pl1=l1.next(pl1);
				}
				else
					if (leidol2<leidol1){
						l3.addLast(leidol2);
						pl2=l2.next(pl2);
					}
					else{
						l3.addLast(leidol2);
							pl1=l1.next(pl1);
							pl2=l2.next(pl2);
					}
				}
			catch (InvalidPositionException|BoundaryViolationException e){
				System.out.println (e.getMessage());
			}
		}
	try{
	if (!vaciol1 && vaciol2)	
		while (pl1!=ultimol1){
			leidol1=pl1.element();
			l3.addLast(leidol1);
			pl1=l1.next(pl1);
		}
	if(!vaciol2 && vaciol1)
		while (pl2 !=ultimol2){
			leidol2=pl2.element();
			l3.addLast(leidol2);
			pl2=l2.next(pl2);
		}
	}
	catch (InvalidPositionException|BoundaryViolationException e){
		System.out.println(e.getMessage());
	}

	if (!vaciol1 && !vaciol2){
		leidol2=pl2.element();
		leidol1=pl1.element();
		if (leidol1<leidol2){
			l3.addLast(leidol1);
			l3.addLast(leidol2);
		}
		else
			if (leidol1>leidol2){
				l3.addLast(leidol2);
				l3.addLast(leidol1);
			}
			else
				l3.addLast(leidol1);
	}
	else
		if (vaciol1 && !vaciol2)
			l3.addLast(pl2.element());
		else if (vaciol2 && !vaciol1)
			l3.addLast(pl1.element());
	return l3;
} 














	public static void main(String[] args) {
		PositionList<Integer> l1,l2,n;
		l1 = new ListaSE<Integer>();
		l2 = new ListaSE<Integer>();
		
		for (int i=0;i<10;i+=2) {
			l1.addLast(i+1);
			l2.addLast(i+1);
		}
		
		n = intercalaOrdenado(l1,l2);
		
		for (int e: n ) {
			System.out.println(e);
		}
	}
}