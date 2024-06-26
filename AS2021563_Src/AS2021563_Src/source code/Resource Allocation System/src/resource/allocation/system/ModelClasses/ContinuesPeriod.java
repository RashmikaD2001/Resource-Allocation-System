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
public class ContinuesPeriod extends Booking{
    
    public ContinuesPeriod(Customer customer, Hall hall, Payment payment, LocalDate startDate, LocalDate endDate) {
        super(customer, hall, payment, startDate, endDate);
        calcPayment();
    }

    public ContinuesPeriod(String bookingId, Customer customer, Hall hall, Payment payment, LocalDate startDate, LocalDate endDate) {
        super(bookingId, customer, hall, payment, startDate, endDate);
        calcPayment();
    }

    public ContinuesPeriod(Customer customer, Hall hall, LocalDate startDate, LocalDate endDate) {
        super(customer, hall, startDate, endDate);
        calcPayment();
    }

    public ContinuesPeriod(String bookingId, Customer customer, Hall hall, LocalDate startDate, LocalDate endDate) {
        super(bookingId, customer, hall, startDate, endDate);
        calcPayment();
    }
    

    public ContinuesPeriod(String bookingId, Hall hall, LocalDate startDate, LocalDate endDate, double bill) {
        super(bookingId, hall, startDate, endDate, bill);
    }

    public ContinuesPeriod() {
        setBookingId("");
    }
    
    

    @Override
    public void calcPayment() {
        int days = super.getDaysBetween()+1;
        
        double cost =  days*(getHall().getType().getCharge());
        
        super.setBill(cost);
    }

    @Override
    public String printInvoive() {
     
        String msg = "############################################\n" +
             "              INVOICE                      \n" +
             "#############################################\n\n" +
             "Customer Name - " + super.getCustomer().getName() + " book for Continues Period\n" +
             "Booking ID - " + super.getBookingId() + "\n" +
             "Booked period from " + super.getStartDate() + " to " + super.getEndDate() + "\n" +
             "Hall - " + getHall().getIdHall() + "\n" +
             "Paid date - " + super.getPayment().getPaymentId() + "\n" +
             "Payment ID - " + super.getPayment().getPaymentId() + "\n" +
             "Bill - " + super.getBill() + "\n" +
             "################################################";
        
        return msg;
        
    }

    @Override
    public String toString() {
        
        String msg = "Customer - "+getCustomer().getName()+"\nBooking ID - "+getBookingId() +"\nBooked from - "+getStartDate()+" to "+getEndDate()+"\nBill is - Rs."+super.getBill();
        return msg;
    }
    
    
}
