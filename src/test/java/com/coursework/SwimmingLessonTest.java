package com.coursework;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SwimmingLessonTest {
    SwimmingLesson lesson;
    int coachId = 1;
    int grade = 1;
    String day = "Monday";
    String timeSlot = "4-5pm";

    @BeforeEach
    void setUp() {
        lesson = new SwimmingLesson(grade, day, timeSlot, coachId);
    }

    @Test
    void addLearner() {
        assertTrue(lesson.addLearner(101), "Should successfully add a learner when not full");

        lesson.addLearner(102);
        lesson.addLearner(103);
        lesson.addLearner(104); //at this point the lesson's capacity gets full
        assertFalse(lesson.addLearner(105), "Should not add a learner when full");

    }

    @Test
    void removeLearner() {
        lesson.addLearner(201);
        lesson.addLearner(202);

        assertTrue(lesson.removeLearner(201), "Should successfully remove a learner who is enrolled");

        assertFalse(lesson.removeLearner(203), "Should not remove a learner who is not enrolled");
    }

    @Test
    void isLearnerEnrolled() {
        lesson.addLearner(301);

        assertTrue(lesson.isLearnerEnrolled(301), "Should return true for an enrolled learner");

        assertFalse(lesson.isLearnerEnrolled(302), "Should return false for a learner not enrolled");
    }
}
