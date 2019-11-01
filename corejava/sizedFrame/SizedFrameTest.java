package sizedFrame;

import java.awt.*;
import javax.swing.*;

public class SizedFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new SizedFrame();
            frame.setTitle("ZisedFrame");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class SizedFrame extends JFrame {

    public SizedFrame() {
        // get screen dimension

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        // set frame width, height and let platform pick screen location

        setSize(screenHeight / 2, screenWidth / 2);
        setLocationByPlatform(true);

        // set frame icon 

        Image img = new ImageIcon("./sizedFrame/java.jpg").getImage();
        setIconImage(img);
    }
}