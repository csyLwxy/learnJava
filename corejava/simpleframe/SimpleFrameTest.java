package corejava.simpleframe;

import java.awt.*;
import javax.swing.*;

public class SimpleFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            SimpleFrame frame = new SimpleFrame();
            // define what should happen when the user closes the application's frame
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
            frame.setVisible(true);
        });
    }
}

class SimpleFrame extends JFrame {
    /**
     * A simple frame
     */
    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public SimpleFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}