/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.list.ui;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="TaskDetails")
public class TaskDetails {
    
    String task_name;
    String task_type;
    String task_day;
    String task_link;
    String task_priority;
    String task_category;
    String task_sub_cat;
    String task_url;
    public String getTask_sub_cat() {
        return task_sub_cat;
    }
    @XmlAttribute
    public void setTask_sub_cat(String task_sub_cat) {
        this.task_sub_cat = task_sub_cat;
    }

    public String getTask_url() {
        return task_url;
    }
    @XmlAttribute
    public void setTask_url(String task_url) {
        this.task_url = task_url;
    }
   
    public String getTask_priority() {
        return task_priority;
    }
    @XmlAttribute
    public void setTask_priority(String task_priority) {
        this.task_priority = task_priority;
    }

    public String getTask_category() {
        return task_category;
    }
    @XmlAttribute
    public void setTask_category(String task_category) {
        this.task_category = task_category;
    }
    
    public String getTask_link() {
        
        return task_link;
    }
    @XmlAttribute
    public void setTask_link(String task_link) {
        
        this.task_link = task_link;
    }

    public TaskDetails() { }

    public String getTask_name() {
        return task_name;
    }
    
    public String getTask_type() {
        return task_type;
    }

    public String getTask_day() {
        return task_day;
    }
    @XmlAttribute
    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }
    @XmlAttribute
    public void setTask_type(String task_type) {
        this.task_type = task_type;
    }
     @XmlAttribute
    public void setTask_day(String task_day) {
        this.task_day = task_day;
    }

    public TaskDetails(String task_name, String task_type, String task_day, String task_link,
                       String task_priority, String task_category, String task_sub_cat, String task_url) {
        this.task_name = task_name;
        this.task_type = task_type;
        this.task_day = task_day;
        this.task_link = task_link;
        this.task_priority = task_priority;
        this.task_category = task_category;
        this.task_sub_cat = task_sub_cat;
        this.task_url = task_url;
    }
}
