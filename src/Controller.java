import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements Observer, Runnable{

    private GUI gui;
    private FileSender client;
    private FileReceiver server;

    private String ip;
    private String filePath;
    private int port;

    public Controller (GUI gui, FileSender client, FileReceiver server, int port) {
        this.gui = gui;
        this.client = client;
        this.server = server;

        this.ip = "";
        this.filePath = "";
        this.port = port;

        gui.addObserver(this);
    }

    public void startThreads() {
        Thread t1 = new Thread(server);
        t1.start();
    }

    @Override
    public void selectedIPWasChanged(GUI panel) {
        if(panel.getSelectedIP() != null) ip = panel.getSelectedIP();
    }

    @Override
    public void selectedFileWasChanged(GUI panel) {
        if(panel.getSelectedFilePath() != null) filePath = panel.getSelectedFilePath();
    }

    @Override
    public void sendButtonWasPressed(GUI gui) {
        if(!filePath.equals("") && !ip.equals("")) client.sendFile(filePath, ip);
    }

    @Override
    public void run() {
        gui.updateDropdown(StreamTools.pingForConnections(port));
    }
}
