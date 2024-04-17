package com.coursework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LearnerTest {
    private Learner learner;
    @BeforeEach
    void setUp(){
        learner = new Learner("John Wick", 'M', 9, "1234567890", 2); // mock learner
    }
    @Test
    void testCanBookLesson_SameGradeLevel() {
        assertTrue(learner.canBookLesson(learner.getGradeLevel()));    //Added rating should be in the coach's ratings list
    }

    @Test
    void testCanBookLesson_OneGradeLevelHigher() {
        assertTrue(learner.canBookLesson(learner.getGradeLevel() + 1));     //Learner should be able to book a lesson one grade level higher
    }

    @Test
    void testCanBookLesson_TwoGradeLevelHigher() {
        assertFalse(learner.canBookLesson(learner.getGradeLevel() + 2));    //Learner should not be able to book a lesson more than one grade level higher
    }

    @Test
    void testCanBookLesson_OneGradeLevelLower() {
        assertFalse(learner.canBookLesson(learner.getGradeLevel() - 1));    //Learner should not be able to book a lesson of a lower grade level; this is my assumption
    }

    @Test
    void testKeepRecordOfBookedLessons() {
        int mockLessonId1 = 1012; // Example lesson ID
        learner.keepRecordOfBookedLessons(mockLessonId1);
        assertTrue(learner.getBookedLessonIds().contains(mockLessonId1));       //Booked lessons should contain the added lesson ID
    }

    @Test
    void testRemoveBookedLesson() {
        int mockLessonId1 = 1012;
        learner.keepRecordOfBookedLessons(mockLessonId1);
        learner.removeBookedLesson(mockLessonId1);
        assertFalse(learner.getBookedLessonIds().contains(mockLessonId1));      //Booked lessons should not contain the removed lesson ID
    }

    @Test
    void testMarkLessonAsAttended() {
        int mockLessonId1 = 1012;
        learner.markLessonAsAttended(mockLessonId1);
        assertTrue(learner.getAttendedLessonIds().contains(mockLessonId1));     //Attended lessons should contain the marked lesson ID
    }

    @Test
    void testMarkLessonAsCanceled() {
        int mockLessonId1 = 1012;
        learner.markLessonAsCanceled(mockLessonId1);
        assertTrue(learner.getCanceledLessonIds().contains(mockLessonId1));     //Canceled lessons should contain the marked lesson ID
    }
}