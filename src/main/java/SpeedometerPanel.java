import javax.swing.*;
import java.awt.*;

class SpeedometerPanel extends JPanel {
    private Car car;

    public SpeedometerPanel(Car car) {
        this.car = car;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int radius = Math.min(getWidth(), getHeight()) / 2 - 10;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        g.setColor(Color.BLACK);
        g.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

        g.setColor(Color.RED);
        double angle = Math.toRadians(-90 + car.getSpeedometer().getSpeed() * 2);
        int pointerX = centerX + (int) (radius * Math.cos(angle));
        int pointerY = centerY + (int) (radius * Math.sin(angle));
        g.drawLine(centerX, centerY, pointerX, pointerY);
    }
}
