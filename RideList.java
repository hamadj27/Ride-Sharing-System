public class RideList implements IRideList {
    private Node<IRide> head;

    public RideList() {
        head = null;
    }

    @Override
    public boolean addRide(IRide ride) {
        if (ride == null) return false;
        
        Node<IRide> tmp = new Node<IRide>(ride);
        
        // إذا القائمة فاضية
        if (head == null) {
            head = tmp;
            return true;
        }
        
        // إذا كان موقع الانطلاق للرحلة الجديدة أبجدياً قبل موقع الـ head
        // ride.compareTo(head.data) ترجع رقم سالب إذا كانت أبجدياً قبل
        if (ride.compareTo(head.data) < 0) {
            tmp.next = head;
            head = tmp;
            return true;
        }
        
        // البحث عن المكان المناسب أبجدياً
        Node<IRide> current = head;
        while (current.next != null) {
            if (ride.compareTo(current.next.data) < 0) {
                break; // لقينا المكان المناسب
            }
            current = current.next;
        }
        
        tmp.next = current.next;
        current.next = tmp;
        return true;
    }

    @Override
    public boolean removeRideById(int rideId) {
        if (head == null) return false;
        
        if (head.data.getRideId() == rideId) {
            head = head.next;
            return true;
        }
        
        Node<IRide> current = head;
        while (current.next != null) {
            if (current.next.data.getRideId() == rideId) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public LinkedList<IRide> getAllAlphabetically() {
        LinkedList<IRide> result = new LinkedList<IRide>();
        Node<IRide> current = head;
        // القائمة أصلاً مرتبة أبجدياً لأننا ضبطنا الإضافة، فمجرد نمشي عليها ونعبيها
        while (current != null) {
            result.insertLast(current.data);
            current = current.next;
        }
        return result;
    }

    @Override
    public LinkedList<IRide> findByPickupLocation(String pickupLocation) {
        LinkedList<IRide> result = new LinkedList<IRide>();
        Node<IRide> current = head;
        while (current != null) {
            if (current.data.getPickupLocation().equals(pickupLocation)) {
                result.insertLast(current.data);
            }
            current = current.next;
        }
        return result;
    }

    @Override
    public LinkedList<IRide> findByRiderName(String riderFullName) {
        LinkedList<IRide> result = new LinkedList<IRide>();
        Node<IRide> current = head;
        
        while (current != null) {
            IRide ride = current.data;
            boolean matchFound = false;
            
            // إذا كانت الرحلة خاصة (Private)
            if (ride instanceof IPrivateRide) {
                IPrivateRide privateRide = (IPrivateRide) ride;
                if (privateRide.getRider() != null && ((IPerson) privateRide.getRider()).getName().equals(riderFullName)) {
                    matchFound = true;
                }
            } 
            // إذا كانت الرحلة مشتركة (Shared)
            else if (ride instanceof ISharedRide) {
                ISharedRide sharedRide = (ISharedRide) ride;
                // نوصل للـ head حق قائمة الركاب المشتركين
                Node<IRider> riderNode = sharedRide.getParticipants().head; 
                
                // نلف على الركاب المشتركين في هالرحلة
                while (riderNode != null) {
                    if (((IPerson) riderNode.data).getName().equals(riderFullName)) {
                        matchFound = true;
                        break; // لقيناه، ما يحتاج نكمل فحص باقي ركاب هالرحلة
                    }
                    riderNode = riderNode.next;
                }
            }
            
            // إذا لقينا الراكب في هالرحلة، نضيف الرحلة للقائمة
            if (matchFound) {
                result.insertLast(ride);
            }
            
            current = current.next;
        }
        return result;
    }

    @Override
    public int size() {
        int count = 0;
        Node<IRide> current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}