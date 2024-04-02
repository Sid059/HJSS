package com.coursework;

import java.util.HashSet;
import java.util.Set;

public class Learner {
    private final int id;
    private String name;
    private char gender;
    private int age;
    private String emergencyContact;
    private int gradeLevel;
    private Set<Integer> bookedLessonIds; // Stores IDs of booked lessons for each learner

    public Learner(String name, char gender, int age, String emergencyContact, int gradeLevel) {
        this.id = (int) (Math.random() * 9000) + 1000;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.emergencyContact = emergencyContact;
        this.gradeLevel = gradeLevel;
        this.bookedLessonIds = new HashSet<>();
    }

    //Getters
    public String getName() {
        return name;
    }
    public char getGender() {
        return gender;
    }
    public int getAge() {
        return age;
    }
    public String getEmergencyContact() {
        return emergencyContact;
    }
    public int getGradeLevel() { return gradeLevel; }
    public int getId() {
        return id;
    }

    // adds the lessonId's of the lesson booked by the learner in a separate list ,i.e, bookedLessonIds
    public boolean keepRecordOfBookedLessons(int lessonId) {
        return bookedLessonIds.add(lessonId);   // adds the specified element to the Set if it is not already present, and returns a boolean indicating whether the element was added
    }

    // removes the lessonId of from the separate list that was being maintained to keep record of all the bookings for each learner
    public boolean removeBookedLesson(int lessonId) {
        return bookedLessonIds.remove(lessonId); //the remove method removes the specified element from the Set if it is present and returns a boolean indicating whether the element was present.
    }

    // Checks if the learner can book a lesson of a specific grade
    public boolean canBookLesson(int lessonGrade) {
        return this.gradeLevel == lessonGrade || this.gradeLevel + 1 == lessonGrade;
    }

    // Method to update grade level
    public void updateGradeLevel(int newGradeLevel) {
        this.gradeLevel = newGradeLevel;
        System.out.println(name + " has now advanced to grade level " + gradeLevel+".");
    }
    public Set<Integer> getBookedLessonIds() {
        return new HashSet<>(bookedLessonIds); // Return a copy to protect the internal set
    }

    //displaying learner details
    public String toString(){
        return  "Name : " +getName()+ "\nId : " +getId()+ "\nAge : " + getAge()+ "\nGender : " +getGender()+ "\nGrade : " +getGradeLevel()+ "\nContact number : " +getEmergencyContact()+"\n";
    }

}
