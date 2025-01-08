import javax.swing.*;
import java.awt.*;

class SimulationGUI {
    private Car car;
    private DashboardPanel dashboardPanel;
    private SpeedometerPanel speedometerPanel;
    private ControlPanel controlPanel;
    private MessagePanel messagePanel;
    private Timer simulationTimer;
    private Timer guiTimer;

    public SimulationGUI(Car car) {
        this.car = car;

        JFrame frame = new JFrame("Car Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        messagePanel = new MessagePanel();
        frame.add(messagePanel, BorderLayout.NORTH);

        speedometerPanel = new SpeedometerPanel(car);
        speedometerPanel.setPreferredSize(new Dimension(200, 200));
        JPanel speedometerContainer = new JPanel(new FlowLayout());
        speedometerContainer.add(speedometerPanel);

        dashboardPanel = new DashboardPanel(car);
        JPanel dashboardContainer = new JPanel(new BorderLayout());
        dashboardContainer.add(dashboardPanel, BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.add(dashboardContainer);
        topPanel.add(speedometerContainer);
        frame.add(topPanel, BorderLayout.CENTER);

        controlPanel = new ControlPanel(car, dashboardPanel, messagePanel);
        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        startGUIUpdater();

        startSimulation();
    }

    private void startGUIUpdater() {
        guiTimer = new Timer(100, e -> {
            dashboardPanel.repaint();
            speedometerPanel.repaint();
        });
        guiTimer.start();
    }

    private void startSimulation() {
        simulationTimer = new Timer(1000, e -> {
            car.getEngine().coolDown(1);

            if (car.getSpeedometer().getSpeed() > 0) {
                car.getSpeedometer().decreaseSpeed(2);
                messagePanel.updateMessage("Car slowing down. Current speed: "
                        + car.getSpeedometer().getSpeed() + " km/h", Color.ORANGE);
            }


            if (car.getFuelTank().isEmpty() && car.getSpeedometer().getSpeed() == 0) {
                endSimulation();
                return;
            }

            if (car.getEngine().isRunning() && car.getSpeedometer().getSpeed() > 0) {
                car.getFuelTank().consumeFuelOverTime(0.1);
                if (car.getFuelTank().isEmpty()) {
                    disableAccelerationButton();
                    messagePanel.updateMessage("Fuel is empty! The car will stop soon.", Color.RED);
                } else if (car.getFuelTank().isLowFuel()) {
                    messagePanel.updateMessage("Warning: Low fuel level!", Color.ORANGE);
                }
            }

            double engineTemp = car.getEngine().getTemperature();
            if (car.getEngine().isOverheated()) {
                messagePanel.updateMessage("Engine overheated! Reduce speed immediately.", Color.RED);
            } else if (engineTemp > 80) {
                messagePanel.updateMessage("Engine temperature is high. Drive carefully.", Color.ORANGE);
            }

            dashboardPanel.repaint();
            speedometerPanel.repaint();
        });
        simulationTimer.start();
    }

    private void disableAccelerationButton() {
        for (Component component : controlPanel.getComponents()) {
            if (component instanceof JButton && ((JButton) component).getText().equals("Accelerate")) {
                component.setEnabled(false);
                break;
            }
        }
    }

    private void endSimulation() {
        if (simulationTimer != null) {
            simulationTimer.stop();
        }
        if (guiTimer != null) {
            guiTimer.stop();
        }

        messagePanel.updateMessage("Simulation ended. The car has stopped.", Color.RED);

        disableControlPanel();
    }

    private void disableControlPanel() {
        for (Component component : controlPanel.getComponents()) {
            if (component instanceof JButton) {
                component.setEnabled(false);
            }
        }
    }
}
