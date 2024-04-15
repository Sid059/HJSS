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
    void testAddLearner_CapacityNotFull() {
        assertTrue(lesson.addLearner(101), "Should successfully add a learner when lesson's capacity is not full");
    }

    @Test
    void testAddLearner_CapacityFull() {
        lesson.addLearner(101);
        lesson.addLearner(102);
        lesson.addLearner(103);
        lesson.addLearner(104); //at this point the lesson's capacity gets full
        assertFalse(lesson.addLearner(105), "Should not add a learner when lesson's capacity is full");
    }

    @Test
    void testRemoveLearner_Enrolled() {
        lesson.addLearner(201);
        lesson.addLearner(202);
        assertTrue(lesson.removeLearner(201), "Should successfully remove a learner who is enrolled in that particular lesson.");
    }

    @Test
    void testRemoveLearner_NotEnrolled() {
        assertFalse(lesson.removeLearner(203), "Should not remove a learner who is not enrolled in that particular lesson.");
    }

    @Test
    void testIsLearnerEnrolled() {
        lesson.addLearner(301);
        assertTrue(lesson.isLearnerEnrolled(301), "Should return true for an enrolled learner");
        assertFalse(lesson.isLearnerEnrolled(302), "Should return false for a learner not enrolled");
    }
}
