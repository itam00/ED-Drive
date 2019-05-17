package TDALista;

import java.util.*;

public class ElementIterator<E> implements Iterator<E> {
	protected Position<E> cursor;
	protected PositionList<E> list;
	
	public ElementIterator (PositionList<E> l){
		list=l;
		try {
			if (!l.isEmpty())
				cursor=l.first();
			else
				cursor=null;
		}
		catch (EmptyListException e){
			System.out.println(e.getMessage());
		}
	}
	
	
	public boolean hasNext() {
		return (cursor!=null);
	}

	@Override
	public E next() throws NoSuchElementException {
		if (!hasNext())
			throw new NoSuchElementException("iterable vacio");
		E res= cursor.element();
		try {
			if (cursor!=list.last())
				cursor=list.next(cursor);
			else
				cursor=null;
		}
		catch (EmptyListException|BoundaryViolationException|InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
			
		return res;
	}

}
