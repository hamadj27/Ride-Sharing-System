public class RideList implements IRideList {
    private Node<IRide> head;

    public RideList() {
        head = null;
    }

    @Override
    public boolean addRide(IRide ride) {
        if (ride == null) return false;
        
        Node<IRide> tmp = new Node<IRide>(ride);
        
        if (head == null) {
            head = tmp;
            return true;
        }

        if (ride.compareTo(head.getData()) < 0) {
            tmp.setNext(head);
            head = tmp;
            return true;
        }
        
        Node<IRide> current = head;
        while (current.getNext() != null) {
            if (ride.compareTo(current.getNext().getData()) < 0) {
                break;
            }
            current = current.getNext();
        }
        
        tmp.setNext(current.getNext());
        current.setNext(tmp);
        return true;
    }

    @Override
    public boolean removeRideById(int rideId) {
        if (head == null) return false;
        
        if (head.getData().getRideId() == rideId) {
            head = head.getNext();
            return true;
        }
        
        Node<IRide> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().getRideId() == rideId) {
                current.setNext(current.getNext().getNext());
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public LinkedList<IRide> getAllAlphabetically() {
        LinkedList<IRide> result = new LinkedList<IRide>();
        Node<IRide> current = head;
        while (current != null) {
            result.insertLast(current.getData());
            current = current.getNext();
        }
        return result;
    }

    @Override
    public LinkedList<IRide> findByPickupLocation(String pickupLocation) {
        LinkedList<IRide> result = new LinkedList<IRide>();
        Node<IRide> current = head;
        while (current != null) {
            if (current.getData().getPickupLocation().equals(pickupLocation)) {
                result.insertLast(current.getData());
            }
            current = current.getNext();
        }
        return result;
    }

    @Override
    public LinkedList<IRide> findByRiderName(String riderFullName) {
        LinkedList<IRide> result = new LinkedList<IRide>();
        Node<IRide> current = head;
        
        while (current != null) {
            IRide ride = current.getData();
            boolean matchFound = false;
            
            if (ride instanceof IPrivateRide) {
                IPrivateRide privateRide = (IPrivateRide) ride;
                if (privateRide.getRider() != null && ((IPerson) privateRide.getRider()).getName().equals(riderFullName)) {
                    matchFound = true;
                }
            } 
            else if (ride instanceof ISharedRide) {
                ISharedRide sharedRide = (ISharedRide) ride;

                Node<IRider> riderNode = sharedRide.getParticipants().getHead(); 
                
                while (riderNode != null) {
                    if (((IPerson) riderNode.getData()).getName().equals(riderFullName)) {
                        matchFound = true;
                        break;
                    }
                    riderNode = riderNode.getNext();
                }
            }
            
            if (matchFound) {
                result.insertLast(ride);
            }
            
            current = current.getNext();
        }
        return result;
    }

    @Override
    public int size() {
        int count = 0;
        Node<IRide> current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }
}