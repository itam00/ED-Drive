package TDALista;

import java.util.Iterator;

public class Punto3e {

	public static void EjercicioE(PositionList<Integer> L1, PositionList<Integer> L2){
		PositionList<Integer> L3= new ListaSE<Integer>();
		Iterator<Position<Integer>>it1= L1.positions().iterator();
		Iterator<Position<Integer>>it2= L2.positions().iterator();
		Position<Integer> leidol1=null;
		Position<Integer> leidol2=null;

		if (it1.hasNext() && it2.hasNext()){
			leidol1= it1.next();
			leidol2= it2.next();
			while (it1.hasNext() && it2.hasNext()){
				if (leidol1.element()==leidol2.element()){
					L3.addLast(leidol1.element());
					leidol1=it1.next();
					leidol2=it2.next();
				}
				else
					if (leidol1.element()<leidol2.element()){
						L3.addLast(leidol1.element());
						leidol1=it1.next();
					}
					else{
						L3.addLast(leidol2.element());
						leidol2=it2.next();
					}
			}
			if (leidol1.element()==leidol2.element())
				L3.addLast(leidol1.element());
			else
				if (leidol1.element()<leidol2.element()){
					L3.addLast(leidol1.element());
					L3.addLast(leidol2.element());
				}
				else{
					L3.addLast(leidol2.element());
					L3.addLast(leidol1.element());	
				}	
		}

		while (it1.hasNext() && !it2.hasNext()){
			L3.addLast(it1.next().element());
		}

		while (it2.hasNext() && !it1.hasNext()){
			L3.addLast(it2.next().element());
		}

		for (Integer i:L3)
			System.out.print(i);
	}	
	
	
	public static void main (String[] args) {
		PositionList<Integer> l1,l2;
		l1 = new ListaSE<Integer>();
		l2 = new ListaSE<Integer>();
		
		 for (int i=0;i<10;i+=2) {
			l1.addLast(i);
			l2.addLast(i+1);
		}
	
		System.out.print("LISTA 1: ");
		for(Integer i: l1)
			System.out.print(i);
		System.out.println();
		System.out.print("LISTA 2: ");
		for (Integer j: l2)
			System.out.print(j);
		
		System.out.println();
		EjercicioE(l1,l2);
	}
}