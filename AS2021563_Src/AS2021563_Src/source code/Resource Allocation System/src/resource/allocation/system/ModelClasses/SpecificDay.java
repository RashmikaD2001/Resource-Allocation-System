/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resource.allocation.system.ModelClasses;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 *
 * @author RASHMIKA
 */
public class SpecificDay extends Booking{
    
    private DayOfWeek specificDay;
    
    public SpecificDay(Customer customer, Hall hall, Payment payment, LocalDate startDate, LocalDate endDate, DayOfWeek specificDay) {
        super(customer, hall, payment, startDate, endDate);
        setSpecificDay(specificDay);
        calcPayment();
    }

    public SpecificDay(String bookingId, Customer customer, Hall hall, Payment payment, LocalDate startDate, LocalDate endDate,DayOfWeek specificDay) {
        super(bookingId, customer, hall, payment, startDate, endDate);
        setSpecificDay(specificDay);
        calcPayment();
    }

    public SpecificDay(Customer customer, Hall hall, LocalDate startDate, LocalDate endDate, DayOfWeek specificDay) {
        super(customer, hall, startDate, endDate);
        setSpecificDay(specificDay);
        calcPayment();
    }

    public SpecificDay(DayOfWeek specificDay, String bookingId, Hall hall, LocalDate startDate, LocalDate endDate, double bill) {
        super(bookingId, hall, startDate, endDate, bill);
        setSpecificDay(specificDay);
        calcPayment();
    }

    public SpecificDay() {
        setBookingId("");
    }

    public SpecificDay(DayOfWeek specificDay, String bookingId, Customer customer, Hall hall, LocalDate startDate, LocalDate endDate) {
        super(bookingId, customer, hall, startDate, endDate);
        setSpecificDay(specificDay);
        calcPayment();
    }
    
    

    public void setSpecificDay(DayOfWeek specificDay) {
        this.specificDay = specificDay;
    }

    public DayOfWeek getSpecificDay() {
        return specificDay;
    }

    @Override
    public void calcPayment() {
        
        int days = super.getDaysBetween();
        
        double cost =  days*(getHall().getType().getCharge())*0.75;
        
        super.setBill(cost);
        
    }

    @Override
    public String printInvoive() {

        String msg = "############################################\n"
                + "              INVOICE                      \n"
                + "#############################################\n\n"
                + "Customer Name - " + super.getCustomer().getName() + " book for Specific Day\n"
                + "Booking ID - " + super.getBookingId() + "\n"
                + "Booked period from " + super.getStartDate() + " to " + super.getEndDate() + " on " + specificDay + "\n"
                + "Hall - " + super.getHall().getIdHall() + "\n"
                + "Paid date - " + super.getPayment().getPaidDate() + "\n"
                + "Payment ID - " + super.getPayment().getPaymentId() + "\n"
                + "Bill - " + super.getBill() + "\n"
                + "################################################";

        return msg;
    }

    @Override
    public String toString() {
        
        String msg = "Customer - "+getCustomer().getName()+"\nBooking ID - "+getBookingId() +"\nBooked from - "+getStartDate()+" to "+getEndDate()+" on "+getSpecificDay()+"\nBill is - "+super.getBill();
        return msg;
    }
    
    
    
}
