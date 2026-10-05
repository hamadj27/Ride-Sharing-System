public class PrivateRide extends Ride implements IPrivateRide {

    private IRider rider;

    public PrivateRide(int rideId, IDriver driver, String pickupLocation,String dropoffLocation, IDateTime pickupTime,
                       IDateTime dropoffTime, IRider rider) {

        super(rideId, driver, pickupLocation, dropoffLocation, pickupTime, dropoffTime);
        this.rider = rider;

    }//big o is 1
    @Override
    public IRider getRider() {
        return rider;
    }//big o is 1

    @Override
    public void setRider(IRider rider) {
        this.rider = rider;
    }//big o is 1

    @Override
    public boolean hasRider(int riderId) {
        return rider != null && rider.getId() == riderId;
    }//big o is 1


}