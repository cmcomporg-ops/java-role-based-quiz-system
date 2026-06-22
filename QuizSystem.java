/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_project;

import java.util.*;
import java.io.*;

public class QuizSystem {

    public static int quiz(User student, Scanner kb) throws IOException {
        System.out.println("Starting the quiz...");
        // Logic for date formatting adapted from W3Schools: https://www.w3schools.com/java/java_date.asp
        String startTime = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("MM-dd-yyyy_HH:mm:ss"));
        //get the start time in milliseconds
        long start = System.currentTimeMillis();
        // load questions into arraylist
        File questionsFile = new File("TestBank.txt");
        Scanner qScanner = new Scanner(questionsFile);
        ArrayList<String> questions = new ArrayList<>();
        while (qScanner.hasNextLine()) {
            questions.add(qScanner.nextLine());
        }
        qScanner.close();

        // load answers into arrayList
        File answersFile = new File("Answers.txt");
        Scanner aScanner = new Scanner(answersFile);
        ArrayList<String> answers = new ArrayList<>();
        while (aScanner.hasNextLine()) {
            answers.add(aScanner.nextLine());
        }
        aScanner.close();

        int score = 0;
        String quizDetails = "";
        Random rand = new Random();

        // hashSet for unique random question selection 
        HashSet<Integer> testQuestions = new HashSet<>();
        while (testQuestions.size() < 10) {
            // adds random questioms number, if already exists, hashset size doesn't increase
            testQuestions.add(rand.nextInt(questions.size()));
        }

        int qNum = 1;
        for (int index : testQuestions) {
            System.out.println("\nQuestion " + qNum + ": " + questions.get(index));
            System.out.print("Enter True(T)/False(F): ");
            String userAnswer = kb.nextLine();

            // validates input 
            while (!(userAnswer.equalsIgnoreCase("T")
                    || userAnswer.equalsIgnoreCase("F")
                    || userAnswer.equalsIgnoreCase("True")
                    || userAnswer.equalsIgnoreCase("False"))) {
                System.out.println("Invalid input. Try again.");
                System.out.print("Enter True(T)/False(F): ");
                userAnswer = kb.nextLine();
            }
            // Compare the first character (T or F) of both user input and key
            if (userAnswer.toLowerCase().charAt(0) == answers.get(index).toLowerCase().charAt(0)) {
                score++;
            }

            // Record details for the final report
            quizDetails += "Question: " + questions.get(index) + "\n"
                    + "Your Answer: " + userAnswer + " | Correct Answer: " + answers.get(index) + "\n\n";
            qNum++;
        }
        // get end time after the loop finishes
        long end = System.currentTimeMillis();
        
        // calculate difference and convert to seconds
        long elapsed = (end - start) / 1000;
        String elapsedTime = elapsed + " seconds";
        
        // Pass all infor collected from quiz to results
        results(student, score, quizDetails, startTime, elapsedTime);
        return score;
    }

    public static void results(User student, int score, String details, String startTime, String elapsedTime) throws IOException {
        // Create a unique filename for the student's attempt
        String filename = student.username + "_COSC_3053_Quiz_" + startTime + ".txt";

        // building report  
        String reportHeader = "Quiz Report\n"
                + "First Name: " + student.firstName + "\n"
                + "Last Name: " + student.lastName + "\n"
                + "Score: " + score + "/10\n"
                + "Elapsed Time: " + elapsedTime + "\n"
                + "--------------------------\n";

        // display report
        System.out.println("\n" + reportHeader + details);

        // writes results to output file 
        PrintWriter outputFile = new PrintWriter(new FileWriter(filename));
        outputFile.println(reportHeader);
        outputFile.println(details);
        outputFile.close();

        System.out.println("Results successfully saved to: " + filename);

        // adds this attempt to a log file for instructor's stats 
        PrintWriter log = new PrintWriter(new FileWriter("QuizResults.txt", true));
        log.println(student.username + "\t" + score);
        log.close();
    }
}
