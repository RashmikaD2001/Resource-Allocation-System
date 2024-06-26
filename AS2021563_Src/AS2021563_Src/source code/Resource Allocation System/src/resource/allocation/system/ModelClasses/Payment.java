/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resource.allocation.system.ModelClasses;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RASHMIKA
 */
public class Payment {
    
    private String paymentId;
    private PaymentMethod method;
    private LocalDate paidDate;

    public Payment(String paymentId, PaymentMethod method, LocalDate paidDate) {
        setMethod(method);
        setPaymentId(paymentId);
        setPaidDate(paidDate);
    }

    public Payment(PaymentMethod method) {
        setMethod(method);
        setPaidDate();
    }

    public Payment() {
        
    }

    
    public void setPaymentId(String paymentId){
        this.paymentId = paymentId;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    
    public void setPaidDate(LocalDate paidDate){
        this.paidDate = paidDate;
    }

    public void setPaidDate() {
        
        Date paidD = new Date();
        LocalDate paidDate = LocalDate.ofInstant(paidD.toInstant(), ZoneId.systemDefault());
        
        this.paidDate = paidDate;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public PaymentMethod getMethod() {
        return method;
    }


    public LocalDate getPaidDate() {
        return paidDate;
    }

    @Override
    public String toString() {
        return "Payment Deatils - " + "paymentId=" + paymentId + ", method=" + method + ", paidDate=" + paidDate;
    }
    
    
}
