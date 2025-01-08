import javax.swing.*;
import java.awt.*;

public class MessagePanel extends JPanel {
    private JLabel messageLabel;

    public MessagePanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 50));
        setBackground(new Color(50, 50, 50));

        messageLabel = new JLabel("Welcome to Car Simulator!");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(messageLabel, BorderLayout.CENTER);
    }

    public void updateMessage(String message, Color color) {
        messageLabel.setText(message);
        messageLabel.setForeground(color);
    }
}
