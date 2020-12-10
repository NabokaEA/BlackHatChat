import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ChatServer {

    private boolean isRunning;
    private ConcurrentLinkedQueue<ClientHandler> clientHandlers=new ConcurrentLinkedQueue<>();

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
                ClientHandler clientHandler = new ClientHandler(socket, this);
                clientHandlers.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (Exception e) {
            System.out.println("Server crushed");
        }
    }
    public void broadCast (String message) throws IOException {
        for (ClientHandler clientHandler : clientHandlers) {
            clientHandler.sendMessage(message);

        }
    }
    public  void kickMe (ClientHandler clientHandler){
        clientHandlers.remove(clientHandler);

    }

}
