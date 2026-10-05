public abstract class Person implements IPerson {
    private final int id;
    private String name;
    private String phoneNumber;
    private LinkedList<IRide> rideHistory;

    public Person(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        setPhoneNumber(phoneNumber);
        this.rideHistory = new LinkedList<IRide>();
    }//big o is 1


    @Override
    public int getId() {
        return id;
    }//big o is 1


    @Override
    public String getName() {
       return name;
    }//big o is 1


    @Override
    public void setName(String name) {
       this.name = name;
    }//big o is 1

    @Override
    public String getPhoneNumber() {
      return phoneNumber;
    } //big o is 1

    @Override
    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone number must be exactly 10 digits.");
        }
        this.phoneNumber = phoneNumber;
    }//big o is 1

    @Override
    public LinkedList<IRide> getRideHistory() {
       return rideHistory;
    }//big o is 1

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Phone Number: " + phoneNumber;
    }//big o is 1


}