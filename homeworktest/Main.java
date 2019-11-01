package homeworktest;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        EventQueue.invokeLater(() -> {
            JFrame toDoList = new ToDoList("MyToDoList");
            toDoList.setLayout(new FlowLayout());

            toDoList.setLayout(new BorderLayout());
            JScrollBar scrollBar = new JScrollBar();
            toDoList.add(scrollBar, BorderLayout.EAST);
        });
    }
}
