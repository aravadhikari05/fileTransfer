public class Controller implements guiObserver {

    private guiPanel gui;
    private FileSender client;
    private FileReceiver server;

    private String ip;
    private String filePath;
    private int port;

    public Controller (guiPanel gui, FileSender client, FileReceiver server, int port) {
        this.gui = gui;
        this.client = client;
        this.server = server;

        this.ip = "";
        this.filePath = "";
        this.port = port;

        gui.addObserver(this);
    }

    @Override
    public void sendButtonWasPressed(guiPanel panel) {
        if(panel.getSelectedIP() != null) {
            ip = panel.getSelectedIP();
        }
        if(panel.getSelectedIP() != null) {
            filePath = panel.getSelectedFilePath();
        }
        if(!filePath.equals("") && !ip.equals("")) client.sendFile(filePath, ip);
    }

    public void pingComputers() {
        while(true) {
           // gui.updateDropdown(StreamTools.pingForConnections(port));
        }

    }
}
