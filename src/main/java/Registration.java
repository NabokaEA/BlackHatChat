import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Registration {
    public TextField login;
    public TextField password;

    public void Enter(ActionEvent actionEvent) throws IOException {
        MockAuthServiceImpl.getInstance().addUser(login.getText(),password.getText());
        Parent MainWindow = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        Stage stage = new Stage();
        stage.setTitle("BlackCatChat");
        stage.setScene(new Scene(MainWindow, 600, 400));
        stage.setResizable(false);
        stage.show();
        login.getScene().getWindow().hide();
    }
}
