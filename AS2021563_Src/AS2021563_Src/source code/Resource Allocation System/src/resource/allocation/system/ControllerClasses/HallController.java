/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resource.allocation.system.ControllerClasses;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import resource.allocation.system.DBConnection;
import resource.allocation.system.ModelClasses.Hall;
import resource.allocation.system.ModelClasses.HallType;

/**
 *
 * @author RASHMIKA
 */
public class HallController {

    public void save(Hall hall) {

        try {
            Connection con = DBConnection.getDBConnection();
            String sql = "INSERT INTO hall(idHall,size) VALUES(?,?)";
            PreparedStatement validate = con.prepareStatement("select idHall from hall where idHall=?");

            validate.setString(1, hall.getIdHall());
            ResultSet rs = validate.executeQuery();

            if (rs.next()) {

                JOptionPane.showMessageDialog(null, "Hall ID is already exist");

            } else {

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, hall.getIdHall());
                ps.setString(2, hall.getType().toString());
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Hall Added");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "error");
        }
    }

    public void Edit(Hall hall) {

        try {
            Connection con = DBConnection.getDBConnection();
            String sql = "UPDATE hall SET size=? WHERE idHall=?";
            PreparedStatement validate = con.prepareStatement("SELECT idHall FROM hall WHERE idHall=?");
            PreparedStatement ps = con.prepareStatement(sql);

            validate.setString(1, hall.getIdHall());
            ResultSet rs = validate.executeQuery();

            if (rs.next()) {
                ps.setString(1, hall.getType().toString());
                ps.setString(2, hall.getIdHall());
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Hall Updated");

            } else {

                JOptionPane.showMessageDialog(null, "Hall ID is not exist");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "error");
        }

    }

    public void delete(String idHall) {

        try {
            Connection con = DBConnection.getDBConnection();
            String sql = "delete from hall WHERE idHall=?";
            PreparedStatement validate = con.prepareStatement("SELECT idHall FROM hall WHERE idHall=?");

            PreparedStatement ps = con.prepareStatement(sql);
            validate.setString(1, idHall);
            ResultSet rs = validate.executeQuery();

            if (rs.next()) {
                ps.setString(1, idHall);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Hall Deleted");
            } else {
                JOptionPane.showMessageDialog(null, "Hall ID is not exist");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "error");
        }
    }

    public Hall get(String id) {

        Hall hall = new Hall();

        try {
            Connection con = DBConnection.getDBConnection();

            String sql = "SELECT * FROM hall WHERE idHall=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                hall.setIdHall(rs.getString("idHall"));
                hall.setType(HallType.valueOf(rs.getString("size")));

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return hall;
    }

    public ArrayList<Availability> checkAvailability() {

        ArrayList<Availability> availabilityList = new ArrayList<>();

        try {

            Connection con = DBConnection.getDBConnection();
            String sql = "SELECT idHall,size FROM hall";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String idHall = rs.getString("idHall");
                HallType type = HallType.valueOf(rs.getString("size"));

                Hall hall = new Hall(idHall, type);

                String query = "SELECT startDate,endDate FROM booking WHERE idHall = ? ORDER BY endDate DESC LIMIT 1";
                PreparedStatement bookingPs = con.prepareStatement(query);
                bookingPs.setString(1, "idHall");

                ResultSet bookingRs = bookingPs.executeQuery();

                if (bookingRs.next()) {
                    String startDate = bookingRs.getString("startDate");
                    String endDate = bookingRs.getString("endDate");

                    Availability a = new Availability(hall, startDate, endDate);

                    availabilityList.add(a);
                } else {
                    Availability a = new Availability(hall, "No booking", "No booing");

                    availabilityList.add(a);

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(HallController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return availabilityList;
    }

    public boolean isValidIdHall(String idHall) {
        try {
            Connection con = DBConnection.getDBConnection();
            PreparedStatement validate = con.prepareStatement("SELECT idHall FROM hall WHERE idHall=?");
            validate.setString(1, idHall);
            ResultSet rs = validate.executeQuery();

            return rs.next(); // If rs.next() returns true, it means the idHall exists

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Hall> getAllHalls() {
        ArrayList<Hall> hallList = new ArrayList<>();

        try {
            Connection con = DBConnection.getDBConnection();
            String sql = "SELECT * FROM hall";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String id = rs.getString("idHall");
                String size = rs.getString("size");
                Hall hall = new Hall(id, HallType.valueOf(size));
                hallList.add(hall);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving halls");
        }

        return hallList;
    }

}
