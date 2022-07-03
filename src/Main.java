import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int port = 55219;
        //temp-----
        String file = "src/newfile.txt";
        //---------
        guiPanel gui = new guiPanel();
        createFrame(gui);
        FileSender client = new FileSender(port);
        FileReceiver server = new FileReceiver(file, port);
        Controller controller = new Controller(gui, client, server, port);

        Thread t1 = new Thread(server);
        t1.start();

        controller.pingComputers();

    }

    public static void createFrame(guiPanel panel) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }

        JFrame frame = new JFrame("File Transfer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
