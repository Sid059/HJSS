package com.coursework;

import java.util.ArrayList;
import java.util.List;

public class Coach {
    private final int id;
    private String name;
    private List<Integer> taughtLessonIds; // List of IDs for lessons assigned to the coach
    private List<Integer> ratings; // Ratings received from learners

    public Coach(String name, int ID) {
        this.id = ID;
        this.name = name;
        this.taughtLessonIds = new ArrayList<>();   //it is intended to keep track of the IDs of lessons that a particular coach is assigned to teach.
        this.ratings = new ArrayList<>();
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getTaughtLessonIds() {
        return new ArrayList<>(taughtLessonIds); // Return a copy to protect the internal list
    }

    public List<Integer> getRatings() {
        return new ArrayList<>(ratings); // Return a copy to protect the internal list
    }

    // Assign a lesson to this coach
    /*public void assignLesson(int lessonId) {
        if (!taughtLessonIds.contains(lessonId)) {
            taughtLessonIds.add(lessonId);
        }
    }*/

    // Add a rating for this coach
    public void addRating(int rating) {     //wherever this function will be invoked don't forget to add a prompt which asks user to enter rating b/w 1 & 5
        if (rating >= 1 && rating <= 5) {
            ratings.add(rating);
        } else {
            throw new IllegalArgumentException("Error ! Rating must be between 1 and 5.");
        }
    }

    // Calculate the average rating of this coach
    public double calculateAverageRating() {
        if (ratings.isEmpty()) {
            return 0.0; // Return 0 if there are no ratings
        }
        double sum = 0.0;
        for (int rating : ratings) {
            sum += rating;
        }
        return sum / ratings.size();
    }
    public String toString(){
        return  "Name: " +getName()+ ", Id: " +getId();
    }
}

