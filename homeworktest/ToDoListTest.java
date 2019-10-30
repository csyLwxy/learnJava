package homeworktest;

import javax.swing.*;
import java.awt.*;

public class ToDoListTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() ->{
            JFrame frame = new ToDoList();
            frame.setTitle("MyToDoList");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}