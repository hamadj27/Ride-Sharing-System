
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
        
        if (head.data.getId() == driver.getId()) {
            return false;
        }
        
        if (head.data.getId() > driver.getId()) {
            tmp.next = head;
            head = tmp;
            return true;
        }
        
        Node<IDriver> current = head;
        while (current.next != null) {
            if (current.next.data.getId() == driver.getId()) {
                return false; 
            }
            if (current.next.data.getId() > driver.getId()) {
                break;
            }
            current = current.next;
        }
        
        tmp.next = current.next;
        current.next = tmp;
        return true;
    }

    @Override
    public IDriver findById(int driverId) {
        Node<IDriver> current = head;
        while (current != null) {
            if (current.data.getId() == driverId) {
                return current.data;
            }
            if (current.data.getId() > driverId) {
                break;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public LinkedList<IDriver> findByName(String fullName) {
        LinkedList<IDriver> result = new LinkedList<IDriver>();
        Node<IDriver> current = head;
        while (current != null) {
            if (current.data.getName().equals(fullName)) {
                result.insertLast(current.data);
            }
            current = current.next;
        }
        return result;
    }

    @Override
    public IDriver findByVehiclePlate(String vehiclePlate) {
        Node<IDriver> current = head;
        while (current != null) {
            if (current.data.getVehiclePlate().equals(vehiclePlate)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public LinkedList<IDriver> findByVehicleType(VehicleType vehicleType) {
        LinkedList<IDriver> result = new LinkedList<IDriver>();
        Node<IDriver> current = head;
        while (current != null) {
            if (current.data.getVehicleType() == vehicleType) {
                result.insertLast(current.data);
            }
            current = current.next;
        }
        return result;
    }

    @Override
    public LinkedList<IDriver> getAll() {
        LinkedList<IDriver> result = new LinkedList<IDriver>();
        Node<IDriver> current = head;
        while (current != null) {
            result.insertLast(current.data);
            current = current.next;
        }
        return result;
    }

    @Override
    public boolean removeById(int driverId) {
        if (head == null) return false;
        
        if (head.data.getId() == driverId) {
            head = head.next;
            return true;
        }
        
        Node<IDriver> current = head;
        while (current.next != null) {
            if (current.next.data.getId() == driverId) {
                current.next = current.next.next;
                return true;
            }
            if (current.next.data.getId() > driverId) {
                break;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean removeByVehiclePlate(String vehiclePlate) {
        if (head == null) return false;
        
        if (head.data.getVehiclePlate().equals(vehiclePlate)) {
            head = head.next;
            return true;
        }
        
        Node<IDriver> current = head;
        while (current.next != null) {
            if (current.next.data.getVehiclePlate().equals(vehiclePlate)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int removeByName(String fullName) {
        int count = 0;
        
        while (head != null && head.data.getName().equals(fullName)) {
            head = head.next;
            count++;
        }
        
        if (head == null) return count;
        
        Node<IDriver> current = head;
        while (current.next != null) {
            if (current.next.data.getName().equals(fullName)) {
                current.next = current.next.next;
                count++;
            } else {
                current = current.next;
            }
        }
        return count;
    }

    @Override
    public int removeByVehicleType(VehicleType vehicleType) {
        int count = 0;
        
        while (head != null && head.data.getVehicleType() == vehicleType) {
            head = head.next;
            count++;
        }
        
        if (head == null) return count;
        
        Node<IDriver> current = head;
        while (current.next != null) {
            if (current.next.data.getVehicleType() == vehicleType) {
                current.next = current.next.next;
                count++;
            } else {
                current = current.next;
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
            current = current.next;
        }
        return count;
    }
}