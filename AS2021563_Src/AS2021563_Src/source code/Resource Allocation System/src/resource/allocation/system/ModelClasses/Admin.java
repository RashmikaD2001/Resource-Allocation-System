/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resource.allocation.system.ModelClasses;

/**
 *
 * @author RASHMIKA
 * 
 * 
 */

//AS2021563 - O.R.D.K.Perera
public class Admin {
    
    private static Admin admin = null;
    
    private String username = "username";
    private String password = "12345678";
    
    private Admin(){}
    
    public boolean adminLogin(String inputUsername, String inputPassword){
        
        if(username.equals(inputUsername) && password.equals(inputPassword)){
            return true;
        }else{
            return false;
        }
    }
    
    public static Admin getAdmin(){
        
        if(admin == null){
            admin = new Admin();
        }
        
        return admin;
    }
}
