import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Application {
    private  String SERVER_ADDR = "localhost" ;
    private  int SERVER_PORT = 8189 ;
    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public void setSERVER_ADDR(String SERVER_ADDR) {
        this.SERVER_ADDR = SERVER_ADDR;
    }

    public void setSERVER_PORT(int SERVER_PORT) {
        this.SERVER_PORT = SERVER_PORT;
    }
    public Client() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader. load (getClass().getResource( "MainWindow.fxml" ));
        primaryStage.setTitle( "BlackHatChat" );
        primaryStage.setScene( new Scene(root, 600 , 400 ));
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        launch();
    }
    public void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    while ( true ) {
                        String strFromServer = (String) objectInputStream.readObject();
                        if (strFromServer.equalsIgnoreCase( "/end" )) {
                            break ;
                        }
                        //chatArea.append(strFromServer);
                        //chatArea.append( "\n" );
                        System.out.println("Received from Server:" + strFromServer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void sendMessage() throws IOException {
      /*  if (!msgInputField.getText().trim().isEmpty()) {
            try {
                outputStream.writeUTF(msgInputField.getText());
                msgInputField.setText( "" );
                msgInputField.grabFocus();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog( null , "Ошибка отправки сообщения" );
            }
        }
       */
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){
            String strToServer = scanner.next();
            objectOutputStream.writeObject(UserMessage.of("", strToServer));
    }





    }
}
