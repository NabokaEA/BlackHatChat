import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private  String SERVER_ADDR = "localhost" ;
    private  int SERVER_PORT = 8189 ;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream outputStream;

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
        prepareGUI();
    }

    private void prepareGUI() {
    }


    public static void main(String[] args) throws IOException {






    }
    public void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        dataInputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
        new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    while ( true ) {
                        String strFromServer = dataInputStream.readUTF();
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
            outputStream.writeUTF(strToServer);
    }





    }
}
