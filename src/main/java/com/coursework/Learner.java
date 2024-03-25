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
    private Set<Integer> bookedLessonIds; // Stores IDs of booked lessons to prevent duplicates

    public Learner(String name, char gender, int age, String emergencyContact, int gradeLevel) {
        this.id = (int) (Math.random() * 9000) + 1000;
        this.name = name;
        this.gender = gender;
        try {
            setAge(age);
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }
        this.emergencyContact = emergencyContact;
        try {
            setGradeLevel(gradeLevel);
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }
        this.bookedLessonIds = new HashSet<>();
    }

    // Setters and Getters
    public void setName(String name) {
        this.name = name;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public void setAge(int age) {
        if (age >= 4 && age <= 11) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Age must be between 4 and 11.");
        }
    }
    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }
    public void setGradeLevel(int gradeLevel) {
        if (gradeLevel >= 0 && gradeLevel <= 5) {
            this.gradeLevel = gradeLevel;
        } else {
            throw new IllegalArgumentException("Grade level must be between 0 and 5.");
        }
    }

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
    public int getGradeLevel() {
        return gradeLevel;
    }
    public int getId() {
        return id;
    }

    // Booking a lesson. If the lesson ID is already present in the set (indicating the lesson has already been booked by this learner), the add method returns false, and the lesson is not added again, preventing duplicate bookings of the same lesson by a single learner.
    public boolean bookLesson(int lessonId) {
        return bookedLessonIds.add(lessonId);   // adds the specified element to the Set if it is not already present and returns a boolean indicating whether the element was added
    }

    // Canceling a booking. If the set contains the lessonId, it is removed, indicating that the booking for that lesson has been successfully canceled.
    public boolean cancelLesson(int lessonId) {
        return bookedLessonIds.remove(lessonId); //the remove method removes the specified element from the Set if it is present and returns a boolean indicating whether the element was present.
    }

    // Checks if the learner can book a lesson of a specific grade
    public boolean canBookLesson(int lessonGrade) {
        return this.gradeLevel == lessonGrade || this.gradeLevel + 1 == lessonGrade;
    }

    // Updates the grade level after attending a higher-grade lesson
    public void attendLesson(int lessonGrade) {
        if (lessonGrade == this.gradeLevel + 1) {
            this.gradeLevel = lessonGrade;
        }
        // Assuming attendance implies removing from booked lessons if needed
        // bookedLessonIds.remove(lessonId); // Uncomment if attending removes the lesson from booked
    }
    public Set<Integer> getBookedLessonIds() {
        return new HashSet<>(bookedLessonIds); // Return a copy to protect the internal set
    }
}
