import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int port = 55219;
        //temp-----
        String file = "src/newfile.txt";
        //---------
        GUI gui = new GUI();
        createFrame(gui);
        FileSender client = new FileSender(port);
        FileReceiver server = new FileReceiver(file, port);
        Controller controller = new Controller(gui, client, server, port);

        controller.startThreads();

    }

    public static void createFrame(GUI panel) {
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
