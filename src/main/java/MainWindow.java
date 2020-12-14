import javafx.application.Platform;
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
    private String userName;



    public void btnEnterClickAction(ActionEvent actionEvent) throws IOException {
        NetworkService.getInstance().write(UserMessage.of(userName, input.getText()));
        input.clear();
    }

    public void exit(ActionEvent actionEvent) throws IOException, CloneNotSupportedException {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FileHistoryService.getInstance().load().forEach(historyLine->{output.appendText(historyLine+"\n");});
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            userName=NetworkService.getInstance().getUsername();
            new CharReader(output, NetworkService.getInstance().getInputStream()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
