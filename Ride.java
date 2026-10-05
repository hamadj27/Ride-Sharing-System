public abstract class Ride implements IRide {

    private final int rideId;
    private IDriver driver;
    private String pickupLocation;
    private String dropoffLocation;
    private IDateTime pickupTime;
    private IDateTime dropoffTime;

    public Ride(int rideId, IDriver driver, String pickupLocation,
                String dropoffLocation, IDateTime pickupTime, IDateTime dropoffTime) {
        this.rideId = rideId;
        this.driver = driver;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.pickupTime = pickupTime;
        this.dropoffTime = dropoffTime;
    }//big o is 1

    @Override
    public int getRideId() {
        return rideId;
    }//big o is 1


    @Override
    public IDriver getDriver() {
        return driver;
    }//big o is 1

    @Override
    public void setDriver(IDriver driver) {
        this.driver = driver;
    }//big o is 1

    @Override
    public String getPickupLocation() {
        return pickupLocation;
    }//big o is 1

    @Override
    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }//big o is 1

    @Override
    public String getDropoffLocation() {
        return dropoffLocation;
    }//big o is 1

    @Override
    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }//big o is 1

    @Override
    public IDateTime getPickupTime() {
        return pickupTime;
    }//big o is 1

    @Override
    public IDateTime getDropoffTime() {
        return dropoffTime;
    }//big o is 1

    @Override
    public abstract boolean hasRider(int riderId);

    @Override
    public int compareTo(IRide other) {
        return this.pickupLocation.compareTo(other.getPickupLocation());
    }//big o is n

    @Override
    public String toString() {
        return "Ride ID: " + rideId +
                ", Pickup Location: " + pickupLocation +
                ", Dropoff Location: " + dropoffLocation +
                ", Pickup Time: " + pickupTime +
                ", Dropoff Time: " + dropoffTime +
                ", Driver: " + driver;
    }







}