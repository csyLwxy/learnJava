package algorithm.astart;

import javax.swing.JPanel;

/**
 * @author web
 */
public class MyPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    final static int SIZE = 50;

    public MyPanel() {
    }

    MyPanel(int x, int y) {
        this.setBounds(x * SIZE, y * SIZE, SIZE, SIZE);
    }

    MyPanel(DiamondPosition diamondPosition) {
        this.setBounds(diamondPosition.getX() * SIZE, diamondPosition.getY() * SIZE, SIZE, SIZE);
    }
}
