package com.arav.fileTransfer.controller;

import com.arav.fileTransfer.dataHandler.FileReceiver;
import com.arav.fileTransfer.dataHandler.FileSender;
import com.arav.fileTransfer.gui.guiPanel;

import java.io.File;
import java.util.ArrayList;

public class Controller implements GuiObserver, ServerObserver, ClientObserver {

    private guiPanel gui;
    private FileSender client;
    private FileReceiver server;

    public Controller (guiPanel gui, FileSender client, FileReceiver server) {
        this.gui = gui;
        this.client = client;
        this.server = server;

        gui.addObserver(this);
        server.addObserver(this);
        client.addObserver(this);

        //ping for open connections once
        //client.pingForConnections();
    }

    @Override //send request to send file
    public void sendButtonPressed() {
        String sendIP = "";
        File fileToSend = null;
        if(gui.getSelectedIP() != null) {
            sendIP = gui.getSelectedIP();
        }
        if(gui.getSelectedFileToSend() != null) {
            fileToSend = gui.getSelectedFileToSend();
        }
        if(!(fileToSend == null) && !sendIP.equals("")) client.sendFileRequest(fileToSend, sendIP);
    }

    @Override //save file that was requested to be received
    public void acceptButtonPressed(String filePath) {
        System.out.println(filePath);
        //server.saveFile(filePath + )
    }

    @Override //deny file that was requested to be received
    public void denyButtonPressed() {
        System.out.println("File saving denied");
    }

    @Override //update entire list of hosts
    public void hostListUpdated(ArrayList<String> ips) {
        gui.addHostList(ips);
    }

    @Override //
    public void fileSendRequestSent() {

    }

    @Override
    public void fileSendReplyReceived(boolean fileAccepted) {

    }

    @Override //confirm file was sent
    public void fileSent() {
        System.out.println("File Sent");
    }

    @Override //add one host to list
    public void hostWentOnline(String ip) {
        gui.addHost(ip);
    }

    @Override //trigger the popup to alert whether to save or deny file
    public void fileSaveRequestWasReceived(String ip, String fileName) {
        gui.triggerPopup(ip, fileName);
    }

    @Override //confirm file was saved
    public void fileWasSaved() {
        System.out.println("File Saved");
    }

    @Override //remove one host from list
    public void hostWentOffline(String ip) {
        gui.removeHost(ip);
    }
}
