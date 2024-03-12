package com.coursework;

import java.util.ArrayList;

public class Learner {
    private String learnerName, gender;
    private int age, currentGradeLevel, ID;
    private long emergencyContactNum;
    private ArrayList <SwimmingLesson> bookings;

    //constructor setting values for learner objects
    public Learner(String learnerName, int ID, String gender, int age, int currentGradeLevel, long emergencyContactNum){
        this.learnerName = learnerName;
        this.gender = gender;
        this.age = age;
        this.currentGradeLevel = currentGradeLevel;
        this.ID = ID;
        this.emergencyContactNum = emergencyContactNum;
        //creating a list to store the bookings of every learner
        this.bookings = new ArrayList<SwimmingLesson>();
    }
    public String getLearnerName() { return learnerName; }
    public String getGender() {
        return gender;
    }
    public int getAge() {
        return age;
    }
    public int getCurrentGradeLevel() { return currentGradeLevel; }
    public int getID(){ return ID; }
    public long getEmergencyContactNum() {
        return emergencyContactNum;
    }

    public void bookLesson(){}
    public void changeBooking(){}
    public void cancelBooking(){}
    public void attendLesson(){}
    public void writeReview(){}
    @Override
    public String toString(){   //getting detail of the registered learner
        return  "learner name :" +getLearnerName()+ "\nID :" +getID()+ "\nlearner age :" + getAge()+ "\nlearner gender :" +getGender()+ "\nlearner grade :" +getCurrentGradeLevel()+ "\nlearner contact :" +getEmergencyContactNum();
    }
}
