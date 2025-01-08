public class TemperatureManager {
    private double temperature;
    private final double maxTemperature = 100;
    private final double minTemperature = 20;

    public TemperatureManager() {
        this.temperature = minTemperature;
    }

    public void increaseTemperature(double increment) {
        temperature += increment;
    }

    public void coolDown(double decrement) {
        temperature = Math.max(minTemperature, temperature - decrement);
    }

    public boolean isOverheated() {
        return temperature > maxTemperature;
    }

    public double getTemperature() {
        return temperature;
    }
}
