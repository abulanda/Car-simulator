import javax.swing.*;
import java.awt.*;

class ControlPanel extends JPanel {
    public ControlPanel(Car car, DashboardPanel dashboardPanel, MessagePanel messagePanel) {
        setLayout(new GridLayout(2, 4, 10, 10));

        JButton startEngineButton = new JButton("Start Engine");
        JButton stopEngineButton = new JButton("Stop Engine");
        JButton economicModeButton = new JButton("Economic Mode");
        JButton sportModeButton = new JButton("Sport Mode");

        JButton shiftUpButton = new JButton("Shift Up");
        JButton shiftDownButton = new JButton("Shift Down");
        JButton accelerateButton = new JButton("Accelerate");
        JButton decelerateButton = new JButton("Decelerate");

        startEngineButton.addActionListener(e -> {
            try {
                car.getEngine().start();
                dashboardPanel.repaint();
                messagePanel.updateMessage("Engine started.", Color.GREEN);
            } catch (Exception ex) {
                messagePanel.updateMessage("Error starting engine: " + ex.getMessage(), Color.RED);
            }
        });

        stopEngineButton.addActionListener(e -> {
            try {
                car.getEngine().stop();
                dashboardPanel.repaint();
                messagePanel.updateMessage("Engine stopped.", Color.ORANGE);
            } catch (Exception ex) {
                messagePanel.updateMessage("Error stopping engine: " + ex.getMessage(), Color.RED);
            }
        });

        economicModeButton.addActionListener(e -> {
            car.setAccelerationStrategy(new EconomicAcceleration());
            messagePanel.updateMessage("Switched to Economic Mode.", Color.BLUE);
        });

        sportModeButton.addActionListener(e -> {
            car.setAccelerationStrategy(new SportAcceleration());
            messagePanel.updateMessage("Switched to Sport Mode.", Color.BLUE);
        });

        shiftUpButton.addActionListener(e -> {
            try {
                car.getGearbox().shiftUp();
                dashboardPanel.repaint();
                messagePanel.updateMessage("Shifted up to gear: " + car.getGearbox().getCurrentGear(), Color.GREEN);
            } catch (Exception ex) {
                messagePanel.updateMessage("Error shifting up: " + ex.getMessage(), Color.RED);
            }
        });

        shiftDownButton.addActionListener(e -> {
            try {
                car.getGearbox().shiftDown();
                dashboardPanel.repaint();
                messagePanel.updateMessage("Shifted down to gear: " + car.getGearbox().getCurrentGear(), Color.GREEN);
            } catch (Exception ex) {
                messagePanel.updateMessage("Error shifting down: " + ex.getMessage(), Color.RED);
            }
        });

        accelerateButton.addActionListener(e -> {
            try {
                if (!car.getFuelTank().isEmpty() && car.getGearbox().getCurrentGear() > 0) {
                    car.accelerate();
                    dashboardPanel.repaint();
                    messagePanel.updateMessage("Car accelerated. Current speed: " + car.getSpeedometer().getSpeed() + " km/h", Color.GREEN);
                } else if (car.getGearbox().getCurrentGear() == 0) {
                    messagePanel.updateMessage("Shift up to drive!", Color.RED);
                } else {
                    messagePanel.updateMessage("Fuel is empty. Please refuel.", Color.RED);
                }
            } catch (Exception ex) {
                messagePanel.updateMessage("Error accelerating: " + ex.getMessage(), Color.RED);
            }
        });

        decelerateButton.addActionListener(e -> {
            try {
                car.decelerate();
                dashboardPanel.repaint();
                messagePanel.updateMessage("Car decelerated. Current speed: " + car.getSpeedometer().getSpeed() + " km/h", Color.ORANGE);
            } catch (Exception ex) {
                messagePanel.updateMessage("Error decelerating: " + ex.getMessage(), Color.RED);
            }
        });

        add(startEngineButton);
        add(stopEngineButton);
        add(economicModeButton);
        add(sportModeButton);

        add(shiftUpButton);
        add(shiftDownButton);
        add(accelerateButton);
        add(decelerateButton);
    }
}
