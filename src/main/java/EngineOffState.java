public class EngineOffState implements EngineState {
    @Override
    public void handle(Engine engine) {
        System.out.println("Engine is off.");
    }
}
