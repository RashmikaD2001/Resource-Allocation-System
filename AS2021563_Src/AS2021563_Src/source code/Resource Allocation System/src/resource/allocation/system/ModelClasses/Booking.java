/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resource.allocation.system.ModelClasses;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author RASHMIKA
 */
public abstract class Booking {
    
    private String bookingId;
    private Customer customer;
    private Hall hall;
    private Payment payment;
    private LocalDate startDate;
    private LocalDate endDate;
    private double bill;

    
    public Booking(String bookingId, Customer customer, Hall hall, Payment payment, LocalDate startDate, LocalDate endDate) {
        setBookingId(bookingId);
        setCustomer(customer);
        setHall(hall);
        setPayment(payment);
        setStartDate(startDate);
        setEndDate(endDate);
        calcPayment();
    }
    
    public Booking(Customer customer, Hall hall, Payment payment, LocalDate startDate, LocalDate endDate) {
        setCustomer(customer);
        setHall(hall);
        setPayment(payment);
        setStartDate(startDate);
        setEndDate(endDate);
        calcPayment();
    }

    public Booking(String bookingId, Customer customer, Hall hall, LocalDate startDate, LocalDate endDate) {
        setBookingId(bookingId);
        setCustomer(customer);
        setHall(hall);
        setStartDate(startDate);
        setEndDate(endDate);
        calcPayment();
    }
    
    

    public Booking(String bookingId,Hall hall, LocalDate startDate, LocalDate endDate, double bill) {
        setBookingId(bookingId);
        setHall(hall);
        setStartDate(startDate);
        setEndDate(endDate);
        setBill(bill);
    }

    public Booking(Customer customer, Hall hall, LocalDate startDate, LocalDate endDate) {
        setCustomer(customer);
        setHall(hall);
        setStartDate(startDate);
        setEndDate(endDate);
        calcPayment();
    }

    public Booking() {
        setBookingId("");
    }
    
    

    public void setBill(double bill) {
        this.bill = bill;
    }

    
    public void setBookingId(String bookingId){
        this.bookingId = bookingId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getBill() {
        return bill;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Hall getHall() {
        return hall;
    }

    public Payment getPayment() {
        return payment;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    
    public int getDaysBetween() {
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }
    
    public abstract void calcPayment();
    
    public abstract String printInvoive();
    
    public abstract String toString();
}
