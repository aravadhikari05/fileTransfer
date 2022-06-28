import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        createFrame();
    }

    public static void createFrame() {
        JFrame frame = new JFrame("File Transfer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GUI());
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
