package com.UI;

import java.util.ArrayList;

public class SwimmingLesson {
    private String day;
    private String time;
    private int gradeLevel;
    private Coach coach;
    private int capacity;       //need to set the capacity
    private ArrayList<Learner> allLearners;  // Reference to the list of all learners in SwimmingSchoolSystem
    private String status;

    public SwimmingLesson(String day, String time, int gradeLevel, Coach coach, int capacity, ArrayList<Learner> allLearners) {
        this.day = day;
        this.time = time;
        this.gradeLevel = gradeLevel;
        this.coach = coach;
        this.capacity = capacity;
        this.allLearners = allLearners;
        this.status = "available";  // Initially set to available
    }

    public void bookLearner(Learner learner) {
        if (isBookingAllowed(learner)) {
            allLearners.add(learner);  // Add learner to the list of all learners
            System.out.println(learner.getLearnerName() + " booked for " + day + " at " + time);
            // Update status after booking
            if (allLearners.size() >= capacity) {
                status = "full";
            }
        } else {
            System.out.println("Booking not allowed for " + learner.getLearnerName() +
                    ". Lesson is already full or learner's grade level is not suitable.");
        }
    }

    public void cancelBooking(Learner learner) {
        if (allLearners.remove(learner)) {
            System.out.println(learner.getLearnerName() + "'s booking cancelled for " + day + " at " + time);
            // Update status after cancellation
            status = "available";
        } else {
            System.out.println(learner.getLearnerName() + " doesn't have a booking for this lesson.");
        }
    }

    public void getDetails() {
        System.out.println("Lesson details for " + day + " at " + time + ":");
        System.out.println("Grade Level: " + gradeLevel);
        System.out.println("Coach: " + coach.getCoachName());
        System.out.println("Capacity: " + capacity);
        System.out.println("Learners: " + allLearners.size() + "/" + capacity);
        System.out.println("Status: " + status);
    }

    private boolean isBookingAllowed(Learner learner) {
        // Check if the lesson has reached its maximum capacity
        if (allLearners.size() >= capacity) {
            return false;  // Lesson is already full
        }
        // Check if the learner's grade level is suitable for the lesson
        int learnerGrade = learner.getCurrentGradeLevel();
        if (learnerGrade < gradeLevel || learnerGrade > gradeLevel + 1) {
            return false;  // Learner's grade level is not suitable
        }
        // Check for other criteria as needed
        // If all criteria are met, booking is allowed
        return true;
    }
}

