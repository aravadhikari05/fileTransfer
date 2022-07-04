package com.arav.fileTransfer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class IOTools {

    public static long copyStream(InputStream in, OutputStream out) {
        int count = 0;
        try {
            byte[] buffer = new byte[8192];
            int n;

            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
                count += n;
            }

        } catch (IOException e) {
            System.out.println(e);
            return count;
        }

        return count;
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
