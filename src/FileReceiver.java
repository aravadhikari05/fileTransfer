import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class fileReceiver {

    private ServerSocket server;
    private Socket client;

    private BufferedInputStream input;
    private BufferedOutputStream output;

    public fileReceiver(int PORT, File fileToWrite){
        try {
            server = new ServerSocket(PORT);
            client = server.accept();

            System.out.println("connected");

            input = new BufferedInputStream(client.getInputStream());
            output = new BufferedOutputStream(new FileOutputStream(fileToWrite));

            byte[] buffer = new byte[8192];
            int count;

            while((count = input.read(buffer)) != -1) {
                output.write(buffer, 0, count);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(input != null) input.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if(output != null) output.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if(server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }

    }


}
