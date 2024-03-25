package com.coursework;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SwimmingSchoolSystem {
    private List<Learner> learners = new ArrayList<>(); //array list to store learners(junior learners)
    private List<Coach> coaches = new ArrayList<>(); //array list to store coaches
    public SwimmingSchoolSystem(){
        initializeCoaches();
        initializePreregisteredLearners();
    }
    private void initializePreregisteredLearners(){
        learners.add(new Learner("Siddharth Rai", 'M',7,"07776735735",4));
        learners.add(new Learner("Sahil Gurung",'M',6,"07766347322",1));
        learners.add(new Learner("Sohit Rai",'M',5,"07756735455",2));
        learners.add(new Learner("Bikash Chhetri",'M',8,"07746734335",1));
        learners.add(new Learner("Rohan Chhetri",'M',8,"07746734325",2));
        learners.add(new Learner("Amir Subba",'M',8,"07746734310",2));
        learners.add(new Learner("Bishal Chhetri",'M',10,"07746734315",3));
        learners.add(new Learner("Tushar Kumar Rai",'M',7,"07746734305",3));
        learners.add(new Learner("Shahzab Ahmad Khan",'M',7,"07746734205",3));
        learners.add(new Learner("Abdul Rahman Ampili",'M',7,"07746734215",2));
        learners.add(new Learner("Mandar Vishwas Chavan",'M',7,"07746734115",3));
    }
    private void initializeCoaches() {
        coaches.add(new Coach("Donald",22056));
        coaches.add(new Coach("Ralph",39728));
        coaches.add(new Coach("Smith",77831));
    }

    //method to register a new learner
    public void registerNewLearner() throws IllegalArgumentException{   //handles if any exception occurs related to age and grade level
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your full name : ");
        String name = sc.nextLine();
        System.out.println("Enter your gender (M/F): ");
        char gender = sc.next().charAt(0);
        System.out.println("Enter your age (4-11) : ");
        int age;
        int tempAge = sc.nextInt();
        if (tempAge >= 4 && tempAge <= 11) {
             age = tempAge;
        } else {
            throw new IllegalArgumentException("Sorry! You cannot register as a learner if your age isn't between 4 and 11.");
        }
        System.out.println("Enter your contact number : ");
        String emergencyContact = sc.next();
        System.out.println("Enter your swimming grade level (0-5) : ");
        int gradeLevel;
        int tempGradeLevel = sc.nextInt();
        if (tempGradeLevel >= 0 && tempGradeLevel <= 5) {
            gradeLevel = tempGradeLevel;
        } else {
            throw new IllegalArgumentException("Error! Grade level must be between 0 and 5.");
        }

        //creates a new learner object
        Learner newRegLearner = new Learner(name,gender,age,emergencyContact,gradeLevel);
        //adding newly registered learner to the list of learners
        learners.add(newRegLearner);
        System.out.println("#-------------------NEWLY REGISTERED LEARNER DETAILS-------------------#\n"+newRegLearner.toString());

    }
    //displays all registered learners
    public void showAllLearners(){
        for (Learner stud: learners){
            System.out.println(stud.toString());
        }
    }
}
