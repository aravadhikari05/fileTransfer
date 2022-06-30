import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

public class FileSender {

    private Socket socket;
    private BufferedInputStream input;
    private BufferedOutputStream output;

    private int port;

    private ArrayList<String> connections;

    public FileSender(int port) {
        connections = new ArrayList<>();
        this.port = port;
    }
    public void sendFile(String fileToSendPath, String ip) {
        try {
            System.out.println("Waiting for server");
            socket = new Socket();
            socket.connect( new InetSocketAddress(ip, port), 2000);
            System.out.println("Connected to server");

            input = new BufferedInputStream(new FileInputStream(fileToSendPath));
            output = new BufferedOutputStream(socket.getOutputStream());

            System.out.println("Sending file");
            if (StreamTools.copyStream(input, output)) {
                System.out.println("File sent");
            } else{
                System.out.println("File could not be sent");
            }
        } catch(IOException e) {
            System.out.println(e);
        } finally {
            StreamTools.closeInputStream(input);
            StreamTools.closeOutputStream(output);
            StreamTools.closeSocket(socket);
        }
    }

    public ArrayList<String> getConnections() {
        return connections;
    }
}
