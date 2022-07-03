import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class guiPanel extends JPanel {

    public final int WINDOW_WIDTH = 360;
    public final int WINDOW_HEIGHT = 180;

    private String selectedFilePath;

    private JTextField fileText;
    private JComboBox<String> dropdown;

    private ArrayList<guiObserver> observers;


    public guiPanel() {
        initPanel();
    }

    private void initPanel() {
        observers = new ArrayList<>();

        setLayout(null);
        setBackground(new Color (0xEFE0B8));

        JLabel hostLabel = new JLabel("Host:");
        hostLabel.setBounds(50, 0, 80, 25);
        add(hostLabel);

        JComboBox<String> dropdown= new JComboBox<>();
        dropdown.setBounds(20, 20, 300, 25);
        add(dropdown);

        JLabel fileLabel = new JLabel("File Name:");
        fileLabel.setBounds(40, 60, 80, 25);
        add(fileLabel);

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
        JButton butt = new JButton("Browse");
        JFileChooser fc = new JFileChooser();
        butt.addActionListener(ae -> {
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.showOpenDialog(null);
            if(fc.getSelectedFile() != null) {
                selectedFilePath = fc.getSelectedFile().getAbsolutePath();
                fileText.setText(selectedFilePath);
            }
        } );
        return butt;
    }

    private JButton sendButton() {
        JButton butt = new JButton("Send");
        butt.addActionListener(ae -> {
            fireSendButtonPressed();
        });
        return butt;
    }

    public String getSelectedFilePath() {
        return selectedFilePath;
    }
    public String getSelectedIP() {
        return String.valueOf(dropdown.getSelectedItem());
    }
    public void updateDropdown(ArrayList<String> list) {
        dropdown.removeAllItems();
        for(String s: list) {
            dropdown.addItem(s);
        }
    }

    public void fireSendButtonPressed() {
        for(guiObserver o: observers) {
            o.sendButtonWasPressed(this);
        }
    }

    public void addObserver(guiObserver o) {
        observers.add(o);
    }
    public void removeObserver(guiObserver o) {
        observers.remove(o);
    }

}
