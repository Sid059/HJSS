package com.coursework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import static com.coursework.SwimmingSchoolSystem.learners;
import static com.coursework.SwimmingSchoolSystem.lessons;

public class AutoLessonManager {
    SwimmingSchoolSystem system = new SwimmingSchoolSystem();

    //assuming this is for the month of march
    public void autoBookLessonsForLearners() {      //function that will book lessons for previous learners
        int lessonsToBook = 5;   // Number of lessons each learner should book
        for (Learner learner : learners) {
            int bookedCount = 0;
            for (SwimmingLesson lesson : lessons) {
                if (bookedCount < lessonsToBook && learner.canBookLesson(lesson.getGrade()) && !lesson.isFull()) {
                    system.bookLessonForLearner(learner.getId(), lesson.getId());
                    bookedCount++;
                }
            }
        }
    }

    public void autoAttendAndRateLessons() {        //function that will cancel lessons for previous learners
        int lessonsToAttend = 3; // Number of lessons each learner should attend
        Random random = new Random();
        for (Learner learner : learners) {
            List<Integer> bookedLessonIds = new ArrayList<>(learner.getBookedLessonIds());
            // Attend up to 'lessonsToAttend' or however many lessons are booked, whichever is smaller
            int lessonsAttended = 0;
            for (int lessonId : bookedLessonIds) {
                if (lessonsAttended >= lessonsToAttend) {
                    break;
                }
                SwimmingLesson lesson = system.findLessonById(lessonId);
                if (lesson != null) {
                    // Simulate attending the lesson
                    learner.markLessonAsAttended(lessonId);
                    // Give a random rating to the coach
                    Coach coach = system.findCoachById(lesson.getCoachId());
                    if (coach != null) {
                        int rating = 1 + random.nextInt(5); // Generates a random rating from 1 to 5
                        coach.addRating(rating);
                    }
                    // If the lesson is one level higher, increase the learner's grade
                    if (lesson.getGrade() == learner.getGradeLevel() + 1) {
                        learner.setGradeLevel(lesson.getGrade());
                    }
                    // Remove from booked lessons
                    lesson.removeLearner(learner.getId());  //removes learner form the learnerIds list that each lesson has that shows learners enrolled in this lesson
                    learner.removeBookedLesson(lessonId);
                    lessonsAttended++;
                }
            }
        }
    }

    public void autoCancelBookingsForLearners(){        //function that will attend lessons for previous learners
        int lessonsToCancel = 2;    //number of lessons each learner should cancel
        for (Learner learner : learners) {
            List<Integer> bookedLessons = new ArrayList<>(learner.getBookedLessonIds());
            Collections.shuffle(bookedLessons); // Randomize the list for fair selection
            for (int i = 0; i < lessonsToCancel && i < bookedLessons.size(); i++) {
                system.cancelBooking(learner.getId(), bookedLessons.get(i));
            }
        }
    }
}
