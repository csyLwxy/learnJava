package todolist;

import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            ToDoList todolist = new ToDoList();
        });
    }
}