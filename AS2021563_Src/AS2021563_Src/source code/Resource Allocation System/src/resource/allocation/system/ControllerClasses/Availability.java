/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resource.allocation.system.ControllerClasses;

import java.time.LocalDate;
import resource.allocation.system.ModelClasses.Hall;

/**
 *
 * @author RASHMIKA
 */

    
public class Availability {

    private Hall hall;
    private String startdate;
    private String endDate;

    public Availability(Hall hall, String startdate, String endDate) {
        setHall(hall);
        setStartdate(startdate);
        setStartdate(endDate);
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Hall getHall() {
        return hall;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "Availability{" + "hall=" + hall + ", startdate=" + startdate + ", endDate=" + endDate + '}';
    }
    
    

}
