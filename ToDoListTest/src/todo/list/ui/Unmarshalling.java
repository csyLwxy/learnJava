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
import javax.xml.bind.Unmarshaller;

public class Unmarshalling {
 
    public List <TaskDetails> retriveFromXML (){
        TaskList task_new;
        String username = System.getProperty("user.name");
        
	File file = new File("C:\\ToDoList\\taskdetails_"+username+".xml");
        List <TaskDetails> task_list= new ArrayList<>();
        try{
        JAXBContext jaxbContext = JAXBContext.newInstance(TaskList.class);
	Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        task_new = (TaskList) jaxbUnmarshaller.unmarshal(file);
        task_list = task_new.getTasks();
                        
	} catch (JAXBException e) {
		e.printStackTrace();
	  }
        return task_list;
    }
}
