/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.list.ui;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TaskList {
    
    private List<TaskDetails> tasks;

    public List<TaskDetails> getTasks() {
        return tasks;
    }

    @XmlElement(name="TaskDetails")
    public void setTasks(List<TaskDetails> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return tasks.toString();
    }
}
