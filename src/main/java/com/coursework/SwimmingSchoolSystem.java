package com.coursework;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SwimmingSchoolSystem {
    Scanner sc = new Scanner(System.in);
    private List<Learner> learners = new ArrayList<>(); //array list to store learners(junior learners)
    private List<Coach> coaches = new ArrayList<>(); //array list to store coaches
    private List<SwimmingLesson> lessons = new ArrayList<>(); //array list to store lessons
    public SwimmingSchoolSystem(){
        initializeCoaches();
        initializeLessons();
        initializePreregisteredLearners();
    }
    private void initializePreregisteredLearners(){
        learners.add(new Learner("Siddharth Rai", 'M',7,"07776735735",4));
        learners.add(new Learner("Sahil Gurung",'M',6,"07766347322",1));
        learners.add(new Learner("Sohit Rai",'M',5,"07756735455",2));
        learners.add(new Learner("Bikash Chhetri",'M',8,"07746734335",1));
        learners.add(new Learner("Rohan Chhetri",'M',8,"07746734325",2));
        learners.add(new Learner("Amir Subba",'M',8,"07746734310",2));
        learners.add(new Learner("Bishal Chhetri",'M',10,"07746734315",3));
        learners.add(new Learner("Tushar Kumar Rai",'M',7,"07746734305",3));
        learners.add(new Learner("Shahzab Ahmad Khan",'M',7,"07746734205",3));
        learners.add(new Learner("Abdul Rahman Ampili",'M',7,"07746734215",2));
        learners.add(new Learner("Mandar Vishwas Chavan",'M',7,"07746734115",3));
    }
    private void initializeCoaches() {
        coaches.add(new Coach("Donald",1));
        coaches.add(new Coach("Ralph",2));
        coaches.add(new Coach("Smith",3));
        coaches.add(new Coach("Spencer",4));
        coaches.add(new Coach("Mike",5));
    }
    private void initializeLessons() {
        // Hardcoding lessons with the associated coachId
        String[] weekdays = {"Monday", "Wednesday", "Friday"};
        String[] weekend = {"Saturday"};
        String[] weekdaysTimeSlots = {"4-5pm", "5-6pm", "6-7pm"};
        String[] weekendTimeSlots = {"2-3pm", "3-4pm"};

        // Loop for 4 weeks
        for (int week = 1; week <= 4; week++) {
            // Weekday lessons
            for (String day : weekdays) {
                for (String timeSlot : weekdaysTimeSlots) {
                    for (int grade = 1; grade <= 5; grade++) {
                        int coachId = grade; // Simplifying assumption: coach ID matches grade
                        lessons.add(new SwimmingLesson(grade, day, timeSlot, coachId, week));
                    }
                }
            }
            // Weekend lessons (Saturday)
            for (String day : weekend) {
                for (String timeSlot : weekendTimeSlots) {
                    for (int grade = 1; grade <= 5; grade++) {
                        int coachId = grade; // Simplifying assumption: coach ID matches grade
                        lessons.add(new SwimmingLesson(grade, day, timeSlot, coachId, week));
                    }
                }
            }
        }
    }

    // displays lessons according to day, coach , grade
    public void runLessonDisplayInterface() {
        System.out.println("-------------------Days & Timings---------------------"
                            +"\n|   Monday   |  Wednesday  |   Friday   |   Saturday |"
                            +"\n|   4-5pm    |    4-5pm    |    4-5pm   |    2-3pm   |"
                            +"\n|   5-6pm    |    5-6pm    |    5-6pm   |    3-4pm   |"
                            +"\n|   6-7pm    |    6-7pm    |    6-7pm   |    Off     |\n");

        System.out.println("Below is the list of the coaches registered for this program.\n");
        showAllCoaches();

        System.out.println("\nHow would you like to view the available lessons ?"
                +"\nType 'day' to filter by day, 'grade' to filter by grade level, or 'coach' to filter by coach's name : ");
        String filterType = sc.nextLine().trim();

        if (!filterType.equalsIgnoreCase("day") && !filterType.equalsIgnoreCase("grade") && !filterType.equalsIgnoreCase("coach")) {
            System.out.println("Invalid filter type. Please choose 'day', 'grade', or 'coach'.");
            return;
        }

        System.out.println("Enter one value for " + filterType + " ,i.e Monday, Wednesday, Friday, Saturday :");
        String filterValue = sc.nextLine().trim();

        displayLessons(filterType, filterValue);
    }
    public void displayLessons(String filterType, String filterValue) {
        System.out.println("Filtered Lessons:");
        for (SwimmingLesson lesson : lessons) {
            String coachName = findCoachById(lesson.getCoachId()).getName(); //fetches the name of the coach
            boolean displayLesson = false;

            switch (filterType.toLowerCase()) {
                case "day":
                    displayLesson = lesson.getDay().equalsIgnoreCase(filterValue);
                    break;
                case "grade":
                    displayLesson = Integer.toString(lesson.getGrade()).equals(filterValue);
                    break;
                case "coach":
                    displayLesson = coachName.equalsIgnoreCase(filterValue);
                    break;
                default:
                    System.out.println("Invalid filter type. Please choose 'day', 'grade', or 'coach'.");
                    return;
            }

            if (displayLesson) {
                System.out.println(lesson.getDetails() + ", Coach: " + coachName +
                        ", Vacancies: " + (lesson.getMaxLearners() - lesson.getLearnerIds().size()));
            }
        }
    }
    private Coach findCoachById(int coachId) {
        for (Coach coach : coaches) {
            if (coach.getId() == coachId) {
                return coach;
            }
        }
        return null; // Consider handling this case more gracefully in your implementation
    }

    //method to register a new learner
    public void registerNewLearner() throws IllegalArgumentException{   //handles if any exception occurs related to age and grade level
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your full name : ");
        String name = sc.nextLine();
        System.out.println("Enter your gender (M/F): ");
        char gender = sc.next().charAt(0);
        System.out.println("Enter your age (4-11) : ");
        int age;
        int tempAge = sc.nextInt();
        if (tempAge >= 4 && tempAge <= 11) {
             age = tempAge;
        } else {
            throw new IllegalArgumentException("Sorry! You cannot register as a learner if your age isn't between 4 and 11.");
        }
        System.out.println("Enter your contact number : ");
        String emergencyContact = sc.next();
        System.out.println("Enter your swimming grade level (0-5) : ");
        int gradeLevel;
        int tempGradeLevel = sc.nextInt();
        if (tempGradeLevel >= 0 && tempGradeLevel <= 5) {
            gradeLevel = tempGradeLevel;
        } else {
            throw new IllegalArgumentException("Error! Grade level must be between 0 and 5.");
        }

        //creates a new learner object
        Learner newRegLearner = new Learner(name,gender,age,emergencyContact,gradeLevel);
        //adding newly registered learner to the list of learners
        learners.add(newRegLearner);
        System.out.println("#-------------------NEWLY REGISTERED LEARNER DETAILS-------------------#\n"+newRegLearner.toString());

    }
    //displays all registered learners
    public void showAllLearners(){
        for (Learner stud: learners){
            System.out.println(stud.toString());
        }
    }
    public void showAllCoaches(){
        for (Coach coach: coaches){
            System.out.println(coach.toString());
        }
    }
}
