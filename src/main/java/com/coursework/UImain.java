package com.coursework;

import java.util.List;
import java.util.Scanner;

public class UImain {
    public static void main(String[] args) {
        //creating pre-registered learners, coaches, and lessons
        AddLearnersCoachesLessons.addLearners();
        AddLearnersCoachesLessons.addCoaches();
        AddLearnersCoachesLessons.addLessons();
        //it automatically books lessons, attends lessons, cancels lessons for pre-registered learners
        AutoLessonManager lessonManager = new AutoLessonManager();
        lessonManager.autoBookLessonsForLearners();
        lessonManager.autoCancelBookingsForLearners();
        lessonManager.autoAttendAndRateLessons();

        SwimmingSchoolSystem sss = new SwimmingSchoolSystem();
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        System.out.println("\033[32mWelcome to Hatfiled Junior Swimming School System.\033[0m");
        try {
            Thread.sleep(1000);
            System.out.println("\033[32mLoading main menu...\033[0m");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while (flag) {
            System.out.println("\n\033[32m#-------------M E N U-------------#\033[0m");
            for (String s : List.of("\033[32m\t1. Book a swimming lesson.\n" + "\t2. Change/Cancel a booking.\n" + "\t3. Attend a swimming lesson.\n" + "\t4. View monthly learner report.\n" + "\t5. View monthly coach report.\n" + "\t6. Register a new learner.\n" + "\t7. View pre-registered learners.\n" + "\t8. Exit.\033[0m")) {
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
                    } catch (IllegalArgumentException e) {
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
                    try {
                        sss.registerNewLearner();
                    } catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                        System.out.println("\033[32mRedirecting to main menu...\033[0m");
                    }
                    break;
                case 7: //view all learners
                    sss.showAllLearners();
                    break;
                case 8:
                    flag = false;
                    System.out.println("\033[32mExiting system...\033[0m");
                    break;
                default:
                    System.out.println("\033[31mError! Please select an appropriate action.\033[0m");
            }
        }
    }
}
