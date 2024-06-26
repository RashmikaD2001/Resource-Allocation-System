/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resource.allocation.system;
import java.sql.*;

/**
 *
 * @author RASHMIKA
 */

//AS2021563 - O.R.D.K.Perera
public class DBConnection {
    
    static Connection con = null;
    
    private DBConnection(){
        
    }
    
    public static Connection getDBConnection(){
        try{
            if(con==null){
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3308/abccompany", "root", "");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return con;
    }
    
}
