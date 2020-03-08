import java.util.Iterator;

public class GenericList<T> implements GenericListInterface<T>{

	private Node<T> head;      //reference to the first element in the list
	private int numItems;	   // number of items in the list

	public GenericList(){
		numItems = 0;
		head = null;
	}// end default constructor;

	public boolean isEmpty(){
		return (numItems == 0);
	}// end isEmpty

	public int size() {return numItems;}// end size

	private Node<T> find(int pos){
		Node<T> curr = head;
		for (int skip = 1; skip < pos; skip++){
			curr = curr.getNext();
		}
		return curr;
	}//end find

	public T get(int pos) throws ListIndexOutOfBoundsException{
		if (pos >= 1 && pos <= numItems)
		{ Node<T> curr = find(pos);
		  T dataItem = curr.getItem();
		  return dataItem;
		}
		else {throw new ListIndexOutOfBoundsException("position out of range in get");}
	}// end get

	public void add(int pos, T item) throws ListIndexOutOfBoundsException{
		if (pos >=1 && pos <= numItems+1){
			if (pos == 1)
			{ Node<T> newNode = new Node<T>(item);
			  newNode.setNext(head);
			  head = newNode;
			}
			else
			{ Node<T> newNode = new Node<T>(item);
			  Node<T> prev = find(pos-1);
			  newNode.setNext(prev.getNext());
			  prev.setNext(newNode);
			}
			numItems++;
		}
		else { throw new ListIndexOutOfBoundsException("position out of range in add");}
	}// end add

	public void remove(int pos) throws ListIndexOutOfBoundsException{
			if (pos >=1 && pos <= numItems){
				if (pos == 1){ head = head.getNext(); }
				else {
				  Node<T> prev = find(pos-1);
				  Node<T> curr = prev.getNext();
  				  prev.setNext(curr.getNext());
			 	}
				numItems--;
			}
			else {
			 	throw new ListIndexOutOfBoundsException("position out of range in remove");
			}
	}// end remove
	
	//post:  Returns a list iterator object. 
	public Iterator<T> iterator() {
		return new GenericListIterator(head);
	}	
     
    
   	private class GenericListIterator implements Iterator<T> {

		private Node<T> current;
		private Node<T> lastReturned;

		public GenericListIterator(Node<T> node) {
			current = node;
			lastReturned = null;
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		public boolean hasNext() {
			return current != null;
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		public T next() {
			T result = current.getItem();
			lastReturned = current;
			current = current.getNext();
			return result;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
	
}//end GenericList<T>



