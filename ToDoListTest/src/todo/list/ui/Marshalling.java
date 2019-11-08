/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.list.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Marshalling  {
    public void saveToXML (TaskDetails task){
       String username = System.getProperty("user.name");
        File file = new File("C:\\ToDoList\\taskdetails_"+username+".xml");
            try {
                if(file.length()!=0){
                    JAXBContext jaxbContext = JAXBContext.newInstance(TaskList.class);
                    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                
                    TaskList task_about = (TaskList) unmarshaller.unmarshal(file);
                    TaskDetails task_store = new TaskDetails(task.getTask_name(), task.getTask_type(), task.getTask_day(),task.getTask_link(),task.getTask_priority(),task.getTask_category(),task.getTask_sub_cat(),task.getTask_url());
                    List <TaskDetails> task_list = task_about.getTasks();
                    task_list.add(task_store);
                    task_about.setTasks(task_list);
                
                    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    jaxbMarshaller.marshal(task_about, file);
	        }                               
                else{
                    try{
                        JAXBContext jaxbContext = JAXBContext.newInstance(TaskList.class);
                        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

                        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                        TaskList task_list = new TaskList();
                        List <TaskDetails> ll = new ArrayList<>();
                        ll.add(task);
                        task_list.setTasks(ll);

                        jaxbMarshaller.marshal(task_list, file);
                    }
                    catch(JAXBException e) {
                    } 
                }
	      
          }catch(JAXBException e){
          e.printStackTrace();
          }
    }
}
