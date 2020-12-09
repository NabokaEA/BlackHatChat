import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable, Closeable {
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;


    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
while (true){
    try {
        String messageFromClient = inputStream.readUTF();
        if (messageFromClient.equals( "/end" )) {
            System.out.println("Received \\End from client");
            break ;
        }
        System.out.println("Received from client: "+messageFromClient);
        outputStream.writeUTF("Echo from server: "+messageFromClient);
        outputStream.flush();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
        outputStream.close();
    }
}
