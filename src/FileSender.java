import java.io.*;
import java.net.Socket;

public class fileSender {

    private Socket socket;
    private BufferedInputStream input;
    private BufferedOutputStream output;


    public fileSender(int PORT, File fileToSend, String ip) {
        try {
            socket = new Socket(ip, PORT);
            System.out.println("connected");

            input = new BufferedInputStream(new FileInputStream(fileToSend));
            output = new BufferedOutputStream(socket.getOutputStream());

            byte[] buffer = new byte[8192];
            int count;
            while((count = input.read(buffer)) != -1) {
                output.write(buffer, 0, count);
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (input != null) input.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (input != null) output.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
