/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resource.allocation.system.ModelClasses;

/**
 *
 * @author RASHMIKA
 */
public class CustomerLogin {
    
    private String username;
    private String password;

    public CustomerLogin(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public CustomerLogin() {
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
}
