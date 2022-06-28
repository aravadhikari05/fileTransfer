import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class fileSender {

    private Socket socket;
    private String ip = "192.168.1.30";
    private InputStream in;


    public fileSender(int PORT, File file) {
        try {
            socket = new Socket(ip, PORT);
            System.out.println("connected");

            in = new FileInputStream(file);

            byte[] data = new byte[in.available()];
            for(int i = 0; i < data.length; i++) {
                System.out.write(data[i]);
            }
            

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
