import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandler implements Runnable, Closeable {

    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;
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


    public ClientHandler(Socket socket, ChatServer server) throws IOException {
        connectionsCount++;
        connectionID = connectionsCount;
        userName = "user#" + connectionID;
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
        isRunning = true;
        buffer = new byte[256];
        this.server = server;

    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                int bytesRead = inputStream.read(buffer);
                if (bytesRead == -1) {
                    server.kickMe(this);
                    server.broadCast("Client " + userName + " leave!");
                    break;
                }
                String messageFromClient = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
                if (messageFromClient.replaceAll("[/n/r]", "").isEmpty()) {
                    continue;
                }
                if (messageFromClient.equals("/end")) {
                    System.out.println("Received \\End from client" + connectionID);
                    break;
                }
                System.out.println("Received from client:" + userName + " " + messageFromClient);
                server.broadCast(userName + ": " + messageFromClient);

            } catch (IOException e) {
                System.err.println("Exception while read");
                break;
            }
        }
    }

    public void sendMessage(String message) throws IOException {
        outputStream.write((userName + ": " + message).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
        outputStream.close();
    }
}
