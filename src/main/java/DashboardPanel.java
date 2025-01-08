import javax.swing.*;
import java.awt.*;

class DashboardPanel extends JPanel {
    private Car car;

    public DashboardPanel(Car car) {
        this.car = car;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setColor(Color.WHITE);
        g2d.drawString("Speed: " + car.getSpeedometer().getSpeed() + " km/h", 50, 50);

        g2d.setColor(Color.GREEN);
        int fuelWidth = (int) (car.getFuelTank().getFuelLevel() * 3);
        g2d.fillRect(50, 70, fuelWidth, 20);

        g2d.setColor(car.getEngine().getTemperature() > 90 ? Color.RED : Color.WHITE);
        g2d.drawString("Engine Temp: " + car.getEngine().getTemperature() + "°C", 50, 110);

        g2d.drawString("Gear: " + car.getGearbox().getCurrentGear(), 50, 140);

        g2d.drawString("Engine: " + (car.getEngine().isRunning() ? "On" : "Off"), 50, 170);
    }
}
