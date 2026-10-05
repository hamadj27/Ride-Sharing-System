public class RideSharingSystem implements IRideSharingSystem {
    RiderList riderList = new RiderList();
    DriverList driverList = new DriverList();
    RideList rideList = new RideList();

    // Loads riders from a CSV file.
    // Returns true if loading succeeds; false otherwise.
    public boolean loadRidersFromCSV(String ridersFilePath) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadRidersFromCSV'");
    }


    // Loads drivers from a CSV file.
    // Returns true if loading succeeds; false otherwise.
    public boolean loadDriversFromCSV(String driversFilePath) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadDriversFromCSV'");
    }


    // Loads rides from a CSV file. All referenced riders and the assigned driver must already exist. It must enforce conflict rules
    // Returns true if loading succeeds; false otherwise.
    public boolean loadRidesFromCSV(String ridesFilePath) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadRidesFromCSV'");
    }


    //Adds a rider to the system after enforcing uniqueness by ID. Returns true if added; false if duplicate ID or invalid
    public boolean addRider(IRider rider) {
        return riderList.add(rider);
    }


    /**
     * Adds a driver to the system after enforcing uniqueness by ID and by
     * vehicle plate number (no two drivers may share the same plate).
     * Returns true if added; false if duplicate ID, duplicate plate, or invalid
     */
    public boolean addDriver(IDriver driver) {
        if(driverList.findByVehiclePlate(driver.getVehiclePlate()) != null) return false;
        return driverList.add(driver);
    }


    //Searches for a rider by ID.
    public IRider searchRiderById(int riderId) {
        return riderList.findById(riderId);
    }


    //Searches for a rider by email.
    public IRider searchRiderByEmail(String email) {
        return riderList.findByEmail(email);
    }


    //Searches for all riders whose full name exactly matches the given name.
    public LinkedList<IRider> searchRidersByName(String fullName) {
        return riderList.findByName(fullName);
    }


    //Searches for all riders from the specified home city.
    public LinkedList<IRider> searchRidersByHomeCity(String homeCity) {
        return riderList.findByHomeCity(homeCity);
    }


    //Returns all riders stored in the system.
    public LinkedList<IRider> getAllRiders() {
        return riderList.getAll();
    }


    //Searches for a driver by ID.
    public IDriver searchDriverById(int driverId) {
        return driverList.findById(driverId);
    }


    //Searches for a driver by vehicle plate.
    public IDriver searchDriverByVehiclePlate(String vehiclePlate) {
        return driverList.findByVehiclePlate(vehiclePlate);
    }


    //Searches for all drivers with the specified vehicle type.
    public LinkedList<IDriver> searchDriversByVehicleType(VehicleType vehicleType) {
        return driverList.findByVehicleType(vehicleType);
    }


    //Returns all drivers stored in the system.
    public LinkedList<IDriver> getAllDrivers() {
        return driverList.getAll();
    }


    /**
     * Removes a rider and performs cascade deletion of associated rides:
     * - All private rides involving the rider are deleted.
     * - The rider is removed from any shared rides.
     * - Shared rides with no remaining participants are deleted.
     * It returns true if the rider was found and removed; false otherwise
     */
    public boolean removeRider(int riderId) {
        LinkedList<IRide> rides = rideList.getAllAlphabetically();
        if(riderList.removeById(riderId)) {
            // rides.findFirst();
            for (int i = 0; i < rideList.size(); i++) {
                IRide ride = rides.retrieve();
                if (ride.hasRider(riderId)) {
                    if (ride instanceof PrivateRide) {
                        rideList.removeRideById(ride.getRideId());
                    }
                    else if (ride instanceof SharedRide) {
                        ((SharedRide)ride).removeParticipantById(riderId);
                        if (((SharedRide)ride).isEmpty()) rideList.removeRideById(riderId);
                    }

                }
                // rides.findNext();
            }
            return true;
        }
        return false;
    }


    /**
     * Removes a driver and performs cascade deletion of their assigned rides:
     * - All private rides assigned to the driver are deleted.
     * - All shared rides assigned to the driver are deleted.
     * It returns true if the driver was found and removed; false otherwise
     */
    public boolean removeDriver(int driverId) {
        LinkedList<IRide> rides = rideList.getAllAlphabetically();
        if (driverList.removeById(driverId)) {
            // rides.findFirst();
            for(int i = 0; i < rideList.size(); i++) {
                IRide ride = rides.retrieve();
                if (ride.getDriver().getId() == driverId) rideList.removeRideById(ride.getRideId());

                // rides.findNext();
            }
            return true;
        }
        return false;
    }


    /**
     * Schedules a private ride for one rider with one driver.
     * Requirements:
     * - The rider must exist.
     * - The driver must exist.
     * - The ride must not conflict with the rider's current schedule.
     * - The ride must not conflict with the driver's current schedule.
     * It returns true if scheduled; false otherwise
     */
    public boolean schedulePrivateRide(String pickupLocation, IDateTime pickupTime, IDateTime dropoffTime,
            String dropoffLocation, int riderId, int driverId) {
        IRider rider = riderList.findById(riderId);
        IDriver driver = driverList.findById(driverId);

        if (rider == null || driver == null) return false;

        LinkedList<IRide> riderRides = rider.getRideHistory();
        
        // riderRides.findFirst();
        while (riderRides != null && !riderRides.last()) {
            IRide ride = riderRides.retrieve();
            if (ride.getPickupTime().compareTo(dropoffTime) == 0 || ride.getDropoffTime().compareTo(dropoffTime) == 0) return false;
            // riderRides.findNext();
        }

        if (riderRides != null) // for the last ride  
            if (riderRides.retrieve().getPickupTime().compareTo(dropoffTime) == 0 || riderRides.retrieve().getDropoffTime().compareTo(dropoffTime) == 0) 
                return false;
       
        
        
        LinkedList<IRide> driverRides = driver.getRideHistory();
        
        // driverRides.findFirst();
        while (driverRides != null && !driverRides.last()) {
            IRide ride = driverRides.retrieve();
            if (ride.getPickupTime().compareTo(dropoffTime) == 0 || ride.getDropoffTime().compareTo(dropoffTime) == 0) return false;
            // driverRides.findNext();
        }

        if (driverRides != null) // for the last ride  
            if (driverRides.retrieve().getPickupTime().compareTo(dropoffTime) == 0 || driverRides.retrieve().getDropoffTime().compareTo(dropoffTime) == 0)
                return false;

            // return rideList.addRide(new PrivateRide()) 
            return true;
    }


    /**
     * Schedules a shared ride for multiple riders with one driver.
     * Requirements:
     * - All riders must exist.
     * - The driver must exist.
     * - The ride must not conflict with any listed rider's schedule.
     * - The ride must not conflict with the driver's current schedule.
     * It returns true if scheduled; false otherwise
     */
    public boolean scheduleSharedRide(String pickupLocation, IDateTime pickupTime, IDateTime dropoffTime,
            String dropoffLocation, int[] riderIds, int driverId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'scheduleSharedRide'");
    }


    //Searches for all rides whose pickup location matches the given location (may return multiple if pickup locations repeat).
    public LinkedList<IRide> searchRidesByPickupLocation(String pickupLocation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchRidesByPickupLocation'");
    }


    //Searches for all rides that involve a rider with the given name.
    public LinkedList<IRide> searchRidesByRiderName(String riderName) {
        return rideList.findByRiderName(riderName);
    }


    //Returns all riders registered on a shared ride identified by its pickup location.
    public LinkedList<IRider> getSharedRideParticipants(String pickupLocation) {
        // LinkedList<IRide> rides = rideList.findByPickupLocation(pickupLocation);
        return null;
    }


    //Returns all rides (private and shared) alphabetically ordered by pickup location.
    public LinkedList<IRide> getAllRidesAlphabetically() {
        return rideList.getAllAlphabetically();
    }


}