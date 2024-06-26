/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package resource.allocation.system.ModelClasses;

/**
 *
 * @author RASHMIKA
 */
public enum HallType {
    
    SMALL (200,100000),
    MEDIUM (500,200000),
    LARGE (1000,500000),
    OTHER(0,0);
    
    private final int capacity;
    private final int charge;

    HallType(int capacity, int charge){
        this.capacity = capacity;
        this.charge = charge;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCharge() {
        return charge;
    }
    
    
}
