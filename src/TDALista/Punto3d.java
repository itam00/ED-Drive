package TDALista;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Punto3d {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void EjercicioD(PositionList<Integer> L1,PositionList<Integer> L2)
	{
		PositionList<Integer> L3= new ListaSE<Integer>();
		Iterator<Integer> it1=null;
		Iterator<Integer> it2=null;
		int leidol1=0;
		int leidol2=0;

		it1= new ElementIterator(L1);
		it2= new ElementIterator(L2);

		try{
			if (it1.hasNext() && it2.hasNext()) {
				leidol1=it1.next();
				leidol2=it2.next();
				while (it1.hasNext() && it2.hasNext()){
					if (leidol1==leidol2){
						L3.addLast(leidol1);
						leidol1=it1.next();
						leidol2=it2.next();
					}
					else
						if (leidol1<leidol2){
							L3.addLast(leidol1);
							leidol1=it1.next();
						}
						else{
							L3.addLast(leidol2);
							leidol2=it2.next();
						}
				}
				if (leidol1==leidol2)
					L3.addLast(leidol1);
				else
					if (leidol1<leidol2) {
						L3.addLast(leidol1);
						L3.addLast(leidol2);
					}
					else {
						L3.addLast(leidol2);
						L3.addLast(leidol1);
					}
			}
		}
		catch (NoSuchElementException e){
			System.out.println(e.getMessage());
		}

		while (it1.hasNext() && !it2.hasNext()){
			leidol1=it1.next();
			L3.addLast(leidol1);
		}

		while (it2.hasNext() && !it1.hasNext()){
			leidol2=it2.next();
			L3.addLast(leidol2);
		}
		
		for (Integer p: L3){
			System.out.print(p);
		}
	}
	
	public static void main (String[] args) {
		PositionList<Integer> l1,l2;
		l1 = new ListaSE<Integer>();
		l2 = new ListaSE<Integer>();
		
		 for (int i=0;i<10;i+=2) {
			l1.addLast(i);
			l2.addLast(i);
		}

		System.out.print("LISTA 1: ");
		for(Integer i: l1)
			System.out.print(i);
		System.out.println();
		System.out.print("LISTA 2: ");
		for (Integer j: l2)
			System.out.print(j);
		
		System.out.println();
		EjercicioD(l1,l2);
	}
}
