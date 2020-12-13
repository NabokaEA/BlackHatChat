import com.sun.nio.sctp.MessageInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Auth {
    public TextField login;
    public TextField password;

    public void Enter(ActionEvent actionEvent) throws IOException {
        boolean auth = MockAuthServiceImpl.getInstance().auth(login.getText(), password.getText());
        if (auth) {
            Parent MainWindow = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("BlackCatChat");
            stage.setScene(new Scene(MainWindow, 600, 400));
            stage.setResizable(false);
            stage.show();
            login.getScene().getWindow().hide();
        } else {
            login.setText("Wrong user name or Password! Try again");
            password.clear();
        }
    }

    public void Registrate(ActionEvent actionEvent) throws IOException {
        Parent Registration = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Регистрация");
        stage.setScene(new Scene(Registration, 600, 400));
        stage.setResizable(false);
        stage.show();
        login.getScene().getWindow().hide();
    }

}