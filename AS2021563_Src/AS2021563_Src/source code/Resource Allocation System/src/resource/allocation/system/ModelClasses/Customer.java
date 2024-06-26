/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resource.allocation.system.ModelClasses;

/**
 *
 * @author RASHMIKA
 */
public class Customer {
    
    private String name;
    private String nic;
    private String telNo;
    private String email;
    private CustomerLogin login;

    public Customer(String name, String nic, String telNo, String email, CustomerLogin login) {
        setName(name);
        setNic(nic);
        setTelNo(telNo);
        setEmail(email);
        setLogin(login);
    }

    public Customer(String name, String nic, String telNo, String email) {
        setName(name);
        setNic(nic);
        setTelNo(telNo);
        setEmail(email);
        setLogin(null);
    }

    public Customer() {
        setName("");
        setNic("");
        setTelNo("");
        setEmail("");
        setLogin(null);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(CustomerLogin login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public String getNic() {
        return nic;
    }

    public String getTelNo() {
        return telNo;
    }

    public String getEmail() {
        return email;
    }

    public CustomerLogin getLogin() {
        return login;
    }
    
    public String toString(){
        return "Customer name - "+name+"\nNIC Number - "+nic+"\nTelephone number - "+telNo+"\nEmail - "+email;
    }
}
