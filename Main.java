import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        RideSharingSystem system = new RideSharingSystem();
        if (!system.loadRidersFromCSV("riders_100.csv")) { //load riders
            System.out.println("Failed to load riders.");
        }

        if (!system.loadDriversFromCSV("drivers_30.csv")) {//load drivers
            System.out.println("Failed to load drivers.");
        }

        if (!system.loadRidesFromCSV("rides_40.csv")) {//load rides
            System.out.println("Failed to load rides.");
        }

        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Ride Sharing System =====");
            System.out.println("1. List all riders");
            System.out.println("2. Search for riders by home city");
            System.out.println("3. Search for riders by name");
            System.out.println("4. Add a rider");
            System.out.println("5. List all drivers");
            System.out.println("6. Search for drivers by vehicle type");
            System.out.println("7. Add a driver");
            System.out.println("8. List all rides alphabetically by pickup location");
            System.out.println("9. Search for rides by pickup location");
            System.out.println("10. Search for rides by rider name");
            System.out.println("11. List the participants of a shared ride by pickup location");
            System.out.println("12. Add a ride");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                LinkedList<IRider> riders = system.getAllRiders();

                if (riders.empty()) {
                    System.out.println("No riders found.");
                } else {
                    riders.findFirst();

                    while (riders.retrieve() != null) {
                        System.out.println(riders.retrieve());
                        riders.findNext();
                    }
                }
            }

            else if (choice == 2) {
                System.out.print("Enter home city: ");
                String city = input.nextLine();

                LinkedList<IRider> riders = system.searchRidersByHomeCity(city);

                if (riders.empty()) {
                    System.out.println("No riders found.");
                } else {
                    riders.findFirst();

                    while (riders.retrieve() != null) {
                        System.out.println(riders.retrieve());
                        riders.findNext();
                    }
                }
            }

            else if (choice == 3) {
                System.out.print("Enter rider name: ");
                String name = input.nextLine();

                LinkedList<IRider> riders = system.searchRidersByName(name);

                if (riders.empty()) {
                    System.out.println("No riders found.");
                } else {
                    riders.findFirst();

                    while (riders.retrieve() != null) {
                        System.out.println(riders.retrieve());
                        riders.findNext();
                    }
                }
            }

            else if (choice == 4) {
                System.out.print("Enter rider ID: ");
                int id = input.nextInt();
                input.nextLine();

                System.out.print("Enter name: ");
                String name = input.nextLine();

                System.out.print("Enter phone number: ");
                String phone = input.nextLine();

                System.out.print("Enter email: ");
                String email = input.nextLine();

                System.out.print("Enter home city: ");
                String city = input.nextLine();

                Rider rider = new Rider(id, name, phone, email, city);

                if (system.addRider(rider)) {
                    System.out.println("Rider added successfully.");
                } else {
                    System.out.println("Failed to add rider.");
                }
            }

            else if (choice == 5) {
                LinkedList<IDriver> drivers = system.getAllDrivers();

                if (drivers.empty()) {
                    System.out.println("No drivers found.");
                } else {
                    drivers.findFirst();

                    while (drivers.retrieve() != null) {
                        System.out.println(drivers.retrieve());
                        drivers.findNext();
                    }
                }
            }
            else if (choice == 6) {
                System.out.print("Enter vehicle type (SEDAN, LUXURY_SEDAN, SUV, VAN): ");
                VehicleType type = VehicleType.valueOf(input.nextLine().toUpperCase());

                LinkedList<IDriver> drivers = system.searchDriversByVehicleType(type);

                if (drivers.empty()) {
                    System.out.println("No drivers found.");
                } else {
                    drivers.findFirst();

                    while (drivers.retrieve() != null) {
                        System.out.println(drivers.retrieve());
                        drivers.findNext();
                    }
                }
            }

            else if (choice == 7) {
                System.out.print("Enter driver ID: ");
                int id = input.nextInt();
                input.nextLine();

                System.out.print("Enter name: ");
                String name = input.nextLine();

                System.out.print("Enter phone number: ");
                String phone = input.nextLine();

                System.out.print("Enter vehicle plate (XXX0000): ");
                String plate = input.nextLine();

                System.out.print("Enter vehicle type (SEDAN, LUXURY_SEDAN, SUV, VAN): ");
                VehicleType type = VehicleType.valueOf(input.nextLine().toUpperCase());

                Driver driver = new Driver(id, name, phone, plate, type);

                if (system.addDriver(driver)) {
                    System.out.println("Driver added successfully.");
                } else {
                    System.out.println("Failed to add driver.");
                }
            }

            else if (choice == 8) {
                LinkedList<IRide> rides = system.getAllRidesAlphabetically();

                if (rides.empty()) {
                    System.out.println("No rides found.");
                } else {
                    rides.findFirst();

                    while (rides.retrieve() != null) {
                        System.out.println(rides.retrieve());
                        rides.findNext();
                    }
                }
            }

            else if (choice == 9) {
                System.out.print("Enter pickup location: ");
                String location = input.nextLine();

                LinkedList<IRide> rides = system.searchRidesByPickupLocation(location);

                if (rides.empty()) {
                    System.out.println("No rides found.");
                } else {
                    rides.findFirst();

                    while (rides.retrieve() != null) {
                        System.out.println(rides.retrieve());
                        rides.findNext();
                    }
                }
            }

            else if (choice == 10) {
                System.out.print("Enter rider name: ");
                String name = input.nextLine();

                LinkedList<IRide> rides = system.searchRidesByRiderName(name);

                if (rides.empty()) {
                    System.out.println("No rides found.");
                } else {
                    rides.findFirst();

                    while (rides.retrieve() != null) {
                        System.out.println(rides.retrieve());
                        rides.findNext();
                    }
                }
            }

            else if (choice == 11) {
                System.out.print("Enter pickup location: ");
                String location = input.nextLine();

                LinkedList<IRider> riders = system.getSharedRideParticipants(location);

                if (riders.empty()) {
                    System.out.println("No participants found.");
                } else {
                    riders.findFirst();

                    while (riders.retrieve() != null) {
                        System.out.println(riders.retrieve());
                        riders.findNext();
                    }
                }
            }

            else if (choice == 12) {
                System.out.print("Enter ride type (PRIVATE or SHARED): ");
                String type = input.nextLine().toUpperCase();

                System.out.print("Enter pickup location: ");
                String pickupLocation = input.nextLine();

                System.out.print("Enter pickup year: ");
                int pickupYear = input.nextInt();

                System.out.print("Enter pickup month: ");
                int pickupMonth = input.nextInt();

                System.out.print("Enter pickup day: ");
                int pickupDay = input.nextInt();

                System.out.print("Enter pickup hour: ");
                int pickupHour = input.nextInt();

                System.out.print("Enter pickup minute: ");
                int pickupMinute = input.nextInt();

                DateTime pickupTime = new DateTime(
                        pickupYear, pickupMonth, pickupDay, pickupHour, pickupMinute);

                System.out.print("Enter dropoff year: ");
                int dropoffYear = input.nextInt();

                System.out.print("Enter dropoff month: ");
                int dropoffMonth = input.nextInt();

                System.out.print("Enter dropoff day: ");
                int dropoffDay = input.nextInt();

                System.out.print("Enter dropoff hour: ");
                int dropoffHour = input.nextInt();

                System.out.print("Enter dropoff minute: ");
                int dropoffMinute = input.nextInt();
                input.nextLine();

                DateTime dropoffTime = new DateTime(
                        dropoffYear, dropoffMonth, dropoffDay, dropoffHour, dropoffMinute);

                System.out.print("Enter dropoff location: ");
                String dropoffLocation = input.nextLine();

                System.out.print("Enter driver ID: ");
                int driverId = input.nextInt();

                if (type.equals("PRIVATE")) {

                    System.out.print("Enter rider ID: ");
                    int riderId = input.nextInt();

                    boolean added = system.schedulePrivateRide(
                            pickupLocation, pickupTime, dropoffTime,
                            dropoffLocation, riderId, driverId);

                    if (added) {
                        System.out.println("Private ride added successfully.");
                    } else {
                        System.out.println("Failed to add private ride.");
                    }
                }

                else if (type.equals("SHARED")) {

                    System.out.print("Enter number of riders (at least 2): ");
                    int numberOfRiders = input.nextInt();

                    while (numberOfRiders < 2) {
                        System.out.print("A shared ride must have at least 2 riders. Enter again: ");
                        numberOfRiders = input.nextInt();
                    }

                    int[] riderIds = new int[numberOfRiders];

                    for (int i = 0; i < numberOfRiders; i++) {
                        System.out.print("Enter rider ID " + (i + 1) + ": ");
                        riderIds[i] = input.nextInt();
                    }

                    boolean added = system.scheduleSharedRide(
                            pickupLocation, pickupTime, dropoffTime,
                            dropoffLocation, riderIds, driverId);

                    if (added) {
                        System.out.println("Shared ride added successfully.");
                    } else {
                        System.out.println("Failed to add shared ride.");
                    }
                }

                else {
                    System.out.println("Invalid ride type.");
                }
            }

            else if (choice == 0) {
                System.out.println("Goodbye.");
            }

            else {
                System.out.println("Invalid choice.");
            }



        } while (choice != 0);
        input.close();





    }

}