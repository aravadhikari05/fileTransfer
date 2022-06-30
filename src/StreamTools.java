import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class StreamTools {

    public static boolean copyStream(InputStream in, OutputStream out) {
        try {
            byte[] buffer = new byte[8192];
            int count;
            int start = 0;

            while ((count = in.read(buffer)) != -1) {
                if(start == 0) {
                    start++;
                }
                out.write(buffer, 0, count);
            }
            if(start == 0) {
                return false;
            }
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    public static ArrayList<String> pingForConnections(int port) {
        ArrayList<String> ips = new ArrayList<>();
        String subnet = "192.168.1.";
        for(int i = 1; i < 100; i++) {
            String ip = subnet + Integer.toString(i);
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(ip, port), 200);
                StreamTools.closeSocket(socket);
                ips.add(ip);
            } catch (IOException e) {
                //System.out.println(e);
            }
        }
        return ips;
    }

    public static void closeInputStream(InputStream in) {
        try {
            if(in != null) {
                in.close();
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void closeOutputStream(OutputStream out) {
        try {
            if(out != null) {
                out.close();
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void closeSocket(Socket socket) {
        try {
            if(socket != null) {
                socket.close();
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void closeServerSocket(ServerSocket server) {
        try {
            if(server != null) {
                server.close();
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }


}
