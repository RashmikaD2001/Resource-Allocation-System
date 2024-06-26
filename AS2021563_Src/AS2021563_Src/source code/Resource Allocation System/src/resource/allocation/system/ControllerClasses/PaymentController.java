/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resource.allocation.system.ControllerClasses;

import resource.allocation.system.ModelClasses.Payment;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import resource.allocation.system.DBConnection;
import resource.allocation.system.ModelClasses.PaymentMethod;

/**
 *
 * @author RASHMIKA
 */
public class PaymentController {

    public void save(Payment payment, String bookingId) {

        try {
            Connection con = DBConnection.getDBConnection();
            String sql = "INSERT INTO payment(method,paidDate,bookingId) values(?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            PreparedStatement validate = con.prepareStatement("select bookingId from booking where bookingId=?");

            validate.setString(1, bookingId);
            ResultSet rs = validate.executeQuery();

            if (rs.next()) {
                ps.setString(1, payment.getMethod().toString());
                ps.setString(2, payment.getPaidDate().toString());

                ps.setString(3, bookingId);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Bill is paid");
            } else {
                JOptionPane.showMessageDialog(null, "No booking exist");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void isPaid(String id) {

        try {
            Connection con = DBConnection.getDBConnection();
            PreparedStatement validate = con.prepareStatement("SELECT * FROM payment WHERE bookingId=? OR paymentId=?");

            validate.setString(1, id);
            validate.setString(2, id);
            ResultSet rs = validate.executeQuery();

            if (rs.next()) {
                String bookingId = rs.getString("bookingId");
                JOptionPane.showMessageDialog(null, "Paid for Booking ID: " + bookingId + "\n" + getPayment(bookingId).toString());

            } else {
                JOptionPane.showMessageDialog(null, "Not Paid");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Payment getPayment(String bookingId) {

        Payment payment = new Payment();

        try {
            Connection con = DBConnection.getDBConnection();

            String sql = "SELECT * FROM payment WHERE bookingId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, bookingId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String id = rs.getString("paymentId");
                PaymentMethod method = PaymentMethod.valueOf(rs.getString("method"));
                LocalDate date = LocalDate.parse(rs.getString("paidDate"));

                payment.setPaymentId(id);
                payment.setPaidDate(date);
                payment.setMethod(method);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return payment;
    }

    public ArrayList<Payment> payments() {

        ArrayList<Payment> pay = new ArrayList<>();

        try {

            Connection con = DBConnection.getDBConnection();

            String sql = "SELECT bookingId FROM payment";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String id = rs.getString("bookingId");
                Payment payment = getPayment(id);
                pay.add(payment);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return pay;
    }

    public String getBookingIdByPaymentId(String paymentId) {

        String bookingId = "";

        try {
            Connection con = DBConnection.getDBConnection();
            String sql = "SELECT bookingId FROM payment WHERE paymentId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, paymentId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                bookingId = rs.getString("bookingId");
            } else {
                JOptionPane.showMessageDialog(null, "Payment ID not found");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bookingId;
    }

    public boolean isPaymentIdExists(String paymentId) {
        boolean exists = false;

        try {
            Connection con = DBConnection.getDBConnection();
            String sql = "SELECT * FROM payment WHERE paymentId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, paymentId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }

    public void deletePaymentByBookingId(String bookingId) {
        try {
            Connection con = DBConnection.getDBConnection();
            String sql = "DELETE FROM payment WHERE bookingId=?";
            PreparedStatement ps = con.prepareStatement(sql);

            PreparedStatement validate = con.prepareStatement("SELECT bookingId FROM payment WHERE bookingId=?");
            validate.setString(1, bookingId);
            ResultSet rs = validate.executeQuery();

            if (rs.next()) {
                ps.setString(1, bookingId);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Payment Deleted");
            } else {
                JOptionPane.showMessageDialog(null, "No payment record exists for the provided booking ID");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public boolean isBookingIdExist(String bookingId) {
        try {
            Connection con = DBConnection.getDBConnection();
            PreparedStatement validate = con.prepareStatement("SELECT bookingId FROM payment WHERE bookingId=?");
            validate.setString(1, bookingId);
            ResultSet rs = validate.executeQuery();

            if (rs.next()) {
                // Booking ID exists in the payment table
                return true;
            } else {
                // Booking ID does not exist in the payment table
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false; // Error occurred, return false
        }
    }

}
