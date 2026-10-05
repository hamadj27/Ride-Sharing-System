public class SharedRide extends Ride implements ISharedRide {

    private LinkedList<IRider> participants;

    public SharedRide(int rideId, IDriver driver, String pickupLocation,
                      String dropoffLocation, IDateTime pickupTime,
                      IDateTime dropoffTime) {

        super(rideId, driver, pickupLocation, dropoffLocation, pickupTime, dropoffTime);
        this.participants = participants;

    }//big o is 1

    @Override
    public LinkedList<IRider> getParticipants() {  // Returns all riders in this shared ride
        return participants;
    }//big o is 1


    @Override
    public boolean addParticipant(IRider rider) { // Add a rider to the end of the participants list
        if (rider == null) {
            return false;
        }//big o is 1

        participants.insertLast(rider); //From linkedlist.java
        return true;
    }

    @Override
    public boolean removeParticipantById(int riderId) {

        if (participants.empty()) {
            return false;
        }

        participants.findFirst();

        while (participants.current != null) {

            if (participants.retrieve().getId() == riderId) {
                participants.remove();
                return true;
            }

            participants.findNext();
        }

        return false;
    }//big o is n

@Override
public boolean isEmpty() { // Checks if there are no riders in the shared ride
    return participants.empty();
}//big o is 1

    @Override
    public boolean hasRider(int riderId) {  // Checks if a rider with the given ID is in the shared ride

        if (participants.empty()) {
            return false;
        }

        participants.current = participants.head;

        while (participants.current != null) {

            if (participants.retrieve().getId() == riderId) {
                return true;
            }

            participants.current = participants.current.next;
        }

        return false;
    }//big o is n




}