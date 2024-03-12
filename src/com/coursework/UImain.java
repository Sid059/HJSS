package com.coursework;

import java.util.List;
import java.util.Scanner;
public class UImain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        while (flag){
            System.out.println("-------------M E N U-------------");
            for (String s : List.of("\t1. Book a swimming lesson\n" + "\t2. Change/Cancel a booking\n" + "\t3. Attend a swimming lesson\n" + "\t4. Monthly learner report\n" + "\t5. Monthly coach report\n" + "\t6. Register a new learner\n" + "\t0. To exit")) {
                System.out.println(s);
            }
            System.out.println("Please enter your choice of action:");
            int choice = sc.nextInt();
            switch (choice){
                case 1: //book a swimming lesson
                    break;
                case 2: //change/cancel a booking
                    break;
                case 3: //attend a swimming lesson
                    break;
                case 4: //monthly learner report
                    break;
                case 5: //monthly coach report
                    break;
                case 6: //register a new learner
                    SwimmingSchoolSystem schoolObj = new SwimmingSchoolSystem();
                    //Invoking register method for Coaches of Swimming School class
                    schoolObj.registerCoach();

                    String name,gender;
                    int age,currentGrade,ID;
                    long contactNum;
                    System.out.println("Enter your name :");
                    name = sc.next();
                    //generating random number to be assigned as ID for learners
                    ID = (int) (Math.random() * 9000) + 1000;
                    System.out.println("Enter your age:");
                    int userAge = sc.nextInt();
                    //condition to check if the learner is between 4 & 11 years
                    if(userAge>=4 && userAge<=11){
                        age = userAge;
                    }
                    else{
                        System.out.println("Sorry! You cannot register as a learner if your age isn't between 4 & 11\n"+ "Thank You!");
                        break;
                    }
                    System.out.println("enter gender:");
                    gender = sc.next();
                    System.out.println("enter current grade (1 - 5):");
                    currentGrade = sc.nextInt();
                    System.out.println("enter contact number:");
                    contactNum = sc.nextLong();

                    //Invoking register method for Learners of Swimming School class and passing arguments to it, within which object of learner is being instantiated with all these passed values
                    schoolObj.registerNewLearner(name,ID,gender,age,currentGrade,contactNum);

                    break;
                case 0:
                    flag = false;
                    break;
                default: System.out.println("please select an appropriate action!");
            }
        }
    }
}

