package todolist;

import todo.list.frame.TaskDetails;
import todo.list.frame.TaskList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Unmarshalling {

    public static List<TaskDetails> retriveFromXML() {
        TaskList newTask;
        String username = System.getProperty("user.name");

        File file = new File("./ToDoList/taskdetails_" + username + ".xml");
        List<TaskDetails> taskList = new ArrayList<>();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(TaskList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            newTask = (TaskList) jaxbUnmarshaller.unmarshal(file);
            taskList = newTask.getTasks();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return taskList;
    }
}