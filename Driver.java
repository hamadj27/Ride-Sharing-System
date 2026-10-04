public class Driver extends Person implements IDriver {

    private String vehiclePlate;
    private VehicleType vehicleType;

    public Driver(int id, String name, String phoneNumber, String vehiclePlate, VehicleType vehicleType) {
        super(id, name, phoneNumber);
        setVehiclePlate(vehiclePlate);
        this.vehicleType = vehicleType;

    }//big o is 1

    @Override
    public String getVehiclePlate() {
        return vehiclePlate;
    }//big o is 1
    @Override
    public void setVehiclePlate(String vehiclePlate) {
        if (vehiclePlate == null || !vehiclePlate.matches("[A-Z]{3}\\d{4}")) {
            throw new IllegalArgumentException("Vehicle plate must be 3 uppercase letters followed by 4 digits.");

        }
        this.vehiclePlate = vehiclePlate;
    }//big o is 1


    @Override
    public VehicleType getVehicleType() {
        return vehicleType;
    }//big o is 1


    @Override
    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }//big o is 1

    @Override
    public int compareTo(IDriver other) {
        if (this.getId() < other.getId()) {
            return -1;
        } else if (this.getId() > other.getId()) {
            return 1;
        } else {
            return 0;
        }
    }//big o is 1



}