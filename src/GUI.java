import javax.swing.*;
import java.awt.*;

public class GUI extends JPanel {

    public final int WINDOW_WIDTH = 400;
    public final int WINDOW_HEIGHT = 100;

    public GUI() {
        initPanel();
    }

    private void initPanel() {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
    }
}
