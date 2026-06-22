/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_project;

import java.util.*;
import java.io.*;

public class Login {

    // hashmap mapping usernames (key) to User objects (value)
    public static HashMap<String, User> userInfo = new HashMap<>();
    // Shared scanner for keyboard input
    private static Scanner kb = new Scanner(System.in);

    private static void loadUsers() throws IOException {
        File file = new File("UsersInfo.txt");
        // if file doesn't exist yet, exit the method early
        if (!file.exists()) {
            return;
        }
        Scanner userfile = new Scanner(file);
        // skip the first line (header)
        if (userfile.hasNextLine()) {
            userfile.nextLine(); 
        }
        // read the file line by line
        while (userfile.hasNextLine()) {
            // split values into an array
            String[] data = userfile.nextLine().split("\t");
            // make sure line has all necessary fields before creating a User object
            if (data.length >= 5) {
                // Add user to the map using username as the key
                userInfo.put(data[2], new User(data[0], data[1], data[2], data[3], data[4]));
            }
        }
        userfile.close(); 
    }

    public static void authenticate() throws IOException {
        loadUsers(); // Load existing users from file into memory

        while (true) {
            System.out.print("\nEnter Username (or 'done' to quit): ");
            String username = kb.nextLine();

            // exit condition
            if (username.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter Password: ");
            String password = kb.nextLine();

            // attempt to get user object from the hashmap
            User user = userInfo.get(username);
            int attempts = 1;

            // loop for password verification
            while (attempts <= 3) {
                // Check if user exists and password matches
                if (user != null && user.password.equals(password)) {
                    System.out.println("Login successful. Welcome, " + user.firstName + "!");
                    break; 
                }

                // exit after 3 failures
                if (attempts == 3) {
                    System.out.println("Maximum login attempts reached. Access denied.");
                    System.exit(0); 
                }

                // Prompt for retry
                System.out.println("Invalid login. You have " + (3 - attempts) + " attempts left.");
                System.out.print("Username: ");
                username = kb.nextLine();
                System.out.print("Password: ");
                password = kb.nextLine();
                user = userInfo.get(username); // Re-fetch from map in case username changed
                attempts++;
            }

            // different path changes based on user role
            if (user.role.equalsIgnoreCase("Instructor") || user.role.equalsIgnoreCase("Teacher")) {
                Instructor.menu(kb); // goes to instructor 
            } else {
                QuizSystem.quiz(user, kb); // goes to quiz
            }
        }
    }
}
