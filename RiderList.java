// RiderList.java
public class RiderList implements IRiderList {
    private Node<IRider> head;

    public RiderList() {
        head = null;
    }

    @Override
    public boolean add(IRider rider) {
        if (rider == null) return false;
        
        Node<IRider> tmp = new Node<IRider>(rider);
        
        if (head == null) {
            head = tmp;
            return true;
        }
        
        if (head.getData().getId() == rider.getId()) {
            return false;
        }
        if (head.getData().getId() > rider.getId()) {
            tmp.setNext(head);
            head = tmp;
            return true;
        }
        
        Node<IRider> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().getId() == rider.getId()) {
                return false;
            }
            if (current.getNext().getData().getId() > rider.getId()) {
                break;
            }
            current = current.getNext();
        }
        
        tmp.setNext(current.getNext());
        current.setNext(tmp);
        return true;
    }

    @Override
    public IRider findById(int riderId) {
        Node<IRider> current = head;
        while (current != null) {
            if (current.getData().getId() == riderId) {
                return current.getData();
            }
            if (current.getData().getId() > riderId) {
                break;
            }
            current = current.getNext();
        }
        return null;
    }

    @Override
    public LinkedList<IRider> findByName(String fullName) {
        LinkedList<IRider> result = new LinkedList<IRider>();
        Node<IRider> current = head;
        while (current != null) {
            if (current.getData().getName().equals(fullName)) {
                result.insertLast(current.getData());
            }
            current = current.getNext();
        }
        return result;
    }

    @Override
    public IRider findByEmail(String email) {
        Node<IRider> current = head;
        while (current != null) {
            if (current.getData().getEmail().equals(email)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    @Override
    public LinkedList<IRider> findByHomeCity(String homeCity) {
        LinkedList<IRider> result = new LinkedList<IRider>();
        Node<IRider> current = head;
        while (current != null) {
            if (current.getData().getHomeCity().equals(homeCity)) {
                result.insertLast(current.getData());
            }
            current = current.getNext();
        }
        return result;
    }

    @Override
    public LinkedList<IRider> getAll() {
        LinkedList<IRider> result = new LinkedList<IRider>();
        Node<IRider> current = head;
        while (current != null) {
            result.insertLast(current.getData());
            current = current.getNext();
        }
        return result;
    }

    @Override
    public boolean removeById(int riderId) {
        if (head == null) return false;
        
        if (head.getData().getId() == riderId) {
            head = head.getNext();
            return true;
        }
        
        Node<IRider> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().getId() == riderId) {
                current.setNext(current.getNext().getNext());
                return true;
            }
            if (current.getNext().getData().getId() > riderId) {
                break;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public boolean removeByEmail(String email) {
        if (head == null) return false;
        
        if (head.getData().getEmail().equals(email)) {
            head = head.getNext();
            return true;
        }
        
        Node<IRider> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().getEmail().equals(email)) {
                current.setNext(current.getNext().getNext());
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public int removeByName(String fullName) {
        int count = 0;
        
     
        
        while (head != null && head.getData().getName().equals(fullName)) {
            head = head.getNext();
            count++;
        }
        
        if (head == null) return count;
        
        Node<IRider> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().getName().equals(fullName)) {
                current.setNext(current.getNext().getNext());
                count++;
            } else {
                current = current.getNext();
            }
        }
        return count;
    }

    @Override
    public int removeByHomeCity(String homeCity) {
        int count = 0;
        
        while (head != null && head.getData().getHomeCity().equals(homeCity)) {
            head = head.getNext();
            count++;
        }
        
        if (head == null) return count;
        
        Node<IRider> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().getHomeCity().equals(homeCity)) {
                current.setNext(current.getNext().getNext());
                count++;
            } else {
                current = current.getNext();
            }
        }
        return count;
    }

    @Override
    public int size() {
        int count = 0;
        Node<IRider> current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }
}