/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resource.allocation.system.ControllerClasses;

import resource.allocation.system.DBConnection;
import resource.allocation.system.ModelClasses.SpecificDay;
import java.sql.*;
import java.time.DayOfWeek;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author RASHMIKA
 */
public class SpecificDayController {
    
    
    public void save(SpecificDay obj){
        
        try {
            Connection con = DBConnection.getDBConnection();
            
            String sql = "INSERT INTO specificday(bookingId,specificDay) VALUES(?,?)";
            
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, obj.getBookingId());
            ps.setString(2, obj.getSpecificDay().toString());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SpecificDayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void delete(String id){
        
        try {
            Connection con = DBConnection.getDBConnection();
            
            String sql = "delete from specificday  WHERE bookingId=?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, id);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SpecificDayController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public DayOfWeek getSpecificDay(String bookingId) {

        DayOfWeek specificDay = null;
        try {
            Connection con = DBConnection.getDBConnection();

            String query = "SELECT specificDay FROM specificday WHERE bookingId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, bookingId);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String specificDayStr = resultSet.getString("specificDay");
                specificDay = DayOfWeek.valueOf(specificDayStr);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
            System.out.println(ex.toString());
        }
        return specificDay;
    }
         
}
