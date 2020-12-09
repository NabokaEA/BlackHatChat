import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final String SERVER_ADDR = "localhost" ;
    private final int SERVER_PORT = 8189 ;
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;



    public static void main(String[] args) throws IOException {





        Scanner scanner=new Scanner(System.in);
        Socket socket= new Socket(SERVER_ADDR, SERVER_PORT);
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        while (scanner.hasNext()){
            String message = scanner.next();
            outputStream.writeUTF(message);
            System.out.println(inputStream.readUTF());
        }
    }
}
