/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resource.allocation.system.ControllerClasses;

import resource.allocation.system.ModelClasses.Customer;
import resource.allocation.system.DBConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author RASHMIKA
 */
public class CustomerController {

    public void save(Customer customer) {

        try {
            Connection con = DBConnection.getDBConnection();

            String st1 = "INSERT INTO customer(nic, name, tel, email) VALUES(?,?,?,?)";
            String st2 = "INSERT INTO customerlogin(nic, password) VALUES(?,?)";

            PreparedStatement ps1 = con.prepareStatement(st1);
            PreparedStatement ps2 = con.prepareStatement(st2);
            PreparedStatement validate = con.prepareStatement("select nic from customer where nic=?");

            validate.setString(1, customer.getNic());
            ResultSet rs = validate.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Customer is already exist");

            } else {
                ps1.setString(1, customer.getNic());
                ps1.setString(2, customer.getName());
                ps1.setString(3, customer.getTelNo());
                ps1.setString(4, customer.getEmail());

                ps1.executeUpdate();

                ps2.setString(1, customer.getNic());
                ps2.setString(2, customer.getLogin().getPassword());

                ps2.executeUpdate();

                JOptionPane.showMessageDialog(null, "Account created");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
    
    public Customer get(String nic){
        
        Customer customer = new Customer();
        try{
            Connection con = DBConnection.getDBConnection();
            
            String sql = "SELECT * FROM customer WHERE nic=?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, nic);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
              customer.setNic(rs.getString("nic"));
              customer.setName(rs.getString("name"));
              customer.setTelNo(rs.getString("tel"));
              customer.setEmail(rs.getString("email"));
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return customer;
    }
    
    public void delete(String nic){
        
        CustomerLoginController del = new CustomerLoginController();
        
        try {
            Connection con = DBConnection.getDBConnection();
            String sql = "delete from customer  WHERE nic=?";
            PreparedStatement ps = con.prepareStatement(sql);

            PreparedStatement validate = con.prepareStatement("SELECT nic FROM customer WHERE nic=?");
            validate.setString(1, nic);
            ResultSet rs = validate.executeQuery();

            if (rs.next()) {

                ps.setString(1, nic);
                ps.executeUpdate();
                del.delete(nic);
                JOptionPane.showMessageDialog(null, "Customer Deleted");
            } else {
                JOptionPane.showMessageDialog(null, "No Customer exist for releveant NIC");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public ArrayList<Customer> getAllCustomers(){
        
        ArrayList<Customer> customers = new ArrayList<>();
        
        try {
            
            
            Connection con = DBConnection.getDBConnection();
            
            String sql = "SELECT nic FROM customer";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                String nic = rs.getString("nic");
                Customer customer = get(nic);
                customers.add(customer);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }
}
