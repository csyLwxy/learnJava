package corejava.mouse;

import javax.swing.*;

/**
 * A frame containing a panel for testing mouse operations
 */

public class MouseFrame extends JFrame {
    public MouseFrame() {
        add(new MouseComponent());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }
}