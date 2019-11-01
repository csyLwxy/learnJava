package homeworktest;

import javax.swing.*;
import java.awt.*;

public class ToDoList extends JFrame {
    private static final int WINDOW_W = 400;
    private static final int WINDOW_H = 600;

    public ToDoList() {

        // set some default set
        initSet();

        // get screen dimension
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rectangle = environment.getMaximumWindowBounds();
        int screenHeight = rectangle.height;
        int screenWidth = rectangle.width;

        // set frame width, height and it's location
        setSize(WINDOW_W, WINDOW_H);
        setLocation((screenWidth - WINDOW_W)/2, (screenHeight - WINDOW_H)/2);

        // set frame icon
         Image img = new ImageIcon("./homeworktest/icon.png").getImage();
         setIconImage(img);
    }

    public ToDoList(String title) {
        this();
        setTitle(title);
    }

    private void initSet() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
