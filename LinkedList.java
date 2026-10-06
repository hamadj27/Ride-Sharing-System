public class LinkedList<T> {
	private Node<T> head;
	private Node<T> current;
	private Node<T> tail;
	
	public LinkedList() {
        head = current = tail = null;
    }
	public boolean empty() {
        return head == null;
    }
	public void findFirst(){
		current = head;
	}
	public void findNext(){
	current = current.getNext();
	
	}
    public boolean last() {
        return current != null && current.getNext() == null;
    }
    public T retrieve() {
    	if(current!=null)return current.getData();
    	return null;
    }
    public Node<T> getHead() {
        return head;
    }
    
    public void update (T data) {
    	current.setData(data);
    }

    public void insert(T data) {
        Node<T> tmp = new Node<T>(data);

        if (empty()) {
            current = head = tail = tmp;
        } else if (current == null) {
            tail.setNext(tmp);
            tail = tmp;
            current = tmp;
        } else {
            tmp.setNext(current.getNext());
            current.setNext(tmp);

            if (current == tail) {
                tail = tmp;
            }

            current = tmp;
        }
    }

    public void remove() {
        if (empty()) {
            return;
        }

        if (current == head) {
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
            current = head;
            return;
        }

        Node<T> prev = head;
        while (prev.getNext() != current) {
            prev = prev.getNext();
        }

        prev.setNext(current.getNext());


        if (current == tail) {
            tail = prev;
            current = head;
        } else {
            current = current.getNext();
        }
    }
    public void insertLast(T val) {
        Node<T> tmp = new Node<T>(val);
        if (empty()) {
            head = current = tail = tmp;
        } else {
            tail.setNext(tmp);
            tail = tmp;
        }
}
}