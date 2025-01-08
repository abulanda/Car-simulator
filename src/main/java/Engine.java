import java.awt.*;

public class Engine {
    private EngineState state;
    private boolean running;
    private MessagePanel messagePanel;
    private TemperatureManager temperatureManager;

    public Engine(MessagePanel messagePanel) {
        this.state = new EngineOffState();
        this.running = false;
        this.messagePanel = messagePanel;
        this.temperatureManager = new TemperatureManager();
    }

    public void start() {
        if (!running) {
            running = true;
            state = new EngineOnState();
            state.handle(this);
            messagePanel.updateMessage("Engine started.", Color.GREEN);
        } else {
            messagePanel.updateMessage("Engine is already running.", Color.ORANGE);
        }
    }

    public void stop() {
        if (running) {
            running = false;
            state = new EngineOffState();
            state.handle(this);
            messagePanel.updateMessage("Engine stopped.", Color.ORANGE);
        } else {
            messagePanel.updateMessage("Engine is already off.", Color.RED);
        }
    }

    public void increaseTemperature(double increment) {
        temperatureManager.increaseTemperature(increment);
        if (temperatureManager.isOverheated()) {
            state = new EngineOverheatedState(messagePanel);
            state.handle(this);
        }
    }

    public void coolDown(double decrement) {
        temperatureManager.coolDown(decrement);
        if (!temperatureManager.isOverheated() && state instanceof EngineOverheatedState) {
            state = new EngineOnState();
            messagePanel.updateMessage("Engine temperature back to normal.", Color.GREEN);
        }
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isOverheated() {
        return temperatureManager.isOverheated();
    }

    public double getTemperature() {
        return temperatureManager.getTemperature();
    }

    public EngineState getState() {
        return state;
    }
}
