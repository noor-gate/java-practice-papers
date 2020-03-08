public class Node<T> {

	private T item;
	private Node<T> next;


	public Node(T newItem) {
		item = newItem;
	}
	
	public  T getItem() {
		return item;
	}

	public  Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> nextNode) {
		this.next = nextNode;
	}
}
