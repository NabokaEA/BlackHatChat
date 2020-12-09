import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public ChatServer() {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Server started!");
            while (true) {
                System.out.println("Server is waiting for connection");
                Socket socket= serverSocket.accept();
                System.out.println("Client accepted"+socket.getInetAddress().getHostName());
               new Thread(new ClientHandler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}
}
