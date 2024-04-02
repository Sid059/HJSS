package com.coursework;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
public class UImain {
    public static void main(String[] args) {
        SwimmingSchoolSystem sss = new SwimmingSchoolSystem();

        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        while (flag){
            System.out.println("-------------M E N U-------------");
            for (String s : List.of("\t1. Book a swimming lesson\n" + "\t2. Change/Cancel a booking\n" + "\t3. Attend a swimming lesson\n" + "\t4. Monthly learner report\n" + "\t5. Monthly coach report\n" + "\t6. Register a new learner\n" + "\t0. To exit")) {
                System.out.println(s);
            }
            System.out.println("\nPlease enter your choice of action:");
            int choice = sc.nextInt();
            switch (choice){
                case 1: //book a swimming lesson
                    try {
                        sss.runLessonDisplayInterface();
                        sss.bookLesson();    //still need to hardcode and book lessons for one month.
                    }
                    catch(InputMismatchException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2: //change/cancel a booking
                    try {
                        sss.changeOrCancelBooking();
                    }
                    catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3: //attend a swimming lesson
                    try {
                        sss.attendLessonAndProvideFeedback();
                    }
                    catch (Exception e){
                        System.out.println("An error occurred: " + e.getMessage());
                    }
                    break;

                case 4: //monthly learner report
                    break;
                case 5: //monthly coach report
                    break;
                case 6: //register a new learner
                    try {
                        sss.registerNewLearner();
                    }
                    catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:
                    flag = false;
                    System.out.println("Exiting system...");
                    break;
                default: System.out.println("please select an appropriate action!");
            }
        }
    }
}

