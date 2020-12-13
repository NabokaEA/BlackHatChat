import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow {
    public TextArea output;
    public TextField input;
    public Button bottonSend;
    public MenuItem exit;

    public void btnOneClickAction(ActionEvent actionEvent) {

    }

    public void btnEnterClickAction(ActionEvent actionEvent) {
        output.appendText(input.getText() + "\n\r");
        input.clear();
    }

    public void exit(ActionEvent actionEvent) throws IOException {
        Parent Auth = FXMLLoader.load(getClass().getResource("Auth.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Регистрация");
        stage.setScene(new Scene(Auth, 600, 400));
        stage.setResizable(false);
        stage.show();
        bottonSend.getScene().getWindow().hide();
    }
}
