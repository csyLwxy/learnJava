  
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitodolist;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {

    public ToDoList() {

    }
    public List<ToDo> toDoList = new ArrayList<ToDo>();

    public List<ToDo> getToDoList() {
        return toDoList;
    }

    public void NewToDoList(String text) {
        ToDo noveTodo = new ToDo();
        noveTodo.setPolozka("- "+text); // polozka bude value z input boxu
        toDoList.add(noveTodo);
    }
}