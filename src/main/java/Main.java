public class Main {
    public static void main(String[] args) {
        MessagePanel messagePanel = new MessagePanel();
        Car car = new Car(messagePanel);
        SimulationGUI gui = new SimulationGUI(car);
    }
}