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
    //private int week;       //need to check into this variable, whether to use ot or not
    private Set<Integer> learnerIds;    // stores IDs of the learners that are for a particular lesson. this will be created for each instance of lesson.
    private final int maxLearners = 4;  // max capacity of learners that each lesson can hold
    private Map<Integer, String> reviews; // Learner ID to review text

    public SwimmingLesson(int grade, String day, String timeSlot, int coachId) {
        this.id = nextId++;
        this.grade = grade;
        this.day = day;
        this.timeSlot = timeSlot;
        this.coachId = coachId;
        //this.week = week;
        this.learnerIds = new HashSet<>();
        this.reviews = new HashMap<>();
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getGrade() { return grade; }

    public String getDay() { return day; }

    public String getTimeSlot() {
        return timeSlot;
    }

    public int getCoachId() {
        return coachId;
    }

    //public int getWeek(){ return week; }        //haven't used this one yet

    public int getMaxLearners(){ return maxLearners; }

    public Set<Integer> getLearnerIds() {   //need to check where it's being used####################################
        return new HashSet<>(learnerIds); // Protect the internal structure
    }

    public boolean isFull() {
        return learnerIds.size() >= maxLearners;
    }

    // helps enroll learner for a lesson ,i.e book a lesson
    public boolean addLearner(int learnerId) {
        if (isFull()) {
            System.out.println("\033[31mError! Cannot add learner to lesson " + id + ": Lesson is full.\033[0m");
            return false;
        }
        //learnerIds is a set so there won't be any duplicate bookings for a particular lesson by a particular learner
        return learnerIds.add(learnerId);   //if this statement gets executed it returns value as true
    }

    // helps un-enroll a learner from a lesson
    public boolean removeLearner(int learnerId) {
        if (learnerIds.contains(learnerId)) {
            learnerIds.remove(learnerId);
            return true;
        }
        return false;
    }

    // records feedback and rating
    public void giveFeedback(int learnerId, String review) {
        reviews.put(learnerId, review);
    }

    // checks if the learnerId passed is enrolled for the particular lesson or not
    public boolean isLearnerEnrolled(int learnerId) {
        return learnerIds.contains(learnerId);
    }

    //public Map<Integer, String> getAllReviews() {         // in case if one wants to see reviews
    //    return new HashMap<>(reviews); // Protect the internal structure
    //}

    // if you just want to fetch a specific learner's review for that particular lesson
    public String getReview(int learnerId) {
        return reviews.getOrDefault(learnerId, "No review submitted");
    }

    public String getLessonDetails() {
        // Construct and return a string representing the lesson's details
        return String.format("Lesson ID: %d, Grade: %d, Day: %s, Time Slot: %s", id, grade, day, timeSlot);
    }
}
