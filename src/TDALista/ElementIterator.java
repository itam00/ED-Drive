package TDALista;

import java.util.*;

	/**
	 * Clase elementIterator.
	 * Permite la lectura de una lista. 
	 * @author Nico, Mati y Alan.
	 * @param <E> Tipo de dato guardado en la lista.
	 */

public class ElementIterator<E> implements Iterator<E> {
	protected Position<E> cursor; //Apunta a uno a los elementos de la lista
	protected PositionList<E> list; //Lista a recorrer
	
	/**
	 * Contructor de la clase ElementIterator el cual guarda la lista y setea el cursor en el primer elemento de la misma.
	 * @param l Lista a recorrer.
	 */
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
	
	/**
	 * Consulta que se fija si hay un elemento siguiente.
	 * @return Verdadero si tiene siguiente falso en caso contrario.
	 */
	public boolean hasNext() {
		return (cursor!=null);
	}

	
	/**
	 * Retorna el siguiente elemento de la lista y mueve el cursor al siguiente.
	 * @return Elemento de tipo <E> de la lista.
	 * @throw NoSuchElementException Si el iterable esta vacio.
	 */
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
