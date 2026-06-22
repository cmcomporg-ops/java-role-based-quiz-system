/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ds_project;

public class User {
    // fields to store user details
    String firstName, lastName, username, password, role; 

    // constructor to initialize new User object from file data or registration
    public User(String fn, String ln, String un, String pw, String r) { 
        firstName = fn; // Sets first name
        lastName = ln;  // Sets last name
        username = un;  // Sets unique login name
        password = pw;  // Sets login password
        role = r;       // Sets role (Student/Instructor/Teacher)
    }
}
