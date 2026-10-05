// RiderList.java
public class RiderList implements IRiderList {
    private Node<IRider> head; // نستخدم الـ Node حقك كبداية للقائمة

    public RiderList() {
        head = null;
    }

    @Override
    public boolean add(IRider rider) {
        if (rider == null) return false;
        
        Node<IRider> tmp = new Node<IRider>(rider);
        
        // إذا كانت القائمة فاضية
        if (head == null) {
            head = tmp;
            return true;
        }
        
        // التحقق من الإضافة في البداية (إذا كان الـ ID أصغر من أول عنصر)
        if (head.data.getId() == rider.getId()) {
            return false; // الـ ID موجود مسبقاً
        }
        if (head.data.getId() > rider.getId()) {
            tmp.next = head;
            head = tmp;
            return true;
        }
        
        // البحث عن المكان المناسب للإدخال المرتب
        Node<IRider> current = head;
        while (current.next != null) {
            if (current.next.data.getId() == rider.getId()) {
                return false; // الـ ID موجود مسبقاً
            }
            if (current.next.data.getId() > rider.getId()) {
                break; // لقينا المكان الصح
            }
            current = current.next;
        }
        
        // ربط النود الجديد
        tmp.next = current.next;
        current.next = tmp;
        return true;
    }

    @Override
    public IRider findById(int riderId) {
        Node<IRider> current = head;
        while (current != null) {
            if (current.data.getId() == riderId) {
                return current.data;
            }
            // عشان القائمة مرتبة، إذا وصلنا لـ ID أكبر، يعني مستحيل نلقاه بعدين
            if (current.data.getId() > riderId) {
                break;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public LinkedList<IRider> findByName(String fullName) {
        LinkedList<IRider> result = new LinkedList<IRider>();
        Node<IRider> current = head;
        while (current != null) {
            // مطابقة الاسم بالكامل حسب المطلوب
            if (current.data.getName().equals(fullName)) {
                result.insertLast(current.data);
            }
            current = current.next;
        }
        return result;
    }

    @Override
    public IRider findByEmail(String email) {
        Node<IRider> current = head;
        while (current != null) {
            if (current.data.getEmail().equals(email)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public LinkedList<IRider> findByHomeCity(String homeCity) {
        LinkedList<IRider> result = new LinkedList<IRider>();
        Node<IRider> current = head;
        while (current != null) {
            if (current.data.getHomeCity().equals(homeCity)) {
                result.insertLast(current.data);
            }
            current = current.next;
        }
        return result;
    }

    @Override
    public LinkedList<IRider> getAll() {
        LinkedList<IRider> result = new LinkedList<IRider>();
        Node<IRider> current = head;
        while (current != null) {
            result.insertLast(current.data);
            current = current.next;
        }
        return result;
    }

    @Override
    public boolean removeById(int riderId) {
        if (head == null) return false;
        
        if (head.data.getId() == riderId) {
            head = head.next;
            return true;
        }
        
        Node<IRider> current = head;
        while (current.next != null) {
            if (current.next.data.getId() == riderId) {
                current.next = current.next.next;
                return true;
            }
            if (current.next.data.getId() > riderId) {
                break;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean removeByEmail(String email) {
        if (head == null) return false;
        
        if (head.data.getEmail().equals(email)) {
            head = head.next;
            return true;
        }
        
        Node<IRider> current = head;
        while (current.next != null) {
            if (current.next.data.getEmail().equals(email)) {
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
        
     
        
        // التعامل مع حالات المطابقة لو كانت في بداية القائمة (ممكن يكون أكثر من واحد ورا بعض)
        while (head != null && head.data.getName().equals(fullName)) {
            head = head.next;
            count++;
        }
        
        if (head == null) return count;
        
        Node<IRider> current = head;
        while (current.next != null) {
            if (current.next.data.getName().equals(fullName)) {
                current.next = current.next.next; // نتخطى النود ونحذفه
                count++;
            } else {
                current = current.next; // نمشي للي بعده بس إذا ما حذفنا
            }
        }
        return count;
    }

    @Override
    public int removeByHomeCity(String homeCity) {
        int count = 0;
        
        while (head != null && head.data.getHomeCity().equals(homeCity)) {
            head = head.next;
            count++;
        }
        
        if (head == null) return count;
        
        Node<IRider> current = head;
        while (current.next != null) {
            if (current.next.data.getHomeCity().equals(homeCity)) {
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
        Node<IRider> current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}