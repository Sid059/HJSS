package com.coursework;

import java.util.*;

public class SwimmingSchoolSystem {
    Scanner sc = new Scanner(System.in);
    private List<Learner> learners = new ArrayList<>(); //array list to store learners(junior learners)
    private List<Coach> coaches = new ArrayList<>(); //array list to store coaches
    private List<SwimmingLesson> lessons = new ArrayList<>(); //array list to store lessons
    public SwimmingSchoolSystem(){
        initializeCoaches();
        initializeLessons();
        initializePreregisteredLearners();
        autoBookLessonsForLearners();   //function that will book lessons for previous learners
        autoCancelBookingsForLearners();    //function that will cancel lessons for previous learners
        autoAttendAndRateLessons();     //function that will attend lessons for previous learners
    }

    private void initializePreregisteredLearners(){
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
                    lessons.add(new SwimmingLesson(grade, day, timeSlot, coachId, week));
                }
            }
            // Weekend lessons (Saturday)
            for (String day : weekend) {
                for (int i = 0; i < weekendTimeSlots.length; i++) {
                    String timeSlot = weekendTimeSlots[i];
                    int grade = weekendGrades[i];
                    int coachId = grade; // Simplifying assumption: coach ID matches grade
                    lessons.add(new SwimmingLesson(grade, day, timeSlot, coachId, week));
                }
            }
        }
    }

    public void autoBookLessonsForLearners() {
        int lessonsToBook = 5;   // Number of lessons each learner should book
        for (Learner learner : learners) {
            int bookedCount = 0;
            for (SwimmingLesson lesson : lessons) {
                if (bookedCount < lessonsToBook && learner.canBookLesson(lesson.getGrade()) && !lesson.isFull()) {
                    bookLessonForLearner(learner.getId(), lesson.getId());
                    bookedCount++;
                }
            }
        }
    }

    public void autoAttendAndRateLessons() {
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
                SwimmingLesson lesson = findLessonById(lessonId);
                if (lesson != null) {
                    // Simulate attending the lesson
                    learner.markLessonAsAttended(lessonId);
                    // Give a random rating to the coach
                    Coach coach = findCoachById(lesson.getCoachId());
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

    public void autoCancelBookingsForLearners() {
        int lessonsToCancel = 2;
        for (Learner learner : learners) {
            List<Integer> bookedLessons = new ArrayList<>(learner.getBookedLessonIds());
            Collections.shuffle(bookedLessons); // Randomize the list for fair selection
            for (int i = 0; i < lessonsToCancel && i < bookedLessons.size(); i++) {
                cancelBooking(learner.getId(), bookedLessons.get(i));
            }
        }
    }

    //method to register a new learner
    public void registerNewLearner() {       // exception handled
        int choice;
        try {
            System.out.println("\033[32mSelect one action:\033[0m");
            System.out.println("\033[32m1. Continue registration." + "\n2. Go back to main menu.\033[0m");
            System.out.println("Enter your choice:");
            choice = sc.nextInt();
            sc.nextLine();
            //function that helps in filling registration details for learner
            fillRegistrationDetailsForLearner(choice);
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println("\033[32mRedirecting to main menu...\033[0m");
        }
        catch (Exception e) {
            System.out.println("\033[31mError! You didn't enter a number.\033[0m");
            System.out.println("\033[32mRedirecting to main menu...\033[0m");
            sc.nextLine();
        }
    }

    public void fillRegistrationDetailsForLearner(int choice) throws IllegalArgumentException {
        if (choice == 1) {
            System.out.println("\033[32m\nRegistration in progress...\033[0m");
            // name input and validation
            System.out.println("Enter your full name (letters only): ");
            String name = sc.nextLine();
            if (!name.matches("[a-zA-Z\\s]+")) {        //a-zA-Z for checking alphabets, \s for whitespaces ,+ so that there can be one or more occurrence of the preceding elements
                throw new IllegalArgumentException("\033[31mError! Name must contain letters and spaces only.\033[0m");     //make all the exceptions look like this
            }
            // gender input and validation
            System.out.println("Enter your gender (M/F/O for Male/Female/Other): ");
            String genderInput = sc.next().toUpperCase();
            sc.nextLine();
            char gender = genderInput.length() == 1 ? genderInput.charAt(0) : ' ';      //checks for char value
            if (gender != 'M' && gender != 'F' && gender != 'O') {          //checks for right character entry
                throw new IllegalArgumentException("\033[31mError! Gender must be 'M', 'F', or 'O'.\033[0m");
            }
            // age input and validation
            int age;
            try {
                System.out.println("Enter your age (4-11):");
                age = sc.nextInt();
                sc.nextLine(); //we use this always after nextInt() and next() to consume the line. This extra nextLine() consumes the leftover newline character from the previous input
            } catch (Exception e) {
                System.out.println("\033[31mError! Age must be in number.\033[0m");
                System.out.println("\033[32mRedirecting to main menu...\033[0m");
                sc.nextLine();      // to consume the wrong input from the scanner
                return;     //need this return here because there are statements below it, if return is not present then those statements won't get executed.
            }
            if (age < 4 || age > 11) {
                throw new IllegalArgumentException("\033[31mSorry! You cannot register as a learner if your age isn't between 4 and 11.\033[0m");
            }
            // emergency contact input and validation
            System.out.println("Enter your 10 digit contact number:");
            String emergencyContact = sc.next();
            sc.nextLine();
            if (emergencyContact.length() != 10 || !emergencyContact.matches("\\d+")) {      //\\d to check if the contact number contains digits 0-9 , + because there can be more than one occurrence of digits
                throw new IllegalArgumentException("\033[31mError! Contact number must be up to 10 digits(0-9).\033[0m");
            }
            // grade input and validation
            int gradeLevel;
            try {
                System.out.println("Enter your swimming grade level (0-5):");
                gradeLevel = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("\033[31mError! Grade must be in number.\033[0m");
                System.out.println("\033[32mRedirecting to main menu...\033[0m");
                sc.nextLine();
                return;
            }
            if (gradeLevel < 0 || gradeLevel > 5) {
                throw new IllegalArgumentException("\033[31mError! Grade level must be between 0 and 5.\033[0m");
            }
            // creates a new learner object
            Learner newRegLearner = new Learner(name, gender, age, emergencyContact, gradeLevel);
            //adding newly registered learner to the list of learners
            learners.add(newRegLearner);
            System.out.println("\033[32m#------------NEWLY REGISTERED LEARNER DETAILS-------------#\033[0m\n" + newRegLearner.toString());
        }
        if(choice == 2){
            System.out.println("\033[32mRedirecting to main menu...\033[0m");
        }
        else{
            throw new IllegalArgumentException("\033[31mError! You didn't select an appropriate action.\033[0m");
        }
    }

    // displays lessons
    public void runLessonDisplayInterface() throws IllegalArgumentException {     //exception handled
        System.out.println("\033[32m#-------------------Days & Timings---------------------#"
                + "\n|   Monday   |  Wednesday  |   Friday   |   Saturday |"
                + "\n|   4-5pm    |    4-5pm    |    4-5pm   |    2-3pm   |"
                + "\n|   5-6pm    |    5-6pm    |    5-6pm   |    3-4pm   |"
                + "\n|   6-7pm    |    6-7pm    |    6-7pm   |    Off     |\033[0m");

        System.out.println("\nHow would you like to view the available lessons ?"+"\nType 'day' to filter by day, 'grade' to filter by grade level, or 'coach' to filter by coach's name:");
        String filterType = sc.next();
        sc.nextLine();
        if (filterType.equalsIgnoreCase("day")) {
            System.out.println("You have to enter one value:"+"\nFor 'day': 'Monday', 'Wednesday', 'Friday', or 'Saturday'.");
        }
        else if (filterType.equalsIgnoreCase("grade")) {
            System.out.println("You have to enter one value:"+"\nFor 'grade': '1'( for learners at level 0 & 1), '2', '3', '4', or '5'.");
        }
        else if (filterType.equalsIgnoreCase("coach")) {
            System.out.println("You have to enter one value:"+"\nFor 'coach': 'Donald', 'Ralph', 'Smith', 'Spencer', or 'Mike'.");
        }
        else{
            throw new IllegalArgumentException("\033[31mError! Invalid filter type. Please type 'day', 'grade', or 'coach' to view lessons accordingly.\033[0m");
        }
        System.out.println("Enter one value for '" + filterType + "':");
        String filterValue = sc.next();
        sc.nextLine();
        //checks if the value passed matches with what it's supposed to be for eg, Monday, Wednesday etc.
        if(!checkFilterValue(filterType,filterValue)) {
            throw new IllegalArgumentException("\033[31mError! Invalid value entered for " + filterType + ".\033[0m");
        }
        //displays lessons according to the filter
        displayLessons(filterType, filterValue);
    }

    // checks the filterValue that's been taken as an input
    public boolean checkFilterValue(String filterType, String value) {
        // converting the value to lower case for a case-insensitive comparison
        String lowerCaseValue = value.toLowerCase();
        switch (filterType.toLowerCase()) {
            case "day":
                return Arrays.asList("monday", "wednesday", "friday", "saturday").contains(lowerCaseValue);
            case "grade":
                // Grades are numeric and case-insensitive by nature
                return Arrays.asList("1", "2", "3", "4", "5").contains(value);
            case "coach":
                return Arrays.asList("donald", "ralph", "smith", "spencer", "mike").contains(lowerCaseValue);
            default:
                return false;       // when invalid filter type
        }
    }

    //displays lessons according to day, coach, grade
    public void displayLessons(String filterType, String filterValue) {
        System.out.println("\033[32mFiltering Lessons...\033[0m");
        if(filterType.equalsIgnoreCase("day")){
            System.out.println("\033[32mLessons for '"+filterValue.toLowerCase()+"'...\033[0m");
        }
        else if (filterType.equalsIgnoreCase("grade")) {
            System.out.println("\033[32mLessons for 'Grade "+filterValue.toLowerCase()+"' learners...\033[0m");
        }
        else{
            System.out.println("\033[32mLessons taught by '"+filterValue.toLowerCase()+"'...\033[0m");
        }

        for (SwimmingLesson lesson : lessons) {
            String coachName = findCoachById(lesson.getCoachId()).getName(); //fetches the name of the coach
            boolean displayLesson;
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
                    System.out.println("\033[31mError! Invalid filter type. Please choose 'day', 'grade', or 'coach'.\033[0m");
                    return;
            }
            if (displayLesson) {
                System.out.println(lesson.getLessonDetails() + ", Coach: " + coachName + ", Vacancies: " + (lesson.getMaxLearners() - lesson.getLearnerIds().size()));
            }
        }
    }

    // displays available lessons to the learner according to their current grade level and one higher level
    public void displayAvailableLessonsForLearner(Learner selectedLearner) {
        System.out.println("\n\033[32mGenerating available lessons...\033[0m");
        System.out.println("\033[32m#------------------------Available lessons------------------------#\033[0m");
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
    }

    //books lesson for learner
    public void bookLesson() throws IllegalArgumentException {                  //exception handled
        System.out.println("\n\033[32mLesson Booking in progress...\033[0m");
        int learnerId;
        try {
            System.out.println("Enter learner ID:");
            learnerId = sc.nextInt();
            sc.nextLine();
        }
        catch(Exception e){
            System.out.println("\033[31mError! Learner ID must be in numbers.\033[0m");
            System.out.println("\033[32mRedirecting to main menu...\033[0m");
            sc.nextLine();      // to consume the wrong input from scanner
            return;
        }

        Learner selectedLearner = findLearnerById(learnerId);
        if (selectedLearner == null) {
            throw new IllegalArgumentException("\033[31mError! Learner not found. You need to register as a learner first to a book lesson.\033[0m");
        }
        //shows available lessons according to learners grade level also checks if the learner is registered or not before booking and showing available lessons
        displayAvailableLessonsForLearner(selectedLearner);

        int lessonId;
        try {
            System.out.println("\nEnter lesson ID:");
            lessonId = sc.nextInt();
            sc.nextLine();
        }
        catch(Exception e){
            System.out.println("\033[31mError! Lesson ID must be in numbers.\033[0m");
            System.out.println("\033[32mRedirecting to main menu...\033[0m");
            sc.nextLine();      // to consume the wrong input from the scanner
            return;
        }

        SwimmingLesson lesson = findLessonById(lessonId);
        if (lesson == null){
            throw new IllegalArgumentException("\033[31mError! The lesson you're trying to book does not exist.\033[0m");
        }
        if (isLessonAlreadyBooked(selectedLearner, lesson)) {
            throw new IllegalArgumentException("\033[31mError! You already have an existing booking for this lesson."
                                                +"\nTo rebook the same lesson, either you have to attend it or Cancel it.\033[0m");
        }
        if (bookLessonForLearner(learnerId, lessonId)) {
            System.out.println("\033[32mLesson booked successfully.\033[0m");
            //choiceToBookMoreLesson();
        }
        else {
            System.out.println("\033[31mError! Failed to book lesson. It might be because it doesn't match lesson's grade level.\033[0m");
            System.out.println("\033[32mRedirecting to main menu...\033[0m");
        }
    }

/*    public void choiceToBookMoreLesson(){
        int choice;
        try {
            System.out.println("Select one action:" + "\n1. Book more lessons." + "\n2. Go back to main menu.");
            System.out.println("Enter your choice:");
            choice = sc.nextInt();
            sc.nextLine();
            bookMoreLesson(choice);
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println("\033[32mRedirecting to main menu...\033[0m");
        }
        catch(Exception e){
            System.out.println("\033[31mError! You didn't enter a number.\033[0m");
            System.out.println("\033[32mRedirecting to main menu...\033[0m");
            sc.nextLine();
        }
    }

    public void bookMoreLesson(int choice) throws IllegalArgumentException{
        if(choice == 1){
            bookLesson();
        }
        if(choice == 2) {
            System.out.println("\033[32mRedirecting to main menu...\033[0m");
        }
        else{
            throw new IllegalArgumentException("\033[31mError! You didn't select an appropriate action.\033[0m");
        }
    }*/

    // checks if the learnerId is associated with the lessonId in a way that indicates booking but not attendance.
    private boolean isLessonAlreadyBooked(Learner learner, SwimmingLesson lesson) {
        return lesson.isLearnerEnrolled(learner.getId());
    }

    private boolean bookLessonForLearner(int learnerId, int lessonId) {     //returns true if lesson has been booked
        for (SwimmingLesson lesson : lessons) {
            if (lesson.getId() == lessonId) {
                Learner learner = findLearnerById(learnerId);
                if (learner != null && learner.canBookLesson(lesson.getGrade())) {
                    // checks the capacity and books lessons accordingly
                    if (lesson.addLearner(learnerId)) {
                        learner.keepRecordOfBookedLessons(lessonId);  //will help in storing the lessonId of each lesson that the learner will book
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // helps attend lessons and provide reviews and ratings accordingly
    public void attendLessonAndProvideFeedback() throws IllegalArgumentException{       //exception handled ; refactored
        System.out.println("\n\033[32mAttending a lesson...\033[0m");
        int learnerId;
        try {
            System.out.println("Enter learner ID:");
            learnerId = sc.nextInt();
            sc.nextLine();
        }
        catch(Exception e){
            System.out.println("\033[31mError! Learner ID must be in numbers.\033[0m");
            System.out.println("\033[32mRedirecting to main menu...\033[0m");
            sc.nextLine();      // to consume the wrong input from scanner
            return;
        }

        Learner learner = findLearnerById(learnerId);
        // checks if the learnerId entered is a registered one,i.e, it should be present inside the learners list
        if(learner == null ){
            throw new IllegalArgumentException("\033[31mError! No learner with this ID has been registered yet.\033[0m");
        }
        if(learner.getBookedLessonIds().isEmpty()){
            throw new IllegalArgumentException("\033[31mError! You haven't booked any lesson that you can attend.\033[0m");
        }

        System.out.println("\033[32mBelow is a list of lessons that you can attend:\033[0m");
        //displays list of bookings that learner has, it's his own list that keeps record of bookings
        learner.getBookedLessonIds().forEach(id -> {
            SwimmingLesson lesson = findLessonById(id);
            System.out.println("Lesson ID: " + lesson.getId() + ", Grade: " + lesson.getGrade() + ", Day: " + lesson.getDay() + ", Time: " + lesson.getTimeSlot());
        });

        int lessonId;
        try {
            System.out.println("\nEnter ID of the lesson you want to attend: ");
            lessonId = sc.nextInt();
            sc.nextLine();
        }
        catch(Exception e){
            System.out.println("\033[31mError! Lesson ID must be in numbers.\033[0m");
            System.out.println("\033[32mRedirecting to main menu...\033[0m");
            sc.nextLine();      // to consume the wrong input from scanner
            return;
        }

        SwimmingLesson lesson = findLessonById(lessonId);
        // checks if the lessonId entered belongs to a lesson that exists,i.e, it should be present inside the list of created lessons
        if (lesson == null) {
            throw new IllegalArgumentException("\033[31mError! The lesson you're trying to attend does not exist.\033[0m");
        }
        try{
        //checks  1.if the lesson passed is valid. 2. if learner is enrolled for that particular lesson. if both true enables to provide feedback
        attendLesson(learner, lesson);
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println("\033[32mRedirecting to main menu...\033[0m");
        }
    }

    // helps in attending lesson, providing review and rating for the lesson attended, and also providing rating for the coach assigned to that lesson
    public void attendLesson(Learner learner, SwimmingLesson lesson) throws IllegalArgumentException {     //exception handled

        // checks if the learner is attending a lesson that is appropriate to its grade level
        if (!(learner.getGradeLevel() == lesson.getGrade() || learner.getGradeLevel() + 1 == lesson.getGrade())) {
            throw new IllegalArgumentException("\033[31mError! You can only attend lessons of your current grade or one level higher."
                    + "\nYour current grade: " + learner.getGradeLevel() + "\nGrade of lesson you're trying to attend: " + lesson.getGrade()
                    + "\nSince you can no longer attend it, you have to cancel or change this booking.\033[0m");
        }
        // checks if the learnerId passed is enrolled for that particular lesson or not
        if (!lesson.isLearnerEnrolled(learner.getId())) {
            throw new IllegalArgumentException("\033[31mError! Learner not enrolled in this lesson.\033[0m");
        }
        System.out.println("\033[32mCongratulations on completing 'Grade " + lesson.getGrade() + "' lesson successfully.\033[0m");

        // checks if the attended lesson's grade is exactly one level higher than the learner's current grade, if true updates learner's grade to a level higher
        if (lesson.getGrade() == learner.getGradeLevel() + 1) {
            learner.updateGradeLevel(lesson.getGrade());
        }
        recordRatingAndFeedback(learner,lesson);
    }

    //function created to record feedback
    public void recordRatingAndFeedback(Learner learner, SwimmingLesson lesson){
        System.out.println("\n\033[32mRecording feedback...\033[0m");
        System.out.println("Please provide a review about the lesson in less than 50 words:");
        String review = sc.nextLine();
        int rating;
        do {
            System.out.println("Please provide a value ranging from 1 to 5 to rate this lesson:" + "\n1: Very dissatisfied" + "\n2: Dissatisfied" + "\n3: Okay" + "\n4: Satisfied" + "\n5: Very Satisfied" + "\nEnter a value to rate this lesson: ");
            while (!sc.hasNextInt()) {
                System.out.println("\033[31mError! Please enter a number between 1 and 5.\033[0m");
                sc.next(); // Clears the invalid input
            }
            rating = sc.nextInt();
            sc.nextLine();
            if (rating < 1 || rating > 5) {
                System.out.println("\033[31mError! Rating must be between 1 and 5.\033[0m");
            }
        } while (rating < 1 || rating > 5);

        // records feedback for the particular lesson that was attended by storing it inside the reviews HashMaps
        lesson.giveFeedback(learner.getId(), review);
        // records/gives rating for the particular coach which was given for that particular lesson earlier
        giveCoachRating(lesson.getCoachId(),rating);

        if(lesson.removeLearner(learner.getId())) {
        // removes bookedLesson details from the list of bookedLessonIds once the learner has attended those lessons.
        learner.removeBookedLesson(lesson.getId());
        // stores the lessonId of the lesson that the learner has recently attended in a list, to use it for learner report
        learner.markLessonAsAttended(lesson.getId());
        System.out.println("\033[32mFeedback recorded successfully.\033[0m");
        System.out.println("\033[32mRedirecting to main menu...\033[0m");
        }
    }

    // helps in giving rating to each coach for each lesson
    private void giveCoachRating(int coachId, int rating) {
        for (Coach coach : coaches) {
            if (coach.getId() == coachId) {
                coach.addRating(rating);
                break;
            }
        }
    }

    // helps in cancelling a booked lesson, also helps in changing the booking,i.e, booking another lesson in place of previous lesson
    public void changeOrCancelBooking() throws IllegalArgumentException{        //exception handled
        System.out.println("\n\033[32mChanging or cancelling a booking...\033[0m");
        int learnerId;
        try {
            System.out.println("Enter learner ID:");
            learnerId = sc.nextInt();
            sc.nextLine();
        }
        catch(Exception e){
            System.out.println("\033[31mError! Learner ID must be in numbers.\033[0m");
            sc.nextLine();      // to consume the wrong input from scanner
            return;
        }

        Learner learner = findLearnerById(learnerId);
        //checks if the learnerId entered is a registered one, if so it should be present inside the learners list
        if(learner == null){
            throw new IllegalArgumentException("\033[31mError! No learner with this ID has been registered yet.\033[0m");
        }
        //checks if the list that stores the bookings for each learner is empty
        if(learner.getBookedLessonIds().isEmpty()){
            throw new IllegalArgumentException("\033[31mOops! It seems you don't have any bookings to cancel/change.\033[0m");
        }

        System.out.println("\033[32mBelow is a list of lessons that you have booked:\033[0m");
        //displays list of bookings that learner has, it's his own list that keeps record of bookings
        learner.getBookedLessonIds().forEach(id -> {
            SwimmingLesson lesson = findLessonById(id);
            System.out.println("Lesson ID: " + lesson.getId() + ", Grade: " + lesson.getGrade() + ", Day: " + lesson.getDay() + ", Time: " + lesson.getTimeSlot());
        });

        int lessonId;
        try {
            System.out.println("\nEnter lesson ID of the lesson you want to cancel/change: ");
            lessonId = sc.nextInt();
            sc.nextLine();
        }
        catch(Exception e){
            System.out.println("\033[31mError! Lesson ID must be in numbers.\033[0m");
            sc.nextLine();      // to consume the wrong input from scanner
            return;
        }

        SwimmingLesson lesson = findLessonById(lessonId);
        // checks if the lesson entered by the user is a lesson that has already been created and that's present in the lessons list
        if(lesson == null){
            throw new IllegalArgumentException("\033[31mError! The lesson you're trying to cancel/change does not exist.\033[0m");
        }
        // checks if the lesson entered by the user is the one that user has actually booked and not any other available ID's
        if(!learner.getBookedLessonIds().contains(lessonId)){
            throw new IllegalArgumentException("\033[31mError! You cannot change/cancel a lesson that you haven't booked yet.\033[0m");
        }

        int choice;
        try {
            System.out.println("\033[32mChoose what action you wish to perform by selecting appropriate number:" + "\n1. Cancel." + "\n2. Change this booking.\033[0m");
            choice = sc.nextInt();
            sc.nextLine();
        }
        catch(Exception e){
            System.out.println("\033[31mError! You must enter a number.\033[0m");
            sc.nextLine();
            return;
        }

        // to Cancel
        if (choice == 1) {
            if (cancelBooking(learnerId, lessonId)) {
                System.out.println("\033[32mBooking cancelled successfully.\033[0m");
            }
        }
        // to change
        else if (choice == 2) {
            //displays list of available lessons according to learners grade level
            displayAvailableLessonsForLearner(learner);

            int newLessonId;
            try {
                System.out.println("\nFrom the list of lessons given above, enter one lesson ID for booking: ");
                newLessonId = sc.nextInt();
                sc.nextLine();
            }
            catch(Exception e){
                System.out.println("\033[31mError! Lesson ID must be in numbers.\033[0m");
                sc.nextLine();
                return;
            }

            SwimmingLesson newLesson = findLessonById(newLessonId);
            // checks if the lessonId entered belongs to a lesson that exists,i.e, it should be present inside the list of created lessons
            if (newLesson == null) {
                throw new IllegalArgumentException("\033[31mError! The lesson you're trying to book to does not exist.\033[0m");
            }
            // checks if learner enters the same lessonId that he/she wanted to change after selecting the change booking option
            if(lessonId == newLessonId) {
                System.out.println("\033[31mError! Attempting to change to the same lesson, i.e, LessonID: " + lessonId + ". No action taken.\033[0m");
                return;
            }
            if(learner.getBookedLessonIds().contains(newLessonId))
            {
                throw new IllegalArgumentException("\033[31mError! You already have an existing booking for the requested lesson, i.e, LessonID: " + lessonId + ". No action taken.\033[0m");
            }
            // checks if learners grade is appropriate for the new lesson
            if(!learner.canBookLesson(newLesson.getGrade())){
                System.out.println("\033[31mError! The chosen lesson's grade is not appropriate for the learner's current grade."
                                    +"\nTry it again with lessons of same grade as learner's.\033[0m");
                return;
            }
            // Check if the new lesson can accommodate the learner
            if (newLesson.isFull()) {
                System.out.println("Error! The chosen lesson is at full capacity.");
                return;
            }
            if (changeBooking(learnerId, lessonId, newLessonId)) {
                System.out.println("\033[32mBooking changed successfully.\033[0m");
            }
            else {
                System.out.println("\033[31mError! Failed to change booking.\033[0m");
            }
        }
        else {
            System.out.println("\033[31mError! Invalid choice.\033[0m");
        }
    }

    public boolean cancelBooking(int learnerId, int lessonId) {
        SwimmingLesson lesson = findLessonById(lessonId);
        if (lesson != null && lesson.removeLearner(learnerId)) {        //this statement checks the validity of the lesson, also removes the learner from the lesson using learnerId
            Learner learner = findLearnerById(learnerId);
            if (learner != null) {
                // this statement removes the lesson being passed from the learner's list that stores bookings for each learner,i.e, bookedLessonIds
                learner.removeBookedLesson(lessonId);
                // stores the lessonId of the lesson that the learner has recently cancelled, in a list, to use it for learner report
                learner.markLessonAsCanceled(lessonId);
                return true;
            }
        }
        return false;
    }

    public boolean changeBooking(int learnerId, int oldLessonId, int newLessonId) {
        // checks if booking is cancelled and new lesson can be added
        return cancelBooking(learnerId, oldLessonId) && bookLessonForLearner(learnerId, newLessonId);   //returns true if both the condition satisfies
    }

    // generates report till current time. Includes details about the lessons
    public void generateMonthlyLearnerReport() {
        System.out.println("\033[32m#--------------Monthly Learner Report--------------#\033[0m");
        for (Learner learner : learners) {
            System.out.println("\nLearner Name: " + learner.getName());
            System.out.println("Learner ID: " + learner.getId());

            // Shows current booked Lessons
            System.out.println("Booked Lessons: " + learner.getBookedLessonIds().size());
            for (Integer lessonId : learner.getBookedLessonIds()) {
                SwimmingLesson lesson = findLessonById(lessonId);
                if (lesson != null) {
                    System.out.println("\tLesson ID: " + lesson.getId() + ", Grade: " + lesson.getGrade() + ", Day: " + lesson.getDay() + ", Time: " + lesson.getTimeSlot());
                }
            }

            // Attended Lessons and Reviews
            System.out.println("\nAttended Lessons: " + learner.getAttendedLessonIds().size());
            for (Integer lessonId : learner.getAttendedLessonIds()) {
                SwimmingLesson lesson = findLessonById(lessonId);
                if (lesson != null) {
                    //String review = lesson.getReview(selectedLearner.getId());
                    System.out.println("\tLesson ID: " + lesson.getId() + ", Grade: " + lesson.getGrade() + ", Day: " + lesson.getDay() + ", Time: " + lesson.getTimeSlot());
                }
            }

            // Canceled Lessons
            System.out.println("\nCanceled Lessons: " + learner.getCanceledLessonIds().size());
            for (Integer lessonId : learner.getCanceledLessonIds()) {
                SwimmingLesson lesson = findLessonById(lessonId);
                if (lesson != null) {
                    System.out.println("\tLesson ID: " + lesson.getId() + ", Grade: " + lesson.getGrade() + ", Day: " + lesson.getDay() + ", Time: " + lesson.getTimeSlot());
                }
            }
            System.out.println("-------------------------------------------");
        }
    }
    public void generateMonthlyCoachReport() {
        System.out.println("\n\033[32m#--------------Monthly Coach Report--------------#\033[0m");
        for (Coach coach : coaches) {
            // Assuming each Coach object has a method to calculate the average rating
            double averageRating = coach.calculateAverageRating();
            System.out.println("Coach Name: " + coach.getName());
            System.out.println("Average Rating: " + averageRating);
            System.out.println("------------------------------------------------");
        }
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

    private Coach findCoachById(int coachId) {      //returns Coach object according to the coachID
        for (Coach coach : coaches) {
            if (coach.getId() == coachId) {
                return coach;
            }
        }
        return null;
    }

    private Learner findLearnerById(int learnerId) {        //returns Learner object according to the LearnerID
        for (Learner learner : learners) {
            if (learner.getId() == learnerId) {
                return learner;
            }
        }
        return null; // when Learner not found
    }

    private SwimmingLesson findLessonById(int lessonId) {       //returns lesson object according to the lessonId
        for (SwimmingLesson lesson : lessons) {
            if (lesson.getId() == lessonId) {
                return lesson;
            }
        }
        return null; // Return null if no lesson matches the given ID
    }
}
