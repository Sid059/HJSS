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

        System.out.println("Enter one value for " + filterType + " : ");
        String filterValue = sc.nextLine().trim();
        //displays lessons
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
                System.out.println(lesson.getDetails() + ", Coach: " + coachName + ", Vacancies: " + (lesson.getMaxLearners() - lesson.getLearnerIds().size()));
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
        System.out.println("\nRegistration in progress...");
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
    public void bookLesson() {      //completed the updating vacancy functionality(done)
        System.out.println("\nLesson Booking in progress...");
        // Display all learners for selection
        System.out.println("#---------------Available Learners------------------#");
        showAllLearners(); //displays all registered learners

        System.out.print("\nEnter learner ID to book a lesson for : ");
        int learnerId = sc.nextInt();
        //shows available lessons according to learners grade level also checks if the learner is registered or not before booking and showing available lessons
        boolean success = displayAvailableLessonsForLearner(learnerId);
        if (success) {
            System.out.print("\nSelect lesson ID to book : ");
            int lessonId = sc.nextInt();

            if (bookLessonForLearner(learnerId, lessonId)) {
                System.out.println("Lesson booked successfully.");
            }
            else {
                if (!lessons.contains(lessonId)) {
                    System.out.println("Error! You have entered wrong lesson Id.");
                }
                else {
                    System.out.println("Oops! Failed to book lesson. It might be because you already have an existing booking for this lesson or because it doesn't match lesson's grade level.");
                }
            }
        }
    }

    private boolean bookLessonForLearner(int learnerId, int lessonId) {
        for (SwimmingLesson lesson : lessons) {
            if (lesson.getId() == lessonId) {
                Learner learner = findLearnerById(learnerId);
                if (learner != null && !lesson.isFull() && learner.canBookLesson(lesson.getGrade())) {
                    // checks the capacity and books lessons accordingly
                    if (lesson.addLearner(learnerId)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private Learner findLearnerById(int learnerId) {
        for (Learner learner : learners) {
            if (learner.getId() == learnerId) {
                return learner;
            }
        }
        return null; // Learner not found
    }
    public boolean displayAvailableLessonsForLearner(int learnerId) {
        Learner selectedLearner = findLearnerById(learnerId);
        if (selectedLearner == null) {
            System.out.println("\nOops! Learner not found. You need to register as a learner first to a book lesson.");
            return false;
        }

        System.out.println("\n#---------------Available lessons---------------#");
        for (SwimmingLesson lesson : lessons) {
            // Checks if the lesson is not full and if the learner can book the lesson based on its grade
            if (!lesson.isFull() && selectedLearner.canBookLesson(lesson.getGrade())) {
                // Calculates the number of vacancies
                int vacancies = lesson.getMaxLearners() - lesson.getLearnerIds().size();
                // Prints the lesson details
                System.out.println("Lesson ID: " + lesson.getId() +
                        ", Grade: " + lesson.getGrade() +
                        ", Day: " + lesson.getDay() +
                        ", Time: " + lesson.getTimeSlot() +
                        ", Vacancies: " + vacancies);
            }
        }
        return true;
    }
    public void attendLessonAndProvideFeedback() {  //need to complete this
        System.out.println("Attending a lesson and providing feedback...");
        System.out.println("Enter learner ID: ");
        int learnerId = sc.nextInt();
        System.out.println("Enter ID of the lesson you want to attend: ");
        int lessonId = sc.nextInt();
        // This extra nextLine() consumes the leftover newline character from the previous input, to ensure that input for the review is correctly waited for and captured.
        sc.nextLine();
        //checks  1.if the lesson passed is valid. 2. if learner is enrolled for that particular lesson. if both true enables to provide feedback
        attendLesson(learnerId, lessonId);
    }
    public void attendLesson(int learnerId, int lessonId) throws IllegalArgumentException {     //need to implement the functionality where after attending a lesson capacity of the lesson turns back to normal
        SwimmingLesson lesson = findLessonById(lessonId);
        Learner learner = findLearnerById(learnerId);
        try {
            if (lesson == null) {
                throw new IllegalArgumentException("Lesson not found.");
            }
            if (!lesson.isLearnerEnrolled(learnerId)) {
                throw new IllegalArgumentException("Learner not enrolled in this lesson.");
            }
            System.out.println("Congratulations on attending Grade "+lesson.getGrade()+" lesson successfully.");
            //Checks if the attended lesson's grade is exactly one level higher than the learner's current grade, if true updates learner's grade to a level higher
            if (lesson.getGrade() == learner.getGradeLevel() + 1) {
                learner.updateGradeLevel(lesson.getGrade());
            }
            System.out.println("\nGive your review about the lesson: ");
            String review = sc.nextLine();
            System.out.println("On a scale of (1-5) how would you rate this lesson: ");
            int rating = sc.nextInt();
            //checks if the value of rating is withing range
            if (rating < 1 || rating > 5) {
                throw new IllegalArgumentException("Rating must be between 1 and 5.");
            }
            //records feedback and rating for the particular lesson that was attended by storing it inside the reviews and rating HashMaps
            lesson.recordFeedback(learnerId, review, rating);

            if(lesson.removeLearner(learnerId)){    //can use this function to cancel booking as well############################
                System.out.println("Feedback recorded successfully.");
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to find a lesson by its ID
    private SwimmingLesson findLessonById(int lessonId) {
        for (SwimmingLesson lesson : lessons) {
            if (lesson.getId() == lessonId) {
                return lesson;
            }
        }
        return null; // Return null if no lesson matches the given ID
    }

    //displays all registered learners
    public void showAllLearners(){
        for (Learner stud: learners){
            System.out.println("Name: "+stud.getName()+", ID: "+stud.getId()+", Grade Level: "+stud.getGradeLevel());
        }
    }
    public void showAllCoaches(){
        for (Coach coach: coaches){
            System.out.println(coach.toString());
        }
    }
}
