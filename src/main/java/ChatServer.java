import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ChatServer {

    private boolean isRunning;
    private ConcurrentLinkedQueue<SerialClientHandler> clientHandlers=new ConcurrentLinkedQueue<>();

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
                SerialClientHandler clientHandler = new SerialClientHandler(socket, this);
                clientHandlers.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (Exception e) {
            System.out.println("Server crushed");
        }
    }
    public void broadCast (UserMessage message) throws IOException {
        for (SerialClientHandler clientHandler : clientHandlers) {
            clientHandler.sendMessage(message);

        }
    }
    public  void kickMe (SerialClientHandler clientHandler){
        clientHandlers.remove(clientHandler);

    }

}
