package todolist;

import todo.list.frame.TaskDetails;
import todo.list.frame.TaskList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * 将事件保存为XML文件
 */
public class Marshalling {

    public static void saveToXML(TaskDetails task) {
        String username = System.getProperty("user.name");
        File file = new File("./ToDoList/taskdetails_" + username + ".xml");
        try {
            if (file.length() != 0) {
                JAXBContext jaxbContext = JAXBContext.newInstance(TaskList.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

                TaskList taskAbout = (TaskList) unmarshaller.unmarshal(file);
                TaskDetails taskStore = new TaskDetails(task.getTaskName(), task.getTaskType(), task.getTaskDay(),
                        task.getTaskLink(), task.getTaskPriority(), task.getTaskCategory(), task.getTaskSubCat(),
                        task.getTaskUrl());
                List<TaskDetails> taskList = taskAbout.getTasks();
                taskList.add(taskStore);
                taskAbout.setTasks(taskList);

                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                jaxbMarshaller.marshal(taskAbout, file);
            } else {
                try {
                    JAXBContext jaxbContext = JAXBContext.newInstance(TaskList.class);
                    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

                    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    TaskList taskList = new TaskList();
                    List<TaskDetails> ll = new ArrayList<>();
                    ll.add(task);
                    taskList.setTasks(ll);

                    jaxbMarshaller.marshal(taskList, file);
                } catch (JAXBException ignored) {
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
