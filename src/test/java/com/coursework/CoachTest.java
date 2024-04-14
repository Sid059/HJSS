package com.coursework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoachTest {
    Coach coach;
    int validRating1 = 5;
    int validRating2 = 3;

    @BeforeEach
    void setUp() {
        coach = new Coach("Bruce Banner", 6609);
    }

    @Test
    void addRating() {
        coach.addRating(validRating1);
        assertTrue(coach.getRatings().contains(validRating1), "Added rating should be in the coach's ratings list.");
    }

    @Test
    void calculateAverageRating() {
        coach.addRating(validRating1);
        coach.addRating(validRating2);
        double expectedAverage = (validRating1 + validRating2) / 2.0;
        assertEquals(expectedAverage, coach.calculateAverageRating(), "Average rating should match the expected value.");
    }

}