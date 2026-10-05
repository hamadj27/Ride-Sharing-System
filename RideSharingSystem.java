import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class RideSharingSystem implements IRideSharingSystem {
    private RiderList riderList = new RiderList();
    private DriverList driverList = new DriverList();
    private RideList rideList = new RideList();

    // Loads riders from a CSV file.
    // Returns true if loading succeeds; false otherwise.
    public boolean loadRidersFromCSV(String ridersFilePath) {
        try (BufferedReader bf = new BufferedReader(new FileReader(new File(ridersFilePath)))) {
            while(true) {
                String s = bf.readLine();
                if (s == null) break;
                String[] ss = s.split(",");
                addRider(new Rider(Integer.parseInt(ss[0]), ss[1], ss[3], ss[2], ss[4]));
            }
        }
            catch(Exception e) {
                riderList = new RiderList();
                return false;
            }

        return true;
    }


    // Loads drivers from a CSV file.
    // Returns true if loading succeeds; false otherwise.
    public boolean loadDriversFromCSV(String driversFilePath) {
        try (BufferedReader bf = new BufferedReader(new FileReader(new File(driversFilePath)))) {
            while(true) {
                String s = bf.readLine();
                if (s == null) break;
                String[] ss = s.split(",");
                addDriver(new Driver(Integer.parseInt(ss[0]), ss[1], ss[2], ss[3], VehicleType.valueOf(ss[4])));
            }
        }
            catch(Exception e) {
                driverList = new DriverList();
                return false;
            }

        return true;
    }


    // Loads rides from a CSV file. All referenced riders and the assigned driver must already exist. It must enforce conflict rules
    // Returns true if loading succeeds; false otherwise.
    public boolean loadRidesFromCSV(String ridesFilePath) {
        try (BufferedReader bf = new BufferedReader(new FileReader(new File(ridesFilePath)))) {
            while(true) {
                String s = bf.readLine();
                if (s == null) break;
                String[] ss = s.split(",");
            
                if (ss[0].equals("PRIVATE")) {
                    schedulePrivateRide(ss[1], new DateTime(Integer.parseInt(ss[2].substring(6,10)), Integer.parseInt(ss[2].substring(0,2)), Integer.parseInt(ss[2].substring(3,5)), Integer.parseInt(ss[2].substring(11,13)), Integer.parseInt(ss[2].substring(14,16))), new DateTime(Integer.parseInt(ss[3].substring(6,10)), Integer.parseInt(ss[3].substring(0,2)), Integer.parseInt(ss[3].substring(3,5)), Integer.parseInt(ss[3].substring(11,13)), Integer.parseInt(ss[3].substring(14,16))), ss[4], Integer.parseInt(ss[6]), Integer.parseInt(ss[5]));
                } else if (ss[0].equals("SHARED")) {
                    String[] rdrs = ss[6].split(";");
                    int[] riders = new int[rdrs.length];
                    for (int i = 0; i < rdrs.length; i++) riders[i] = Integer.parseInt(rdrs[i]);
                    scheduleSharedRide(ss[1], new DateTime(Integer.parseInt(ss[2].substring(6,10)), Integer.parseInt(ss[2].substring(0,2)), Integer.parseInt(ss[2].substring(3,5)), Integer.parseInt(ss[2].substring(11,13)), Integer.parseInt(ss[2].substring(14,16))), new DateTime(Integer.parseInt(ss[3].substring(6,10)), Integer.parseInt(ss[3].substring(0,2)), Integer.parseInt(ss[3].substring(3,5)), Integer.parseInt(ss[3].substring(11,13)), Integer.parseInt(ss[3].substring(14,16))), ss[4], riders, Integer.parseInt(ss[5]));
                }
            }
        }
            catch(Exception e) {
                return false;
            }
            return true;
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
            rides.findFirst();
            while(rides.retrieve() != null) {
                IRide ride = rides.retrieve();
                if (ride.hasRider(riderId)) {
                    if (ride instanceof PrivateRide) {
                        rideList.removeRideById(ride.getRideId());
                        LinkedList<IRide> driverHis = ride.getDriver().getRideHistory();
                        driverHis.findFirst();
                        while (driverHis.retrieve() != ride) driverHis.findNext();
                        driverHis.remove();
                    }
                    else if (ride instanceof SharedRide) {
                        ((SharedRide)ride).removeParticipantById(riderId);
                        if (((SharedRide)ride).isEmpty()) {
                            rideList.removeRideById(ride.getRideId());
                            LinkedList<IRide> driverHistory = ride.getDriver().getRideHistory();
                            driverHistory.findFirst();
                            while (driverHistory.retrieve() != ride) driverHistory.findNext();
                            driverHistory.remove();
                        }
                    }

                }
                rides.findNext();
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
            rides.findFirst();
            while(rides.retrieve() != null) {
                IRide ride = rides.retrieve();
                if (ride.getDriver().getId() == driverId) {
                    rideList.removeRideById(ride.getRideId());

                    if (ride instanceof PrivateRide) {
                        LinkedList<IRide> rdHistory = ((PrivateRide)ride).getRider().getRideHistory();
                        rdHistory.findFirst();
                        while (rdHistory.retrieve() != ride) rdHistory.findNext();
                        rdHistory.remove();
                    }
                    else if(ride instanceof SharedRide) {
                        LinkedList<IRider> rdrs = ((SharedRide)ride).getParticipants();
                        rdrs.findFirst();
                        while(rdrs.retrieve() != null) {
                            LinkedList<IRide> rdrHistory = rdrs.retrieve().getRideHistory();

                            rdrHistory.findFirst();
                            while(rdrHistory.retrieve() != ride) rdrHistory.findNext();

                            rdrHistory.remove();

                            rdrs.findNext();
                        }
                    }
                }

                rides.findNext();
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
        if (pickupTime.compareTo(dropoffTime) >= 0) return false;
        IRider rider = riderList.findById(riderId);
        IDriver driver = driverList.findById(driverId);

        if (rider == null || driver == null) return false;

        LinkedList<IRide> riderRides = rider.getRideHistory();
        
        riderRides.findFirst();
        while (riderRides.retrieve() != null) {
            IRide ride = riderRides.retrieve();
            if (ride.getPickupTime().compareTo(dropoffTime) < 0 && ride.getDropoffTime().compareTo(pickupTime) > 0) return false;
            riderRides.findNext();
        }
       
        
        
        LinkedList<IRide> driverRides = driver.getRideHistory();
        
        driverRides.findFirst();
        while (driverRides.retrieve() != null) {
            IRide ride = driverRides.retrieve();
            if (ride.getPickupTime().compareTo(dropoffTime) < 0 && ride.getDropoffTime().compareTo(pickupTime) > 0) return false;
            driverRides.findNext();
        }



        IRide ride = new PrivateRide(Ride.latestRideId++, driver, pickupLocation, dropoffLocation, pickupTime, dropoffTime, rider);
        if(!rideList.addRide(ride)) return false;

        rider.getRideHistory().insert(ride);
        driver.getRideHistory().insert(ride);
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
        if (pickupTime.compareTo(dropoffTime) >= 0) return false;
        LinkedList<IRider> riders = new LinkedList<>();
        for(int i = 0; i < riderIds.length; i++) {
            riders.insert(riderList.findById(riderIds[i]));
            if (riders.retrieve() == null) return false;
        }

        IDriver driver = driverList.findById(driverId);
        if (driver == null) return false;
    

        riders.findFirst();
        for (int i = 0; i < riderIds.length; i++) {
            IRider rider = riders.retrieve(); 
            LinkedList<IRide> riderRides = rider.getRideHistory();
        
            riderRides.findFirst();
            while (riderRides.retrieve() != null) {
                IRide ride = riderRides.retrieve();
                if (ride.getPickupTime().compareTo(dropoffTime) < 0 && ride.getDropoffTime().compareTo(pickupTime) > 0) return false;
                riderRides.findNext();
            }

            riders.findNext();
        }

        LinkedList<IRide> driverRides = driver.getRideHistory();
        
        driverRides.findFirst();
        while (driverRides.retrieve() != null) {
            IRide ride = driverRides.retrieve();
            if (ride.getPickupTime().compareTo(dropoffTime) < 0 && ride.getDropoffTime().compareTo(pickupTime) > 0) return false;
            driverRides.findNext();
        }

            
        IRide ride = new SharedRide(riders, Ride.latestRideId++, driver, pickupLocation, dropoffLocation, pickupTime, dropoffTime);
        if(!rideList.addRide(ride)) return false;
        driver.getRideHistory().insert(ride);
        riders.findFirst();
        while(riders.retrieve() != null) {
            riders.retrieve().getRideHistory().insert(ride);
            riders.findNext();
        }
        return true;
            
    }


    //Searches for all rides whose pickup location matches the given location (may return multiple if pickup locations repeat).
    public LinkedList<IRide> searchRidesByPickupLocation(String pickupLocation) {
        return rideList.findByPickupLocation(pickupLocation);
    }


    //Searches for all rides that involve a rider with the given name.
    public LinkedList<IRide> searchRidesByRiderName(String riderName) {
        return rideList.findByRiderName(riderName);
    }


    //Returns all riders registered on a shared ride identified by its pickup location.
    public LinkedList<IRider> getSharedRideParticipants(String pickupLocation) {
        LinkedList<IRide> rides = rideList.findByPickupLocation(pickupLocation);
        LinkedList<IRider> allRiders = new LinkedList<>();


        rides.findFirst();
        while(rides.retrieve() != null) {
            if (!(rides.retrieve() instanceof SharedRide)) {
                rides.findNext();
                continue;
            }
            LinkedList<IRider> riders = ((SharedRide)rides.retrieve()).getParticipants();
            riders.findFirst();
            while(riders.retrieve() != null) {
                allRiders.insert(riders.retrieve());
                riders.findNext();
            }

            rides.findNext();
        }
            return allRiders;
    }


    //Returns all rides (private and shared) alphabetically ordered by pickup location.
    public LinkedList<IRide> getAllRidesAlphabetically() {
        return rideList.getAllAlphabetically();
    }


}