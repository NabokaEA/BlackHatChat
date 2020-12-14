import sun.plugin2.message.Message;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SerialClientHandler implements Runnable, Closeable {
    private final ObjectInputStream inputStream;
    private final ObjectOutputStream outputStream;
    private static int connectionsCount = 0;
    private int connectionID = 0;
    private final String userName;
    private boolean isRunning;
    private final byte[] buffer;
    private final ChatServer server;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }


    public SerialClientHandler(Socket socket, ChatServer server) throws IOException {
        connectionsCount++;
        connectionID = connectionsCount;
        userName = "user#" + connectionID;
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        isRunning = true;
        buffer = new byte[256];
        this.server = server;
        outputStream.writeObject(UserMessage.of(userName,"Ok"));
        outputStream.flush();

    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                UserMessage userMessage=(UserMessage) inputStream.readObject();
                System.out.println(userMessage);
                server.broadCast(userMessage);
                if (userMessage.equals("/end")) {
                    System.out.println("Received \\/End from client" + connectionID);
                    break;
                }
                System.out.println("Received from client:" + userName + " " + userMessage);


            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Exception while read");
                break;
            }
        }
    }

    public void sendMessage(UserMessage message) throws IOException {
        outputStream.writeObject(userName + ": " + message);
        outputStream.flush();
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
        outputStream.close();
    }
}
