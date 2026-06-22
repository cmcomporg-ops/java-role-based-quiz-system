/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_project;

import java.util.*;
import java.io.*;

public class Instructor {
    public static void menu(Scanner kb) throws IOException {
        while (true) {
            // instructor menu options 
            System.out.println("\n--- Instructor Menu ---");
            System.out.println("1. Register Student\n2. Display Stats\n3. Add Question\n4. Logout");
            System.out.print("Selection: ");
            String choice = kb.nextLine();
            
            if (choice.equals("4")) break;
            if (choice.equals("1")) register(kb);
            else if (choice.equals("2")) showStats();
            else if (choice.equals("3")) addQuestion(kb);
            else System.out.println("Invalid selection (1-4).");
        }
    }

private static void showStats() throws IOException {
    File f = new File("QuizResults.txt");
    if (!f.exists()) { 
        System.out.println("No data found."); 
        return; 
    }
    
    Scanner sc = new Scanner(f);
    ArrayList<Integer> scores = new ArrayList<>();
    HashSet<String> students = new HashSet<>();
    int passes = 0;

    // Load data from file
    while (sc.hasNextLine()) {
        String[] d = sc.nextLine().split("\t");
        int s = Integer.parseInt(d[1]); // convert scores to integer for stats
        scores.add(s);
        students.add(d[0]); // hashset ignores duplicate usernames
        if (s >= 6) passes++; // 60% or higher is passing
    }
    sc.close();
    
    // safety check if file is empty
    if (scores.isEmpty()) return;

    int lowest = scores.get(0);
    int highest = scores.get(0);
    double sum = 0;

    for (int s : scores) {
        if (s < lowest) {
            lowest = s; // update lowest if current score is smaller
        }
        if (s > highest) {
            highest = s; // update highest if current score is larger
        }
        sum += s; // add up sum of scores for the average
    }

    // calculations and display
    double average = sum / scores.size();
    double passRate = ((double) passes / scores.size()) * 100;

    System.out.println("--- Instructor Statistics ---");
    System.out.println("Lowest Score: " + lowest);
    System.out.println("Highest Score: " + highest);
    System.out.println("Average Score: " + String.format("%.2f", average)); // %.2f for formmating
    System.out.println("Total Quizzes Taken: " + scores.size());
    System.out.println("Unique Students Participated: " + students.size());
    System.out.println("Overall Pass Rate: " + String.format("%.2f", passRate) + "%"); // %.2f for formmating
}

    private static void register(Scanner kb) throws IOException {
        // collect new user data
        System.out.print("First Name: "); String fn = kb.nextLine(); 
        System.out.print("Last Name: "); String ln = kb.nextLine();
        System.out.print("Username: "); String un = kb.nextLine();
        System.out.print("Password: "); String pw = kb.nextLine();
        
        // opens file to add new user to the end
        PrintWriter out = new PrintWriter(new FileWriter("UsersInfo.txt", true)); 
        out.print("\n" + fn + "\t" + ln + "\t" + un + "\t" + pw + "\tStudent");
        out.close();
        // updates hasmap so the new user can log in immediately without restart
        Login.userInfo.put(un, new User(fn, ln, un, pw, "Student"));
        System.out.println("Registration complete.");
    }

    private static void addQuestion(Scanner kb) throws IOException {
        System.out.print("Question: "); 
        String q = kb.nextLine();
        System.out.print("Answer (True/False): "); 
        String a = kb.nextLine(); 
        
        // adds new question text to TestBank.txt
        PrintWriter qOut = new PrintWriter(new FileWriter("TestBank.txt", true));
        qOut.println("\n" + q); qOut.close();
        // adds matching answer to Answers.txt
        PrintWriter aOut = new PrintWriter(new FileWriter("Answers.txt", true));
        aOut.println("\n" + a); aOut.close();
    }
}
