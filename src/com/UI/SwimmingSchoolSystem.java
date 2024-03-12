package com.UI;

import java.util.ArrayList;
import java.util.HashMap;

public class SwimmingSchoolSystem {
    //array list to store learners(junior learners)
    private ArrayList<Learner> learners;
    //HashMap used to store the learners with ID
    private HashMap<Integer,Learner> swimmingschool;
    //arraylist to store preregistered coaches(4)
    private ArrayList<Coach> coaches;
    public SwimmingSchoolSystem(){
        coaches = new ArrayList<Coach>();
        learners = new ArrayList<Learner>();
        swimmingschool = new HashMap<Integer, Learner>() ;
    }
    //preregistered coaches(4)
    Coach coach1 = new Coach("Amit Bhadana");
    Coach coach2 = new Coach("Asish Chanchalani");
    Coach coach3 = new Coach("Subodh Subba");
    Coach coach4 = new Coach("Sandip Singh");

    //preregistered learners(11)
    Learner stud1 = new Learner("Siddharth Rai", 2207,"Male",7,4,07776735735);
    Learner stud2 = new Learner("Sahil Gurung",1329,"Male",6,1,07766347322);
    Learner stud3 = new Learner("Sohit Rai",1256,"Male",5,2,07756735455);
    Learner stud4 = new Learner("Bikash Chhetri",2157,"Male",8,1,07746734335);
    Learner stud5 = new Learner("Rohan Chhetri",1579,"Male",8,2,07746734325);
    Learner stud6 = new Learner("Bishal Chhetri",1931,"Male",10,3,07746734315);
    Learner stud7 = new Learner("Amir Subba",2354,"Male",8,2,07746734310);
    Learner stud8 = new Learner("Tushar Kumar Rai",2979,"Male",7,3,07746734305);
    Learner stud9 = new Learner("Shahzab Ahmad Khan",3201,"Male",7,3,07746734205);
    Learner stud10 = new Learner("Abdul Rahman Ampili",3359,"Male",7,2,07746734215);
    Learner stud11 = new Learner("Mandar Vishwas Chavan",2974,"Male",7,3,07746734115);

    //method to register a new learner
    public void registerNewLearner(String name, int ID, String gender, int age, int currentGrade, long emergencyNumber){
        //adding learners to the array list
        learners.add(stud1);
        learners.add(stud2);
        learners.add(stud3);
        learners.add(stud4);
        learners.add(stud5);
        learners.add(stud6);
        learners.add(stud7);
        learners.add(stud8);
        learners.add(stud9);
        learners.add(stud10);
        learners.add(stud11);
        //adding preregistered learners to hashmap
        for(Learner stud: learners){
            swimmingschool.put(stud.getID(),stud);  //(key,value)
        }

        //creates a new learner object
        Learner newRegLearner = new Learner(name,ID,gender,age,currentGrade,emergencyNumber);
        //adding newly registered learner to the list of learners
        addNewLearner(newRegLearner);
    }
    //adding new learner to the arraylist learners
    public void addNewLearner(Learner newlearner){
        learners.add(newlearner);
        //registering learner with their unique ID
        swimmingschool.put(newlearner.getID(),newlearner);
        //showing new learner that recently registered
        System.out.println("-------NEW LEARNER REGISTERED-------\n"+newlearner.toString());
    }

    //method to register coaches
    public void registerCoach(){
        coaches.add(coach1);
        coaches.add(coach2);
        coaches.add(coach3);
        coaches.add(coach4);
//        for (int i = 0; i < coaches.size(); i++) {
//            System.out.println(coaches.get(i).toString());
//        }
    }
//    public void showAllLearners(){
//        for (Learner stud: swimmingschool.values()){
//            System.out.println(stud.toString());
//        }
//    }
    public void generateMonthlyReport(){}
}
