

public class LinkedList<T> {
<<<<<<< HEAD
	Node<T> head;
	Node<T> current;
	Node<T> tail ;
	
	public LinkedList() {
=======
    Node<T> head;
    Node<T> current;
    Node<T> tail ;

    public LinkedList() {
>>>>>>> models
        head = current = tail = null;
    }
    public boolean empty() {
        return head == null;
    }
<<<<<<< HEAD
	public void findFirst(){
		current = head;
	}
	public void findNext(){
	current = current.next;
	
	}
=======
    public void findFirst(){
        current = head;
    }
    public void findNext(){
        current = current.next;

    }
>>>>>>> models
    public boolean last() {
        return current != null && current.next == null;
    }
    public T retrieve() {
        if(current!=null)return current.data;
        return null;
    }

    public void update (T data) {
        current.data = data;
    }

    public void insert(T data) {
        Node<T> tmp = new Node<T>(data);
        if (empty()) {
            current = head = tail = tmp;
        } else {
            tmp.next = current.next;
            current.next = tmp;
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
            head = head.next;
            if (head == null) {
                tail = null; // صارت القائمة فاضية
            }
            current = head;
            return;
        }

        Node<T> prev = head;
        while (prev.next != current) {
            prev = prev.next;
        }

        prev.next = current.next;


        if (current == tail) {
            tail = prev;
            current = head;
        } else {
            current = current.next;
        }
    }
    public void insertLast(T val) {
        Node<T> tmp = new Node<T>(val);
        if (empty()) {
            head = current = tail = tmp;
        } else {
            tail.next = tmp;
            tail = tmp;
        }
<<<<<<< HEAD
}
=======
    }
>>>>>>> models
}