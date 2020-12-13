import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainWindow {
    public TextArea output;
    public TextField input;
    public Button bottonSend;

    public void btnOneClickAction(ActionEvent actionEvent) {

    }

    public void btnEnterClickAction(ActionEvent actionEvent) {
        output.appendText(input.getText() + "\n\r");
        input.clear();
    }
}
