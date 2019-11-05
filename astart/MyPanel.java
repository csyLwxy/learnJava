package astart;

import javax.swing.JPanel;

public class MyPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public final static int size = 50;

    public MyPanel() {
    }

    public MyPanel(int x, int y) {
        this.setBounds(x * size, y * size, size, size);
    }

    public MyPanel(FangKuaiPosition fk) {
        this.setBounds(fk.getX() * size, fk.getY() * size, size, size);
    }
}
