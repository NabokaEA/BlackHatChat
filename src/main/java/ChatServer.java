import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    private boolean isRunning;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }


    public ChatServer() {
        isRunning = true;
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Server started!");
            while (isRunning) {
                System.out.println("Server is waiting for connection");
                Socket socket = serverSocket.accept();
                System.out.println("Client accepted" + socket.getInetAddress().getHostName());
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (Exception e) {
            System.out.println("Server crushed");
        }
    }
}
