/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resource.allocation.system.ControllerClasses;

import resource.allocation.system.DBConnection;
import resource.allocation.system.ModelClasses.CustomerLogin;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author RASHMIKA
 */
public class CustomerLoginController {

    public CustomerLogin logIn(String nic) {

        CustomerLogin login = new CustomerLogin();

        try {
            Connection con = DBConnection.getDBConnection();
            String st = "SELECT * FROM customerlogin WHERE nic=?";

            PreparedStatement ps = con.prepareStatement(st);

            ps.setString(1, nic);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                login.setPassword(rs.getString("password"));
                login.setUsername(nic);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return login;
    }

    public void delete(String nic) {

        try {
            Connection con = DBConnection.getDBConnection();
            String st = "DELETE FROM customerlogin WHERE nic=?";
            PreparedStatement ps = con.prepareStatement(st);
            ps.setString(1, nic);
            ps.executeUpdate();
            // Optionally, you can also delete from other related tables here
        } catch (SQLException ex) {
            Logger.getLogger(CustomerLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update(CustomerLogin customerLogin) {

        try {
            Connection con = DBConnection.getDBConnection();
            String sql = "UPDATE customerlogin SET password=? WHERE nic=?";
            PreparedStatement ps = con.prepareStatement(sql);
            PreparedStatement validate = con.prepareStatement("SELECT nic FROM customerlogin WHERE nic=?");

            validate.setString(1, customerLogin.getUsername());

            ResultSet rs = validate.executeQuery();
            if (rs.next()) {
                ps.setString(1, customerLogin.getUsername());
                ps.setString(2, customerLogin.getPassword());
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Password changed");
            } else {
                JOptionPane.showMessageDialog(null, "No customer exists in the system under the given NIC Number");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
