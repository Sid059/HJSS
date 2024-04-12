package com.coursework;

import java.util.List;
import java.util.Scanner;

public class UImain {
    public static void main(String[] args) {
        SwimmingSchoolSystem sss = new SwimmingSchoolSystem();

        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.println("\n\033[32m#-------------M E N U-------------#\033[0m");
            for (String s : List.of("\033[32m\t1. Book a swimming lesson\n" + "\t2. Change/Cancel a booking\n" + "\t3. Attend a swimming lesson\n" + "\t4. Monthly learner report\n" + "\t5. Monthly coach report\n" + "\t6. Register a new learner\n" + "\t7. Show Pre-registered learners\n" + "\t0. To exit\033[0m")) {
                System.out.println(s);
            }
            int choice = 0;
            int counter = 0;
            while(counter == 0){        // keeps on iterating until user enters a number.
                try {
                    System.out.println("\nPlease enter your choice of action:");
                    choice = sc.nextInt();
                    counter = 1;
                } catch (Exception e) {
                    System.out.println("\033[31mError! Enter an appropriate number.\033[0m");
                    sc.next();
                }
            }
            switch (choice) {
                case 1: //book a swimming lesson
                    try {
                        sss.runLessonDisplayInterface();
                        sss.bookLesson();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        System.out.println("\033[32mRedirecting to main menu...\033[0m");
                    }
                    break;
                case 2: //change/cancel a booking
                    try {
                        sss.changeOrCancelBooking();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        System.out.println("\033[32mRedirecting to main menu...\033[0m");
                    }
                    break;
                case 3: //attend a swimming lesson
                    try {
                        sss.attendLessonAndProvideFeedback();
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        System.out.println("\033[32mRedirecting to main menu...\033[0m");
                    }
                    break;
                case 4: //monthly learner report
                    sss.generateMonthlyLearnerReport();
                    break;
                case 5: //monthly coach report
                    sss.generateMonthlyCoachReport();
                    break;
                case 6: //register a new learner
                    sss.registerNewLearner();
                    break;
                case 7: //view all learners
                    sss.showAllLearners();
                    break;
                case 0:
                    flag = false;
                    System.out.println("\033[32mExiting system...\033[0m");
                    break;
                default:
                    System.out.println("\033[31mError! Please select an appropriate action.\033[0m");
            }
        }
    }
}



