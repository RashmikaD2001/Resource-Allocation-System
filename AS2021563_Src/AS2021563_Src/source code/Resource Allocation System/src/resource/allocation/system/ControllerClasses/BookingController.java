/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resource.allocation.system.ControllerClasses;

import resource.allocation.system.ControllerClasses.SpecificDayController;
import resource.allocation.system.ModelClasses.Booking;
import resource.allocation.system.DBConnection;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import resource.allocation.system.ModelClasses.ContinuesPeriod;
import resource.allocation.system.ModelClasses.Customer;
import resource.allocation.system.ModelClasses.Hall;
import resource.allocation.system.ModelClasses.SpecificDay;
import java.util.Optional;
import resource.allocation.system.ViewClasses.HallBookingForm;
import resource.allocation.system.ViewClasses.PaymentUI;

/**
 *
 * @author RASHMIKA
 */
public class BookingController {

    public void save(Booking booking, String str) {

        try {
            Connection con = DBConnection.getDBConnection();

            String sql = "INSERT INTO booking(startDate,endDate,bill,nic,idHall,bookingtype) VALUES(?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, booking.getStartDate().toString());
            ps.setString(2, booking.getEndDate().toString());
            ps.setDouble(3, booking.getBill());
            ps.setString(4, booking.getCustomer().getNic());
            ps.setString(5, booking.getHall().getIdHall());
            ps.setString(6, str);

            ps.executeUpdate();

            String query = "SELECT * FROM booking ORDER BY bookingId DESC LIMIT 1";

            PreparedStatement idPs = con.prepareStatement(query);
            ResultSet resultSet = idPs.executeQuery();

            if (resultSet.next()) {
                String id = resultSet.getString("bookingId");
                booking.setBookingId(id);
            }

            JOptionPane.showMessageDialog(null, "        Booked!\n" + booking.toString());

            
            
            /*int dialogResult = JOptionPane.showConfirmDialog(null, "Booked!\n" + booking.toString() + "\n\nDo you pay now?", "Confirmation", JOptionPane.YES_NO_OPTION);
            
            if (dialogResult == JOptionPane.YES_OPTION){
                PaymentUI pu = new PaymentUI();
                
                
                pu.setVisible(true);
                pu.getIdtxt().setText(booking.getBookingId());
                
                hbf.dispose();
            }*/

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
            System.out.println(ex.toString());
        }
    }

