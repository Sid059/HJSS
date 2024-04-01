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
    private int week;       //need to check into this variable, whether to use ot or not
    private Set<Integer> learnerIds;    // stores IDs of the learners that are enrolled in a particular lesson. this will be created for each instance of lesson.
    private final int maxLearners = 4;  // max capacity of learners that each lesson can hold
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

    public int getGrade() { return grade; }

    public String getDay() {
        return day;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public int getCoachId() {
        return coachId;
    }
    public int getWeek(){ return week; }
    public int getMaxLearners(){ return maxLearners; }

    public Set<Integer> getLearnerIds() {   //need to check where it's being used####################################
        return new HashSet<>(learnerIds); // Protect the internal structure
    }
    public boolean isFull() {
        return learnerIds.size() >= maxLearners;
    }

    //manages the booking at the individual lesson level.
    public boolean addLearner(int learnerId) {
        if (isFull()) {
            System.out.println("Cannot add learner to lesson " + id + ": Lesson is full.");
            return false;
        }
        //learnerIds is a set so there won't be any duplicate bookings for a particular lesson by a particular learner
        return learnerIds.add(learnerId);
    }
    public boolean removeLearner(int learnerId) {       //for now, it's getting invoked after attending a lesson
        if (learnerIds.contains(learnerId)) {
            learnerIds.remove(learnerId);
            return true;
        }
        return false;
    }

    // records feedback and rating
    public void recordFeedback(int learnerId, String review, int rating) {
        reviews.put(learnerId, review);
        ratings.put(learnerId, rating);
    }

    //checks if the learnerId passed is enrolled for the particular lesson or not
    public boolean isLearnerEnrolled(int learnerId) {
        return learnerIds.contains(learnerId);
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
