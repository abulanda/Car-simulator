class SportAcceleration implements AccelerationStrategy {
    @Override
    public void accelerate(Speedometer speedometer, FuelTank fuelTank, Engine engine, int currentGear) {
        if (currentGear < 3 && speedometer.getSpeed() > 50) {
            System.out.println("Cannot accelerate. Gear is too low for this speed.");
        } else {
            speedometer.increaseSpeed(15);
            fuelTank.consumeFuel(speedometer.getSpeed(), currentGear);
            engine.increaseTemperature(8);
        }
    }
}