    public void update(String bookingId, String newDate) {

        try {
            Connection con = DBConnection.getDBConnection();
            String st = "UPDATE booking SET endDate=? WHERE bookingId=?";
            PreparedStatement ps = con.prepareStatement(st);

            PreparedStatement validate = con.prepareStatement("SELECT bookingId FROM booking WHERE bookingId=?");
            validate.setString(1, bookingId);
            ResultSet rs = validate.executeQuery();

            if (rs.next()) {
                ps.setString(1, newDate);
                ps.setString(2, bookingId);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Updated");
            } else {
                JOptionPane.showMessageDialog(null, "No booking exists for the provided bookingId.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String delete(String bookingId) {

        String bookingType = "";

        try {
            Connection con = DBConnection.getDBConnection();
            PreparedStatement ps = con.prepareStatement("SELECT bookingtype FROM booking WHERE bookingId=?");
            ps.setString(1, bookingId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                bookingType = rs.getString("bookingtype");

                PreparedStatement deletePs = con.prepareStatement("DELETE FROM booking WHERE bookingId=?");
                deletePs.setString(1, bookingId);
                deletePs.executeUpdate();

                JOptionPane.showMessageDialog(null, "Booking with ID: " + bookingId + " has been deleted.");
            } else {
                JOptionPane.showMessageDialog(null, "No booking found for given booking ID.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error deleting booking: " + ex.getMessage());
        }

        return bookingType;
    }

    public String getBookingType(String bookingId) {
        String bookingType = "";
        try {
            Connection con = DBConnection.getDBConnection();

            String query = "SELECT bookingtype FROM booking WHERE bookingId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, bookingId);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                bookingType = resultSet.getString("bookingtype");
            } else {
                JOptionPane.showMessageDialog(null, "Booking ID " + bookingId + " does not exist.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
            System.out.println(ex.toString());
        }
        return bookingType;
    }

    /*public Booking get(String bookingId){
        
        try {
            Connection con = DBConnection.getDBConnection();
            
            String sql = "SELECT * FROM booking WHERE bookingId=?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            PreparedStatement validate = con.prepareStatement("select bookindId from booking where bookingId=?");
            
            ps.setString(1,bookingId);
            
            ResultSet rs = validate.executeQuery();
            
            if(rs.next()){
                String result1 = rs.getString("startDate");
                String result2 = rs.getString("endDate");
                double result3 = rs.getDouble("bill");
                String result4 = rs.getString("nic");
                String result5 = rs.getString("idHall");
                String result6 = rs.getString("bookingtype");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }*/
    public Optional<Booking> getOptional(String bookingId) {

        /*String bookingType = getBookingType(bookingId);
        Booking booking;

        try {
            Connection con = DBConnection.getDBConnection();

            String sql = "SELECT * FROM booking WHERE bookingId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, bookingId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                String result1 = rs.getString("startDate");
                String result2 = rs.getString("endDate");

                String result4 = rs.getString("nic");
                String result5 = rs.getString("idHall");

                LocalDate start = LocalDate.parse(result1);
                LocalDate end = LocalDate.parse(result2);

                HallController hc = new HallController();
                Hall hall = hc.get(result5);

                CustomerController cc = new CustomerController();
                Customer customer = cc.get(result4);
                if ("Continues Period".equals(bookingType)) {

                    booking = new ContinuesPeriod(bookingId, customer, hall, start, end);
                    return booking;
                } else {

                    SpecificDayController sdc = new SpecificDayController();
                    //sdc.getSpecificDay(bookingId);

                    booking = new SpecificDay(sdc.getSpecificDay(bookingId), bookingId, customer, hall, start, end);
                    return booking;
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return booking;*/
        String bookingType = getBookingType(bookingId);
        Optional<Booking> booking = Optional.empty();

        try {
            Connection con = DBConnection.getDBConnection();

            String sql = "SELECT * FROM booking WHERE bookingId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, bookingId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Code for populating booking details

                String result1 = rs.getString("startDate");
                String result2 = rs.getString("endDate");

                String result4 = rs.getString("nic");
                String result5 = rs.getString("idHall");

                LocalDate start = LocalDate.parse(result1);
                LocalDate end = LocalDate.parse(result2);

                HallController hc = new HallController();
                Hall hall = hc.get(result5);

                CustomerController cc = new CustomerController();
                Customer customer = cc.get(result4);

                if ("Continues Period".equals(bookingType)) {
                    booking = Optional.of(new ContinuesPeriod(bookingId, customer, hall, start, end));
                } else {
                    SpecificDayController sdc = new SpecificDayController();
                    //sdc.getSpecificDay(bookingId);

                    booking = Optional.of(new SpecificDay(sdc.getSpecificDay(bookingId), bookingId, customer, hall, start, end));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return booking;

    }

    public ArrayList<Booking> getBooking() {

        ArrayList<Booking> bookings = new ArrayList<>();

        try {

            Connection con = DBConnection.getDBConnection();

            String sql = "SELECT bookingId FROM booking";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String id = rs.getString("bookingId");
                Booking booking = get(id);
                bookings.add(booking);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bookings;
    }

    public boolean isBooked(String idHall, LocalDate startDate, LocalDate endDate) {
        try {
            Connection con = DBConnection.getDBConnection();
            String sql = "SELECT * FROM booking WHERE idHall = ? AND ((startDate <= ? AND endDate >= ?) OR (startDate <= ? AND endDate >= ?) OR (startDate >= ? AND endDate <= ?))";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idHall);
            ps.setString(2, startDate.toString());
            ps.setString(3, startDate.toString());
            ps.setString(4, endDate.toString());
            ps.setString(5, endDate.toString());
            ps.setString(6, startDate.toString());
            ps.setString(7, endDate.toString());
            ResultSet rs = ps.executeQuery();

            return rs.next(); // If rs.next() returns true, it means there is an overlap in the dates

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean isBookingExist(String bookingId) {

        try {
            Connection con = DBConnection.getDBConnection();
            String sql = "SELECT * FROM booking WHERE bookingId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, bookingId);
            ResultSet rs = ps.executeQuery();

            return rs.next(); // If rs.next() returns true, it means the booking exists

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public Booking get(String bookingId) {
        String bookingType = getBookingType(bookingId);
        Booking booking = null; // Initialize booking to null

        try {
            Connection con = DBConnection.getDBConnection();

            String sql = "SELECT * FROM booking WHERE bookingId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, bookingId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Code for populating booking details

                String result1 = rs.getString("startDate");
                String result2 = rs.getString("endDate");

                String result4 = rs.getString("nic");
                String result5 = rs.getString("idHall");

                LocalDate start = LocalDate.parse(result1);
                LocalDate end = LocalDate.parse(result2);

                HallController hc = new HallController();
                Hall hall = hc.get(result5);

                CustomerController cc = new CustomerController();
                Customer customer = cc.get(result4);

                if ("Continues Period".equals(bookingType)) {
                    booking = new ContinuesPeriod(bookingId, customer, hall, start, end);
                } else {
                    SpecificDayController sdc = new SpecificDayController();
                    //sdc.getSpecificDay(bookingId);

                    booking = new SpecificDay(sdc.getSpecificDay(bookingId), bookingId, customer, hall, start, end);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return booking;
    }
}
