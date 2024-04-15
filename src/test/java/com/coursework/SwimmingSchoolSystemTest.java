package com.coursework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class SwimmingSchoolSystemTest {

    private SwimmingSchoolSystem swimmingSystem;

    @BeforeEach
    public void setUp() {
        swimmingSystem = new SwimmingSchoolSystem();
    }

    @AfterEach
    public void restoreStreams() {
        System.setIn(System.in);
    }

    @Test
    void testRegisterNewLearner_ValidInput() {
        String inputData = "1\nRobert Downey\nM\n7\n1234567890\n3\n";       //1 at the first is the choice for the action
        System.setIn(new ByteArrayInputStream(inputData.getBytes()));
        swimmingSystem.sc = new Scanner(System.in);

        assertDoesNotThrow(() -> swimmingSystem.registerNewLearner());
        assertEquals(12, swimmingSystem.getLearners().size());  // Assuming the list was empty before the test

        Learner registeredLearner = swimmingSystem.getLearners().get(11);
        assertEquals("Robert Downey", registeredLearner.getName());
        assertEquals('M', registeredLearner.getGender());
        assertEquals(7, registeredLearner.getAge());
        assertEquals("1234567890", registeredLearner.getEmergencyContact());
        assertEquals(3, registeredLearner.getGradeLevel());
    }

    @Test
    void testRegisterNewLearner_InvalidAgeInputBelowMargin() {
        String inputData = "1\nRobert Downey\nM\n15\n1234567890\n3\n";  // Age 3 is below the valid range
        System.setIn(new ByteArrayInputStream(inputData.getBytes()));
        swimmingSystem.sc = new Scanner(System.in);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> swimmingSystem.registerNewLearner());
        assertNotNull(exception);   //throws an exception when age is below valid range
    }

    @Test
    void testRegisterNewLearner_InvalidAgeInputAboveMargin() {
        String inputData = "1\nRobert Downey\nM\n15\n1234567890\n12\n";  // Age 12 is above the valid range
        System.setIn(new ByteArrayInputStream(inputData.getBytes()));
        swimmingSystem.sc = new Scanner(System.in);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> swimmingSystem.registerNewLearner());
        assertNotNull(exception);   //throws an exception when age is above valid range
    }

    @Test
    void testRegisterNewLearner_InvalidGradeInputBelowMargin() {
        String inputData = "1\nRobert Downey\nM\n7\n1234567890\n-1\n";  // Grade -1 is below the valid range
        System.setIn(new ByteArrayInputStream(inputData.getBytes()));
        swimmingSystem.sc = new Scanner(System.in);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> swimmingSystem.registerNewLearner());
        assertNotNull(exception);    //throws an exception when grade is below valid range
    }

    @Test
    void testRegisterNewLearner_InvalidGradeInputAboveMargin() {
        String inputData = "1\nRobert Downey\nM\n7\n1234567890\n6\n";  // Grade 6 is above the valid range
        System.setIn(new ByteArrayInputStream(inputData.getBytes()));
        swimmingSystem.sc = new Scanner(System.in);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> swimmingSystem.registerNewLearner());
        assertNotNull(exception);    //throws an exception when grade is above valid range
    }

}
