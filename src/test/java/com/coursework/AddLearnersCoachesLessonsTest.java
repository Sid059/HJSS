package com.coursework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddLearnersCoachesLessonsTest {

    @BeforeEach
    void setUp() {
        // Clearing lists before each test to avoid contamination between tests
        SwimmingSchoolSystem.learners.clear();
        SwimmingSchoolSystem.coaches.clear();
        SwimmingSchoolSystem.lessons.clear();
    }

    @Test
    void testAddLearners() {
        AddLearnersCoachesLessons.addLearners();        //should add 11 learners
        assertEquals(11, SwimmingSchoolSystem.learners.size());     //checking if there are exact number of learners
        //checking details of first learner to verify correctness
        Learner learner = SwimmingSchoolSystem.learners.get(0);
        assertEquals("Siddharth Rai", learner.getName());
        assertEquals('M', learner.getGender());
        assertEquals(7, learner.getAge());
        assertEquals("07776735735", learner.getEmergencyContact());
        assertEquals(4, learner.getGradeLevel());
    }

    @Test
    void testAddCoaches() {
        AddLearnersCoachesLessons.addCoaches();         //should add 5 coaches
        assertEquals(5, SwimmingSchoolSystem.coaches.size());       //checking if there are exact number of coaches
        //checking details of first coach to verify correctness
        Coach coach = SwimmingSchoolSystem.coaches.get(0);
        assertEquals("Donald", coach.getName());
        assertEquals(1, coach.getId());
    }

    @Test
    void testAddLessons() {
        AddLearnersCoachesLessons.addLessons();     //should create and add 44 lessons
        int expectedLessonsCount = 44;      // 3 weekdays time slots for 4 weeks + 2 weekend time slots for 4 weeks
        assertEquals(expectedLessonsCount, SwimmingSchoolSystem.lessons.size());
        //checking details of first lesson to verify correctness
        SwimmingLesson lesson = SwimmingSchoolSystem.lessons.get(0);
        assertEquals(1, lesson.getGrade());
        assertEquals("Monday", lesson.getDay());
        assertEquals("4-5pm", lesson.getTimeSlot());
        assertEquals(1, lesson.getCoachId());
    }
}
