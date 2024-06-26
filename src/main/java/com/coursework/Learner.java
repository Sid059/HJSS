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
    private Set<Integer> bookedLessonIds; // Stores IDs of booked lessons for each learner until they have attended those lessons
    private Set<Integer> attendedLessonIds; // stores the lessonId of the lesson that the learner has recently attended in a list, to use it for learner report
    private Set<Integer> canceledLessonIds; // stores the lessonId of the lesson that the learner has recently cancelled, in a list, to use it for learner report

    public Learner(String name, char gender, int age, String emergencyContact, int gradeLevel) {
        this.id = (int) (Math.random() * 9000) + 1000;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.emergencyContact = emergencyContact;
        this.gradeLevel = gradeLevel;
        this.bookedLessonIds = new HashSet<>();
        this.attendedLessonIds = new HashSet<>();
        this.canceledLessonIds = new HashSet<>();
    }

    //setter        this will set grade for preregistered learners
    public void setGradeLevel(int newGrade){
        this.gradeLevel = newGrade;
    }

    public Set<Integer> getBookedLessonIds() {      //returns a list of lessons that a learner has booked currently which stays there unless it is attended
        return new HashSet<>(bookedLessonIds);
    }
    public Set<Integer> getAttendedLessonIds() {     //returns a list of lessons that a learner has successfully attended
        return new HashSet<>(attendedLessonIds);
    }

    public Set<Integer> getCanceledLessonIds() {     //returns a list of lesson that a learner has cancelled
        return new HashSet<>(canceledLessonIds);
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

    public int getId() { return id; }

    // adds the lessonId's of the lesson booked by the learner in a separate list ,i.e, bookedLessonIds which keeps record of all the bookings that learner has not attended yet
    public void keepRecordOfBookedLessons(int lessonId) {
        bookedLessonIds.add(lessonId);   // adds the specified element to the Set if it is not already present
    }

    // removes the lessonId from the list of lessons(when learner attends that lesson)that was being stored to keep record of all the bookings for each learner
    public void removeBookedLesson(int lessonId) {
        bookedLessonIds.remove(lessonId); //the remove method removes the specified element from the Set if it is present
    }

    // Checks if the learner can book a lesson of a specific grade
    public boolean canBookLesson(int lessonGrade) {
        return this.gradeLevel == lessonGrade || this.gradeLevel + 1 == lessonGrade;
    }
    // stores the lessonId of the lesson that the learner has recently attended in a list, to use it for learner report
    public void markLessonAsAttended(int lessonId) {
        attendedLessonIds.add(lessonId);
    }
    // stores the lessonId of the lesson that the learner has recently cancelled, in a list, to use it for learner report
    public void markLessonAsCanceled(int lessonId) {
        canceledLessonIds.add(lessonId);
    }

    // updates grade level of the learner if learner attends a lesson that is one level higher than it's current grade
    public void updateGradeLevel(int newGradeLevel) {
        this.gradeLevel = newGradeLevel;
        System.out.println("\033[32m'"+ name + "' has now advanced to grade level " + gradeLevel+".\033[0m");
    }

    //displaying learner details
    public String toString(){
        return  "Name : " +getName()+ "\nId : " +getId()+ "\nAge : " + getAge()+ "\nGender : " +getGender()+ "\nGrade : " +getGradeLevel()+ "\nContact number : " +getEmergencyContact();
    }
}
