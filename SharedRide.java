public class SharedRide extends Ride implements ISharedRide {

    private LinkedList<IRider> participants;

    public SharedRide(int rideId, IDriver driver, String pickupLocation,
                      String dropoffLocation, IDateTime pickupTime,
                      IDateTime dropoffTime) {

        super(rideId, driver, pickupLocation, dropoffLocation, pickupTime, dropoffTime);
        this.participants = new LinkedList<IRider>();

    }//big o is 1

    @Override
    public LinkedList<IRider> getParticipants() {
        return participants;
    }//big o is 1





}