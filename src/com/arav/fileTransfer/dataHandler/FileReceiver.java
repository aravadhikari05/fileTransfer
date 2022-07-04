package com.arav.fileTransfer.dataHandler;

import com.arav.fileTransfer.IOTools;
import com.arav.fileTransfer.controller.ServerObserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class FileReceiver implements Runnable{

    private ServerSocket serverSocket;
    private Socket socket;

    private int port;

    private DataInputStream input;
    private FileOutputStream output;

    private ArrayList<ServerObserver> observers;

    public FileReceiver(int port){
        this.port = port;
        observers = new ArrayList<>();
    }

    @Override
    public void run() {
        String fileToWritePath = "";
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println(e);
        }
        while (true) {
            try {
                socket = serverSocket.accept();

                input = new DataInputStream(socket.getInputStream());
                //output = new FileOutputStream(fileToWritePath);//TODO try with resources

                boolean done = false;

                while(!done) {
                    byte indicator = input.readByte();
                    switch(indicator) {
                        case 1:
                            for(ServerObserver o: observers) {
                                o.hostWentOnline(socket.getInetAddress().getHostAddress());
                            }
                            break;
                        case 2:
                            for(ServerObserver o: observers) {
                                String ip = input.readUTF();
                                String fileName = input.readUTF();
                                o.fileSaveRequestWasReceived(ip, fileName);
                            }
                            break;
                        default:
                            done = true;

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOTools.closeInputStream(input);
                IOTools.closeOutputStream(output);
                IOTools.closeSocket(socket);
            }
        }
    }

    public boolean saveFile(String fileToWritePath, InputStream in) {
        FileOutputStream out = null;
        File file = new File(fileToWritePath);
        try {
            if(!file.createNewFile()) {
                return false;
            }
            out = new FileOutputStream(file);
        } catch (IOException e) {
            System.out.println(e);
        }

        return(IOTools.copyStream(in,out) > 0);

    }

    public void fireFileWasSaved() {
        for(ServerObserver o: observers) {
            o.fileWasSaved();
        }
    }

    public void addObserver(ServerObserver o) {
        observers.add(o);
    }
    public void removeObserver(ServerObserver o) {
        observers.remove(o);
    }

}
