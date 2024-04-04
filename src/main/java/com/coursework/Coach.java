package com.coursework;

import java.util.ArrayList;
import java.util.List;

public class Coach {
    private final int id;
    private String name;
    private List<Integer> taughtLessonIds; // List of IDs for lessons assigned to the coach. haven't used it yet
    private List<Integer> ratings; // stores list of ratings for each coach received from lessons

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
        return new ArrayList<>(ratings); // Returns a copy of the ratings for each coach, this is to protect the internal list
    }

    /*public void printRatings() {        //can be used to see all the ratings that the coach received
        List<Integer> ratings = getRatings();
        System.out.println("Ratings:");
        for (Integer rating : ratings) {
            System.out.println(rating);
        }
    }*/

    // Adds a rating to the list of ratings to each coach for each lesson
    public void addRating(int rating) {
        if (rating >= 1 && rating <= 5) {
            ratings.add(rating);
        } else {
            throw new IllegalArgumentException("Error ! Rating must be between 1 and 5.");
        }
    }

    // Calculate the average rating of this coach
    public double calculateAverageRating() {        //haven't used it yet, but it's there in the requirements section
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

