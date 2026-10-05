public class DriverList implements IDriverList {
    private Node<IDriver> head;

    public DriverList() {
        head = null;
    }

    @Override
    public boolean add(IDriver driver) {
        if (driver == null) return false;
        
        Node<IDriver> tmp = new Node<IDriver>(driver);
        
        if (head == null) {
            head = tmp;
            return true;
        }
        if (head.getData().getId() == driver.getId()) {
            return false;
        }
        
        if (head.getData().getId() > driver.getId()) {
            tmp.setNext(head);
            head = tmp;
            return true;
        }
        
        Node<IDriver> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().getId() == driver.getId()) {
                return false; 
            }
            if (current.getNext().getData().getId() > driver.getId()) {
                break;
            }
            current = current.getNext();
        }
        
        tmp.setNext(current.getNext());
        current.setNext(tmp);
        return true;
    }

    @Override
    public IDriver findById(int driverId) {
        Node<IDriver> current = head;
        while (current != null) {
            if (current.getData().getId() == driverId) {
                return current.getData();
            }
            if (current.getData().getId() > driverId) {
                break;
            }
            current = current.getNext();
        }
        return null;
    }

    @Override
    public LinkedList<IDriver> findByName(String fullName) {
        LinkedList<IDriver> result = new LinkedList<IDriver>();
        Node<IDriver> current = head;
        while (current != null) {
            if (current.getData().getName().equals(fullName)) {
                result.insertLast(current.getData());
            }
            current = current.getNext();
        }
        return result;
    }

    @Override
    public IDriver findByVehiclePlate(String vehiclePlate) {
        Node<IDriver> current = head;
        while (current != null) {
            if (current.getData().getVehiclePlate().equals(vehiclePlate)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    @Override
    public LinkedList<IDriver> findByVehicleType(VehicleType vehicleType) {
        LinkedList<IDriver> result = new LinkedList<IDriver>();
        Node<IDriver> current = head;
        while (current != null) {
            if (current.getData().getVehicleType() == vehicleType) {
                result.insertLast(current.getData());
            }
            current = current.getNext();
        }
        return result;
    }

    @Override
    public LinkedList<IDriver> getAll() {
        LinkedList<IDriver> result = new LinkedList<IDriver>();
        Node<IDriver> current = head;
        while (current != null) {
            result.insertLast(current.getData());
            current = current.getNext();
        }
        return result;
    }

    @Override
    public boolean removeById(int driverId) {
        if (head == null) return false;
        
        if (head.getData().getId() == driverId) {
            head = head.getNext();
            return true;
        }
        
        Node<IDriver> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().getId() == driverId) {
                current.setNext(current.getNext().getNext());
                return true;
            }
            if (current.getNext().getData().getId() > driverId) {
                break;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public boolean removeByVehiclePlate(String vehiclePlate) {
        if (head == null) return false;
        
        if (head.getData().getVehiclePlate().equals(vehiclePlate)) {
            head = head.getNext();
            return true;
        }
        
        Node<IDriver> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().getVehiclePlate().equals(vehiclePlate)) {
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
        
        Node<IDriver> current = head;
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
    public int removeByVehicleType(VehicleType vehicleType) {
        int count = 0;
        
        while (head != null && head.getData().getVehicleType() == vehicleType) {
            head = head.getNext();
            count++;
        }
        
        if (head == null) return count;
        
        Node<IDriver> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().getVehicleType() == vehicleType) {
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
        Node<IDriver> current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }
}