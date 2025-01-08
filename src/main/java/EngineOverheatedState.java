import java.awt.*;

public class EngineOverheatedState implements EngineState {
    private MessagePanel messagePanel;

    public EngineOverheatedState(MessagePanel messagePanel) {
        this.messagePanel = messagePanel;
    }

    @Override
    public void handle(Engine engine) {
        messagePanel.updateMessage("Warning: Engine is overheated! Reduce speed immediately.", Color.RED);
    }
}
