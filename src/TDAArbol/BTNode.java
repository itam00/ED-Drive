package TDAArbol;

public class BTNode<E> implements BTPosition<E> {

	private E element;
	private BTPosition<E> left, right, parent;
	
	public BTNode(E elem, BTPosition<E> left, BTPosition<E> right, BTPosition<E> parent) {
		element = elem;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
	
	public E element() {
		return element;
	}
	public BTPosition<E> getLeft(){
		return left;
	}
	
	public BTPosition<E> getRight(){
		return right;
	}
	
	public BTPosition<E> getParent(){
		return parent;
	}
	
	public void setElement(E elem) {
		element = elem;
	}
	
	public void setLeft(BTPosition<E> nodo){
		left = nodo;
	}
	
	public void setRight(BTPosition<E> nodo) {
		right = nodo;
	}
	
	public void setParent(BTPosition<E> nodo) {
		parent = nodo;
	}
}
