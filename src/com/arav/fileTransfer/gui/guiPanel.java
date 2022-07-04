package com.arav.fileTransfer.gui;

import com.arav.fileTransfer.controller.GuiObserver;
import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class guiPanel extends JPanel {

    public static final int WINDOW_WIDTH = 360;
    public static final int WINDOW_HEIGHT = 180;

    private File selectedFileToSend;

    private JTextField fileText;
    private JComboBox<String> dropdown;

    private ArrayList<GuiObserver> observers;

    public guiPanel() {
        observers = new ArrayList<>();
        initPanel();
    }

    private void initPanel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e.getMessage());
        }

        setLayout(null);
        setBackground(new Color (0xEFE0B8));

        JLabel hostLabel = new JLabel("Host:");
        hostLabel.setBounds(50, 0, 80, 25);
        add(hostLabel);

        //host list dropdown
        dropdown= new JComboBox<>();
        dropdown.setBounds(20, 20, 300, 25);
        add(dropdown);

        JLabel fileLabel = new JLabel("File Name:");
        fileLabel.setBounds(40, 60, 80, 25);
        add(fileLabel);

        //uneditable text box for filepath
        fileText = new JTextField();
        fileText.setEditable(false);
        fileText.setBounds(20, 80, 230, 25);
        add(fileText);

        JButton browse = browseButton();
        browse.setBounds(260, 80, 80, 25);
        add(browse);

        JButton send = sendButton();
        send.setBounds(120, 130, 100, 25);
        add(send);

        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
    }

    private JButton browseButton() {
        JButton button = new JButton("Browse");
        JFileChooser fc = new JFileChooser();
        button.addActionListener(ae -> {
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.showOpenDialog(null);
            if(fc.getSelectedFile() != null) {
                selectedFileToSend = fc.getSelectedFile();
                fileText.setText(selectedFileToSend.getAbsolutePath());
            }
        } );
        return button;
    }

    private JButton sendButton() {
        JButton button = new JButton("Send");
        button.addActionListener(ae -> {
            fireSendButtonPressed();
        });
        return button;
    }

    public void addHostList(ArrayList<String> list) {
        dropdown.removeAllItems();
        for(String s: list) {
            dropdown.addItem(s);
        }
    }

    public void addHost(String ip) {
        dropdown.addItem(ip);
    }

    public void removeHost(String ip) {
        for(int i = 0; i < dropdown.getItemCount(); i++) {
            if(dropdown.getItemAt(i).equals(ip)) {
                dropdown.removeItemAt(i);
            }
        }
    }

    //triggers popup when client requests to send file
    public void triggerPopup(String ip, String fileName) {
        Object[] options = {"Accept", "Deny"};

        int selection = JOptionPane.showOptionDialog(null, ip + " wants to send you " + fileName, "Alert", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        switch (selection){
            //TODO Override accept button pressed to not go away until file has been chosen
            case 0:
                System.out.println("hi");
                JFileChooser fc = new JFileChooser(); //TODO hangs sometimes
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int option = fc.showOpenDialog(null);
                if(option == JFileChooser.APPROVE_OPTION) {
                    fireFileSaveAcceptButtonPressed(fc.getSelectedFile().getAbsolutePath() + File.separator + fileName);
                    break;
                } else {
                    fireFileSaveDenyButtonPressed();
                }
            case 1, JOptionPane.CLOSED_OPTION:
                fireFileSaveDenyButtonPressed();
                break;
        }
    }

    private void fireSendButtonPressed() {
        for(GuiObserver o: observers) {
            o.sendButtonPressed();
        }
    }

    private void fireFileSaveAcceptButtonPressed(String filePath) {
        for (GuiObserver o : observers) {
            o.acceptButtonPressed(filePath);
        }
    }

    private void fireFileSaveDenyButtonPressed() {
        for (GuiObserver o : observers) {
            o.denyButtonPressed();
        }
    }

    public void addObserver(GuiObserver o) {
        observers.add(o);
    }
    public void removeObserver(GuiObserver o) {
        observers.remove(o);
    }

    public File getSelectedFileToSend() {
        return selectedFileToSend;
    }
    public String getSelectedIP() {
        return String.valueOf(dropdown.getSelectedItem());
    }



}
