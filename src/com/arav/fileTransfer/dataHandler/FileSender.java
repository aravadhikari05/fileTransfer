package com.arav.fileTransfer.dataHandler;

import com.arav.fileTransfer.IOTools;
import com.arav.fileTransfer.controller.ClientObserver;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;


public class FileSender {

    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    private int port;

    private ArrayList<String> connections;

    private ArrayList<ClientObserver> observers;

    public FileSender(int port) {
        this.port = port;
        connections = new ArrayList<>();
        observers = new ArrayList<>();
    }
    public void sendFileRequest(File file, String ip) {
        try {
            System.out.println("Waiting for server");
            socket = new Socket(); //TODO try with resources
            socket.connect( new InetSocketAddress(ip, port), 2000);
            System.out.println("Connected to server");

            output = new DataOutputStream(socket.getOutputStream());
            output.writeByte(2);
            output.writeUTF(ip);
            output.writeUTF(file.getName());
            output.flush();

        } catch(IOException e) {
            System.out.println(e);
        } finally {
            IOTools.closeInputStream(input);
            IOTools.closeOutputStream(output);
            IOTools.closeSocket(socket);
        }
    }

    public void pingForConnections() {
        ArrayList<String> ips = new ArrayList<>();
        String subnet = "192.168.1.";
        for(int i = 1; i < 37; i++) {
            String ip = subnet + Integer.toString(i);
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(ip, port), 200);
                System.out.println("connectd");
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.writeByte(1); //indicate that its online
                IOTools.closeSocket(socket);
                ips.add(ip);

            } catch (IOException e) {
                //System.out.println(e);
            }
        }
        for(ClientObserver o: observers) {
            o.hostListUpdated(ips);
        }
    }
    public void addObserver(ClientObserver o) {
        observers.add(o);
    }
    public void removeObserver(ClientObserver o) {
        observers.remove(o);
    }

    public ArrayList<String> getConnections() {
        return connections;
    }


}
