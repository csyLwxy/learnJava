/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitodolist;

import java.util.Date;
import java.time.LocalDateTime;

/**
 *
 * @author Ecoczdrumercz
 */
public class ToDo {

    public ToDo() {
        checked = false;
        dateCreated = new Date(); // poresit dateTime, ...
    }
 // public Date date; //datum, kdy se má ukol splnit
    public String polozka; // ukol
    public boolean checked; // splneno/nesplneno
    public Date dateCreated; // datum vytvoreni

    public Date getDateCreated() {
        return dateCreated;
    }

    private void setDateCreated(Date dateCreated) {

        this.dateCreated = dateCreated;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getPolozka() {
        return polozka;
    }

    public void setPolozka(String polozka) {
        this.polozka = polozka;
    }

    
   /* public void setDate(Date date) {
        this.date = date;
    }
    public Date getDate() {
        return this.date;
    }
    */

    @Override
    public String toString() {
        return polozka; //To change body of generated methods, choose Tools | Templates.
    }

}