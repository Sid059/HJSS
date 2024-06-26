package com.coursework;

import java.util.ArrayList;
import java.util.List;

public class Coach {
    private final int id;
    private String name;
    private List<Integer> ratings; // stores list of ratings for each coach received from lessons

    public Coach(String name, int ID) {
        this.id = ID;
        this.name = name;
        this.ratings = new ArrayList<>();
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getRatings() {
        return new ArrayList<>(ratings); // Returns a copy of the ratings for each coach, this is to protect the internal list
    }

    // Adds a rating to the list of ratings to each coach for each lesson
    public void addRating(int rating) {
        ratings.add(rating);
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

