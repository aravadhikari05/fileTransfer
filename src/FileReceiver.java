import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileReceiver implements Runnable{

    private ServerSocket serverSocket;
    private Socket socket;

    private int port;

    private DataInputStream input;
    private DataOutputStream output;

    private String fileToWritePath;

    public FileReceiver(String fileToWritePath, int port){
        this.fileToWritePath = fileToWritePath;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println(e);
        }
        while (true) {
            try {
                System.out.println("Waiting for client");
                socket = serverSocket.accept();
                System.out.println("Connected to client");

                input = new DataInputStream(socket.getInputStream());
                output = new DataOutputStream(new FileOutputStream(fileToWritePath));
/*
                boolean done = false;

                while(!done) {
                    byte indicator = input.readByte();

                    switch(indicator) {
                        case 1:

                    }
                }*/

                System.out.println("Saving file");
                if (StreamTools.copyStream(input, output)) {
                    System.out.println("File Saved");
                } else {
                    System.out.println("File couldn't be saved");
                }


            } catch (IOException e) {
                System.out.println(e);
            } finally {
                StreamTools.closeInputStream(input);
                StreamTools.closeOutputStream(output);
                StreamTools.closeSocket(socket);
            }
        }
    }
}
