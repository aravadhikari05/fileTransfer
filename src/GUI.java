import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

public class GUI extends JPanel {

    public final int WINDOW_WIDTH = 600;
    public final int WINDOW_HEIGHT = 200;

    File selectedFile;
    ArrayList<String> temp;

    public GUI() {
        initPanel();
        fileOpener();
        setArrow();
        selectClient();
        sendButton();
    }

    private void initPanel() {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setBackground(new Color (0xEFE0B8));
    }
    private void setArrow() {
        ImageIcon image = new ImageIcon("src/arrow.png");
        JLabel arrow = new JLabel(image);
        add(arrow);
    }

    private void fileOpener() {
        JFileChooser f1 = new JFileChooser();
        JTextField text = new JTextField("Choose File");
        text.setEditable(false);
        text.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int returnValue = f1.showDialog(GUI.this, "OPEN");
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = f1.getSelectedFile();
                    text.setText(selectedFile.getName());
                }
            }
        });
        add(text);
    }

    private void selectClient() {
        temp = new ArrayList<>();
        temp.add("hi");
        temp.add("ooo");
        temp.add("kflsadjf");
        JComboBox clientList = new JComboBox(temp.toArray());
        add(clientList);
    }
    private void sendButton() {
        JButton send = new JButton("send");
        add(send);
    }

}
