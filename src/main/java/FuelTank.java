public class FuelTank {
    private double fuelLevel;

    public FuelTank(double fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public double getFuelLevel() {
        return fuelLevel;
    }

    public void consumeFuel(double speed, int gear) {
        double consumptionRate = (speed / 50.0) + (gear * 0.1);
        fuelLevel = Math.max(0, fuelLevel - consumptionRate);
    }

    public void consumeFuelOverTime(double rate) {
        fuelLevel = Math.max(0, fuelLevel - rate);
    }

    public boolean isEmpty() {
        return fuelLevel <= 0;
    }

    public boolean isLowFuel() {
        return fuelLevel < 5;
    }
}
