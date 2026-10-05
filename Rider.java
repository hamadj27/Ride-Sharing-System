public class Rider extends Person implements IRider {
<<<<<<< HEAD


    public String getEmail() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEmail'");
    }


    public void setEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEmail'");
    }


    public String getHomeCity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHomeCity'");
    }


    public void setHomeCity(String homeCity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setHomeCity'");
    }


    public int compareTo(IRider other) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }
=======

    private String email;
    private String homeCity;

    public Rider(int id, String name, String phoneNumber, String email, String homeCity) {
        super(id, name, phoneNumber);
        this.email = email;
        this.homeCity = homeCity;
    }//big o is 1

    @Override
    public String getEmail() {
        return email;
    }//big o is 1

    @Override
    public void setEmail(String email) {
        this.email = email;
    }//big o is 1

    @Override
    public String getHomeCity() {
        return homeCity;
    }//big o is 1

    @Override
    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }//big o is 1

    @Override
    public int compareTo(IRider other) {

            if (this.getId() < other.getId()) {
                return -1;
            } else if (this.getId() > other.getId()) {
                return 1;
            } else {
                return 0;
            }

    }//big o is 1




>>>>>>> models



}