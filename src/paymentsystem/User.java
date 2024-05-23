/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paymentsystem;

/**
 *
 * @author Tony NDOSS
 */
public class User {

    static String username;
    static String firstname;
    static String lastname;
    static String companyname;
//    static String password;

    public void setUsername(String username) {
        User.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setFirstname(String firstname) {
        User.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setLastname(String lastname) {
        User.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setCompanyname(String companyname) {
        User.companyname = companyname;
    }

    public String getCompanyname() {
        return companyname;
    }

//    public void setPassword(String password) {
//        User.password = password;
//    }
//
//    public String getPassword() {
//        return password;
//    }
}
