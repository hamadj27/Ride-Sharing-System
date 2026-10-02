public class RideSharingSystem implements IRideSharingSystem {


    public boolean loadRidersFromCSV(String ridersFilePath) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadRidersFromCSV'");
    }


    public boolean loadDriversFromCSV(String driversFilePath) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadDriversFromCSV'");
    }


    public boolean loadRidesFromCSV(String ridesFilePath) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadRidesFromCSV'");
    }


    public boolean addRider(IRider rider) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addRider'");
    }


    public boolean addDriver(IDriver driver) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addDriver'");
    }


    public IRider searchRiderById(int riderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchRiderById'");
    }


    public IRider searchRiderByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchRiderByEmail'");
    }


    public LinkedList<IRider> searchRidersByName(String fullName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchRidersByName'");
    }


    public LinkedList<IRider> searchRidersByHomeCity(String homeCity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchRidersByHomeCity'");
    }


    public LinkedList<IRider> getAllRiders() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllRiders'");
    }


    public IDriver searchDriverById(int driverId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchDriverById'");
    }


    public IDriver searchDriverByVehiclePlate(String vehiclePlate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchDriverByVehiclePlate'");
    }


    public LinkedList<IDriver> searchDriversByVehicleType(VehicleType vehicleType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchDriversByVehicleType'");
    }


    public LinkedList<IDriver> getAllDrivers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllDrivers'");
    }


    public boolean removeRider(int riderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeRider'");
    }


    public boolean removeDriver(int driverId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeDriver'");
    }


    public boolean schedulePrivateRide(String pickupLocation, IDateTime pickupTime, IDateTime dropoffTime,
            String dropoffLocation, int riderId, int driverId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'schedulePrivateRide'");
    }


    public boolean scheduleSharedRide(String pickupLocation, IDateTime pickupTime, IDateTime dropoffTime,
            String dropoffLocation, int[] riderIds, int driverId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'scheduleSharedRide'");
    }


    public LinkedList<IRide> searchRidesByPickupLocation(String pickupLocation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchRidesByPickupLocation'");
    }


    public LinkedList<IRide> searchRidesByRiderName(String riderName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchRidesByRiderName'");
    }


    public LinkedList<IRider> getSharedRideParticipants(String pickupLocation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSharedRideParticipants'");
    }


    public LinkedList<IRide> getAllRidesAlphabetically() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllRidesAlphabetically'");
    }


}