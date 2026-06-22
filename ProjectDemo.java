/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_project;

import java.io.IOException;

public class ProjectDemo {
    public static void main(String[] args) {
        // visual header for output
        System.out.println("=====================");
        System.out.println("DATA STRUCTURES LOGIN");
        System.out.println("=====================");
        
        try {
            // calls authentication method to start the program 
            Login.authenticate();
        } catch (IOException e) {
            // Error handling if UsersInfo.txt or other required files are missing/unreadable
            System.out.println("Error: Required data files missing.");
        }      
        // Prints out once infinite loops in Login/Instructor/Student are broken
        System.out.println("\nSystem closed.");
    }
}
