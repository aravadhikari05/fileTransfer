package com.arav.fileTransfer;

import com.arav.fileTransfer.controller.Controller;
import com.arav.fileTransfer.dataHandler.FileReceiver;
import com.arav.fileTransfer.dataHandler.FileSender;
import com.arav.fileTransfer.gui.guiPanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int port = 55219;
        System.out.println("Running on port: " + port);

        //initialize window
        guiPanel gui = new guiPanel();
        JFrame frame = new JFrame("File Transfer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(gui);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        FileSender client = new FileSender(port);
        FileReceiver server = new FileReceiver(port);
        Controller controller = new Controller(gui, client, server);

        Thread t1 = new Thread(server);
        t1.start();

        client.pingForConnections();
    }
}
