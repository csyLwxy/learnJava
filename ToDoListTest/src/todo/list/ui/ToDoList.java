/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.list.ui;

import javax.swing.*;
import  java.awt.EventQueue;

public class ToDoList {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame toDoList = new MainFrame();
            toDoList.setTitle("ToDoList");
            toDoList.setVisible(true);
        });
    }
}
