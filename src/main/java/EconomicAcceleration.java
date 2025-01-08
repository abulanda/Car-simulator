class EconomicAcceleration implements AccelerationStrategy {
    @Override
    public void accelerate(Speedometer speedometer, FuelTank fuelTank, Engine engine, int currentGear) {
        if (currentGear > 2 && speedometer.getSpeed() < 50) {
            System.out.println("Cannot accelerate. Gear is too high for this speed.");
        } else {
            speedometer.increaseSpeed(5);
            fuelTank.consumeFuel(speedometer.getSpeed(), currentGear);
            engine.increaseTemperature(2);
        }
    }
}