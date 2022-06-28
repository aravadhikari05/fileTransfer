import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class fileReceiver {
    private final int PORT = 55329;

    private ServerSocket server;
    private Socket client;
    private File file;
    BufferedInputStream in;

    public fileReceiver(){
        try {
            server = new ServerSocket(PORT);
            client = server.accept();
            System.out.println("connected");
            in = new BufferedInputStream(client.getInputStream());

            byte[] inputArray = new byte[in.available()];
            in.read(inputArray);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(in != null) {
                    in.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


}
