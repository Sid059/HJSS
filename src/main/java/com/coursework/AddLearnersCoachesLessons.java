package com.coursework;

import static com.coursework.SwimmingSchoolSystem.*;

public class AddLearnersCoachesLessons {
    public static void addLearners(){
        learners.add(new Learner("Siddharth Rai", 'M',7,"07776735735",4));
        learners.add(new Learner("Sahil Gurung",'M',6,"07766347322",5));
        learners.add(new Learner("Sohit Rai",'M',5,"07756735455",2));
        learners.add(new Learner("Bikash Chhetri",'M',8,"07746734335",1));
        learners.add(new Learner("Rohan Chhetri",'M',8,"07746734325",2));
        learners.add(new Learner("Amir Subba",'M',8,"07746734310",4));
        learners.add(new Learner("Bishal Chhetri",'M',10,"07746734315",3));
        learners.add(new Learner("Tushar Kumar Rai",'M',7,"07746734305",5));
        learners.add(new Learner("Shahzab Ahmad Khan",'M',7,"07746734205",3));
        learners.add(new Learner("Abdul Rahman Ampili",'M',7,"07746734215",2));
        learners.add(new Learner("Mandar Vishwas Chavan",'M',7,"07746734115",1));
    }

    public static void addCoaches(){
        coaches.add(new Coach("Donald",1));
        coaches.add(new Coach("Ralph",2));
        coaches.add(new Coach("Smith",3));
        coaches.add(new Coach("Spencer",4));
        coaches.add(new Coach("Mike",5));
    }

    public static void addLessons(){
        // Hardcoding lessons with the associated coachId
        String[] weekdays = {"Monday", "Wednesday", "Friday"};
        String[] weekend = {"Saturday"};
        String[] weekdaysTimeSlots = {"4-5pm", "5-6pm", "6-7pm"};
        Integer[] weekdaysGrades = {1, 2, 3}; // Assigning a specific grade to each time slot for weekdays
        String[] weekendTimeSlots = {"2-3pm", "3-4pm"};
        Integer[] weekendGrades = {4, 5}; // Assigning a specific grade to each time slot for weekends
        // Loop for 4 weeks
        for (int week = 1; week <= 4; week++) {
            // Weekday lessons
            for (String day : weekdays) {
                for (int i = 0; i < weekdaysTimeSlots.length; i++) {
                    String timeSlot = weekdaysTimeSlots[i];
                    int grade = weekdaysGrades[i];
                    int coachId = grade; // Simplifying assumption: coach ID matches grade
                    lessons.add(new SwimmingLesson(grade, day, timeSlot, coachId));
                }
            }
            // Weekend lessons (Saturday)
            for (String day : weekend) {
                for (int i = 0; i < weekendTimeSlots.length; i++) {
                    String timeSlot = weekendTimeSlots[i];
                    int grade = weekendGrades[i];
                    int coachId = grade; // Simplifying assumption: coach ID matches grade
                    lessons.add(new SwimmingLesson(grade, day, timeSlot, coachId));
                }
            }
        }
    }
}
