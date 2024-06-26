/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resource.allocation.system.ModelClasses;

import java.time.LocalDate;

/**
 *
 * @author RASHMIKA
 */
public class Hall {
    
    private String idHall;
    private HallType type;

    public Hall(String idHall, HallType type) {
        setIdHall(idHall);
        setType(type);
    }

    public Hall(String idHall) {
        setIdHall(idHall);
        setType(HallType.OTHER);
        
    }

    public Hall() {
        setIdHall("");
        setType(HallType.OTHER);
    }
    
    

    public void setIdHall(String idHall) {
        this.idHall = idHall;
    }

    public void setType(HallType type) {
        this.type = type;
    }

    public String getIdHall() {
        return idHall;
    }

    public HallType getType() {
        return type;
    }
    
    public boolean isBooked(Booking booking, LocalDate date){
        
        boolean ans;
        if(booking == null){
            ans = false;
        }else{
            if(date.isAfter(booking.getStartDate()) && date.isBefore(booking.getEndDate())){
                ans = false;
            }else{
                ans = true;
            }
        }
        
        return ans;
    }
    
    @Override
    public String toString() {
        return "Hall Details - " + "idHall=" + idHall + ", type=" + type;
    }

}
