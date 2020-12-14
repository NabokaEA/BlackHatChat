import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
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

    public void exit(ActionEvent actionEvent) throws IOException, CloneNotSupportedException {
        Parent Auth = FXMLLoader.load(getClass().getResource("Auth.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Авторизация");
        stage.setScene(new Scene(Auth, 400, 250));
        stage.setResizable(false);
        stage.show();
        bottonSend.getScene().getWindow().hide();
        FileHistoryService.getInstance().saveHistory(Arrays.asList(output.getText().split("\n").clone()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FileHistoryService.getInstance().load().forEach(historyLine->{output.appendText(historyLine+"\n");});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
