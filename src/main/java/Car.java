import java.util.ArrayList;
import java.util.List;

public class Car {
    private Engine engine;
    private FuelTank fuelTank;
    private GearBox gearbox;
    private Speedometer speedometer;
    private AccelerationStrategy accelerationStrategy;
    private List<Observer> observers;

    public Car(MessagePanel messagePanel) {
        this.engine = new Engine(messagePanel);
        this.fuelTank = new FuelTank(50);
        this.gearbox = new GearBox();
        this.speedometer = new Speedometer();
        this.accelerationStrategy = new EconomicAcceleration();
        this.observers = new ArrayList<>();
        this.observers.add(new ConsoleObserver());
    }

    public Engine getEngine() {
        return engine;
    }

    public FuelTank getFuelTank() {
        return fuelTank;
    }

    public GearBox getGearbox() {
        return gearbox;
    }

    public Speedometer getSpeedometer() {
        return speedometer;
    }

    public void setAccelerationStrategy(AccelerationStrategy strategy) {
        this.accelerationStrategy = strategy;
    }

    public void accelerate() {
        if (engine.isOverheated()) {
            notifyObservers("Cannot accelerate. Engine is overheated!");
            return;
        }
        if (fuelTank.isEmpty()) {
            notifyObservers("Cannot accelerate. Fuel is empty!");
            return;
        }
        accelerationStrategy.accelerate(speedometer, fuelTank, engine, gearbox.getCurrentGear());
        notifyObservers("Car accelerated. Current speed: " + speedometer.getSpeed() + " km/h");
    }

    public void decelerate() {
        speedometer.decreaseSpeed(10);
        engine.coolDown(5);
        notifyObservers("Car decelerated. Current speed: " + speedometer.getSpeed() + " km/h");
    }

    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
