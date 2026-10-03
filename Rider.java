public class Rider extends Person implements IRider {

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






}