package com.coursework;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SwimmingLesson {
    private static int nextId = 1;
    private final int id;
    private int grade;
    private String day;
    private String timeSlot;
    private int coachId;
    private int week;
    private Set<Integer> learnerIds;
    private final int maxLearners = 4;
    private Map<Integer, String> reviews; // Learner ID to review text
    private Map<Integer, Integer> ratings; // Learner ID to rating

    public SwimmingLesson(int grade, String day, String timeSlot, int coachId, int week) {
        this.id = nextId++;
        this.grade = grade;
        this.day = day;
        this.timeSlot = timeSlot;
        this.coachId = coachId;
        this.week = week;
        this.learnerIds = new HashSet<>();
        this.reviews = new HashMap<>();
        this.ratings = new HashMap<>();
    }
    // Getters
    public int getId() {
        return id;
    }

    public int getGrade() {
        return grade;
    }

    public String getDay() {
        return day;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public int getCoachId() {
        return coachId;
    }
    public int getWeek(){
        return week;
    }
    public int getMaxLearners(){
        return maxLearners;
    }

    public Set<Integer> getLearnerIds() {
        return new HashSet<>(learnerIds); // Protect the internal structure
    }
    public boolean isFull() {
        return learnerIds.size() >= maxLearners;
    }


    // This method manages the booking at the individual lesson level.
    public boolean addLearner(int learnerId) {
        if (learnerIds.size() >= maxLearners) {
            System.out.println("Cannot add learner to lesson " + id + ": Lesson is full.");
            return false;
        }
        return learnerIds.add(learnerId);
    }
    public boolean removeLearner(int learnerId) {
        if (learnerIds.contains(learnerId)) {
            learnerIds.remove(learnerId);
            return true;
        }
        return false;
    }

    // New functionalities to manage lesson attendance and feedback
    public boolean attendLesson(int learnerId, String review, int rating) {
        // Check if the learner was booked for the lesson
        if (!learnerIds.contains(learnerId)) {
            System.out.println("Learner " + learnerId + " was not booked for lesson " + id);
            return false;
        }
        // Remove learner from booked set to mark attendance
        learnerIds.remove(learnerId);
        // Save review and rating
        addReview(learnerId, review);
        addRating(learnerId, rating);
        return true;
    }

    private void addReview(int learnerId, String review) {
        reviews.put(learnerId, review);
    }

    private void addRating(int learnerId, int rating) {
        if (rating >= 1 && rating <= 5) {
            ratings.put(learnerId, rating);
        } else {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
    }

    public Map<Integer, String> getAllReviews() {
        return new HashMap<>(reviews); // Protect the internal structure
    }

    public Map<Integer, Integer> getAllRatings() {
        return new HashMap<>(ratings); // Protect the internal structure
    }

    // Calculate the average rating for this lesson
    public double calculateAverageRating() {        // will remove it in future
        if (ratings.isEmpty()) {
            return 0.0;
        }
        return ratings.values().stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    // Method to check if a learner is booked for this lesson
    public boolean isLearnerBooked(int learnerId) {
        return learnerIds.contains(learnerId);      //keeping it for future purpose if in case i need it
    }
    public String getDetails() {
        // Construct and return a string representing the lesson's details
        return String.format("Lesson ID: %d, Grade: %d, Day: %s, Time Slot: %s", id, grade, day, timeSlot);
    }
}
