package corejava.plaf;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.*;

/**
 * A frame with a button panel for changing look-and-feel
 */
public class PlafFrame extends JFrame {

    private JPanel buttonPanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new PlafFrame();
            frame.setTitle("PlafFrame");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    public PlafFrame() {
        buttonPanel = new JPanel();

        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo info: infos) {
            makeButton(info.getName(), info.getClassName());
        }

        setSize(400, 300);
        add(buttonPanel, BorderLayout.NORTH);
        pack();
    }

    /**
     * Makes a button to change the pluggable look-and-feel
     * @param name the button name
     * @param className the name of the look-and-feel class
     */
    private void makeButton(String name, String className) {
        // add button to panel
        JButton button = new JButton(name);
        buttonPanel.add(button);

        // set button action
        button.addActionListener(event -> {
            // button action: switch to the new look-and-feel
            try {
                UIManager.setLookAndFeel(className);
                SwingUtilities.updateComponentTreeUI(this);
                pack();
            } catch(Exception e) {
                e.printStackTrace();
            }
        });
    }

}
