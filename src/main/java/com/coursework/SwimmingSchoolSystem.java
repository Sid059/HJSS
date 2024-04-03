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

    //method to register a new learner
    public void registerNewLearner() throws IllegalArgumentException{
        // This extra nextLine() consumes the leftover newline character from the previous input, to ensure that input for the name is correctly waited for and captured.
        sc.nextLine();

        System.out.println("\nRegistration in progress...");
        // name input and validation
        System.out.println("Enter your full name (letters only): ");
        String name = sc.nextLine();
        if (!name.matches("[a-zA-Z\\s]+")) {        //a-zA-Z for checking alphabets, \s for whitespaces ,+ so that there can be one or more occurrence of the preceding elements
            throw new IllegalArgumentException("Error! Name must contain letters and spaces only.");
        }
        // gender input and validation
        System.out.println("Enter your gender (M/F/O for Male/Female/Other): ");
        String genderInput = sc.next().toUpperCase();
        char gender = genderInput.length() == 1 ? genderInput.charAt(0) : ' ';      //checks for char value
        if (gender != 'M' && gender != 'F' && gender != 'O') {          //checks for right character entry
            throw new IllegalArgumentException("Error! Gender must be 'M', 'F', or 'O'.");
        }
        // age input and validation
        System.out.println("Enter your age (4-11) : ");
        int age;
        int tempAge = sc.nextInt();
        if (tempAge >= 4 && tempAge <= 11) {
             age = tempAge;
        } else {
            throw new IllegalArgumentException("Sorry! You cannot register as a learner if your age isn't between 4 and 11.");
        }
        // emergency contact input and validation
        System.out.println("Enter your contact number (up to 10 digits): ");
        String emergencyContact = sc.next();
        if (emergencyContact.length() > 10 || !emergencyContact.matches("\\d+")) {      //\\d to check if the contact number contains digits 0-9 , + because there can be more than one occurrence of digits
            throw new IllegalArgumentException("Error! Contact number must be up to 10 digits(0-9).");
        }
        // grade input and validation
        System.out.println("Enter your swimming grade level (0-5) : ");
        int gradeLevel;
        int tempGradeLevel = sc.nextInt();
        if (tempGradeLevel >= 0 && tempGradeLevel <= 5) {
            gradeLevel = tempGradeLevel;
        } else {
            throw new IllegalArgumentException("Error! Grade level must be between 0 and 5.");
        }

        // creates a new learner object
        Learner newRegLearner = new Learner(name,gender,age,emergencyContact,gradeLevel);
        //adding newly registered learner to the list of learners
        learners.add(newRegLearner);
        System.out.println("#-------------------NEWLY REGISTERED LEARNER DETAILS-------------------#\n"+newRegLearner.toString());

    }

    // displays lessons according to day, coach , grade
    public void runLessonDisplayInterface() throws InputMismatchException {
        System.out.println("-------------------Days & Timings---------------------"
                +"\n|   Monday   |  Wednesday  |   Friday   |   Saturday |"
                +"\n|   4-5pm    |    4-5pm    |    4-5pm   |    2-3pm   |"
                +"\n|   5-6pm    |    5-6pm    |    5-6pm   |    3-4pm   |"
                +"\n|   6-7pm    |    6-7pm    |    6-7pm   |    Off     |\n");

        System.out.println("Below is the list of the coaches registered for this program.\n");
        //displays the list of all registered coaches with their name and Ids
        showAllCoaches();

        System.out.println("\nHow would you like to view the available lessons ?"
                          +"\nType 'day' to filter by day, 'grade' to filter by grade level, or 'coach' to filter by coach's name : ");
        String filterType = sc.next();
        if (filterType.equalsIgnoreCase("day")) {
            System.out.println("You have to enter one value:-"+"\nFor 'day': 'Monday', 'Wednesday', 'Friday', or 'Saturday'.");
        }
        else if (filterType.equalsIgnoreCase("grade")) {
            System.out.println("You have to enter one value:-"+"\nFor 'grade': '1'( for learners at level 0 & 1), '2', '3', '4', or '5'.");
        }
        else if (filterType.equalsIgnoreCase("coach")) {
            System.out.println("You have to enter one value:-"+"\nFor 'coach': 'Donald', 'Ralph', 'Smith', 'Spencer', or 'Mike'.");
        }
        else{
            throw new InputMismatchException("Error! Invalid filter type. Please type 'day', 'grade', or 'coach' to view lessons accordingly.");
        }

        System.out.println("\nEnter one value for " + filterType + ":-");
        String filterValue = sc.next();
        //checks if the value passed matches with what it's supposed to be for eg, Monday, Wednesday etc.
        if(!checkFilterValue(filterType,filterValue)) {
            throw new InputMismatchException("Error! Invalid value entered for " + filterType + ".");
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

    private Coach findCoachById(int coachId) {      //returns Coach object according to the coachID
        for (Coach coach : coaches) {
            if (coach.getId() == coachId) {
                return coach;
            }
        }
        return null;
    }

    //books lesson for learner
    public void bookLesson() {
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

            if (isLessonAlreadyBooked(learnerId, lessonId)) {
                System.out.println("\nError! You have already an existing booking for this lesson."
                                    +"\nTo rebook the same lesson, either you have to attend it or Cancel it.");
            }
            else if (bookLessonForLearner(learnerId, lessonId)) {
                System.out.println("Lesson booked successfully.");
            }
            else {
                if (!lessons.contains(lessonId)) {
                    System.out.println("Error! Please enter an appropriate lesson ID.");
                }
                else {
                    System.out.println("Error! Failed to book lesson. It might be because it doesn't match lesson's grade level.");
                }
            }
        }
    }

    // checks if the learnerId is associated with the lessonId in a way that indicates booking but not attendance.
    private boolean isLessonAlreadyBooked(int learnerId, int lessonId) {
        SwimmingLesson lesson = findLessonById(lessonId);
        return lesson != null && lesson.isLearnerEnrolled(learnerId);
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

    private Learner findLearnerById(int learnerId) {        //returns Learner object according to the LearnerID
        for (Learner learner : learners) {
            if (learner.getId() == learnerId) {
                return learner;
            }
        }
        return null; // when Learner not found
    }

    // displays available lessons to the learner according to their current grade level and one higher level
    public boolean displayAvailableLessonsForLearner(int learnerId) {
        Learner selectedLearner = findLearnerById(learnerId);
        if (selectedLearner == null) {
            System.out.println("\nError! Learner not found. You need to register as a learner first to a book lesson.");
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

    // helps attend lessons and provide reviews and ratings accordingly
    public void attendLessonAndProvideFeedback() {
        System.out.println("\nAttending a lesson and providing feedback...");
        System.out.println("Enter learner ID: ");
        int learnerId = sc.nextInt();
        System.out.println("Enter ID of the lesson you want to attend: ");
        int lessonId = sc.nextInt();

        // This extra nextLine() consumes the leftover newline character from the previous input, to ensure that input for the review is correctly waited for and captured.
        sc.nextLine();
        //checks  1.if the lesson passed is valid. 2. if learner is enrolled for that particular lesson. if both true enables to provide feedback
        try {
            attendLesson(learnerId, lessonId);
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    // helps in attending lesson, providing review and rating for the lesson attended, and also providing rating for the coach assigned to that lesson
    public void attendLesson(int learnerId, int lessonId) throws IllegalArgumentException {
        SwimmingLesson lesson = findLessonById(lessonId);
        Learner learner = findLearnerById(learnerId);

        // checks if the learnerId entered is a registered one,i.e, it should be present inside the learners list
        if(learner == null ){
            throw new IllegalArgumentException("Error! No learner with this ID has been registered yet.");
        }
        // checks if the lessonId entered belongs to a lesson that exists,i.e, it should be present inside the list of created lessons
        if (lesson == null) {
            throw new IllegalArgumentException("Error! Lesson not found.");
        }
        // checks if the learner is attending a lesson that is appropriate to its grade level
        if (!(learner.getGradeLevel() == lesson.getGrade() || learner.getGradeLevel() + 1 == lesson.getGrade())) {
            throw new IllegalArgumentException("Error! You can only attend lessons of your current grade or one level higher."
                    + "\nYour current grade: " + learner.getGradeLevel() + "\nGrade of lesson you're trying to attend: " + lesson.getGrade());
        }
        // checks if the learnerId passed is enrolled for that particular lesson or not
        if (!lesson.isLearnerEnrolled(learnerId)) {
            throw new IllegalArgumentException("Error! Learner not enrolled in this lesson.");
        }
        System.out.println("Congratulations on completing \'Grade "+lesson.getGrade()+"\' lesson successfully.");

        // checks if the attended lesson's grade is exactly one level higher than the learner's current grade, if true updates learner's grade to a level higher
        if (lesson.getGrade() == learner.getGradeLevel() + 1) {
            learner.updateGradeLevel(lesson.getGrade());
        }

        System.out.println("\nPlease provide a review about the lesson in a few words: ");
        String review = sc.nextLine();
        System.out.println("Please provide a value ranging from 1 to 5 to rate this lesson :-"+"\n1: Very dissatisfied"+"\n2: Dissatisfied"+"\n3: Okay"+"\n4: Satisfied"+"\n5: Very Satisfied"+"\nEnter a value to rate this lesson: ");
        int rating = sc.nextInt();

        // checks if the value of rating is withing range
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Error! Rating must be between 1 and 5.");
        }
        // records feedback for the particular lesson that was attended by storing it inside the reviews HashMaps
        lesson.recordFeedback(learnerId, review);
        // records/gives rating for the particular coach which was given for that particular lesson earlier
        giveCoachRating(lesson.getCoachId(),rating);

        if(lesson.removeLearner(learnerId)) {
        // removes bookedLesson details from the list of bookedLessonIds once the learner has attended those lessons.
        learner.removeBookedLesson(lessonId);
        // stores the lessonId of the lesson that the learner has recently attended in a list, to use it for learner report
        learner.markLessonAsAttended(lessonId);
        System.out.println("Feedback recorded successfully.");
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

    private SwimmingLesson findLessonById(int lessonId) {       //returns lesson object according to the lessonId
        for (SwimmingLesson lesson : lessons) {
            if (lesson.getId() == lessonId) {
                return lesson;
            }
        }
        return null; // Return null if no lesson matches the given ID
    }

    // helps in cancelling a booked lesson, also helps in changing the booking,i.e, booking another lesson in place of previous lesson
    public void changeOrCancelBooking() throws IllegalArgumentException{       //need to implement error handling
        System.out.println("\nChanging or cancelling a booking...");
        System.out.println("Enter learner ID: ");
        int learnerId = sc.nextInt();

        Learner learner = findLearnerById(learnerId);
        //checks if the learnerId entered is a registered one, if so it should be present inside the learners list
        if(learner == null){
            throw new IllegalArgumentException("Error! No learner with this ID has been registered yet.");
        }
        //checks if the list that stores the bookings for each learner is empty
        if(learner.getBookedLessonIds().isEmpty()){
            throw new IllegalArgumentException("You don't have any bookings to cancel/change.");
        }

        System.out.println("Below is the list of the lessons that you have booked:- ");

        //displays list of bookings that learner has, it's his own list that keeps record of bookings
        learner.getBookedLessonIds().forEach(id -> {
            SwimmingLesson lesson = findLessonById(id);
            System.out.println("Lesson ID: " + lesson.getId() + ", Grade: " + lesson.getGrade() + ", Day: " + lesson.getDay() + ", Time: " + lesson.getTimeSlot());
        });

        System.out.println("\nEnter lesson ID of the lesson you want to cancel/change: ");
        int lessonId = sc.nextInt();
        SwimmingLesson lesson = findLessonById(lessonId);

        // checks if the lesson entered by the user is a lesson that has already been created and that's present in the lessons list
        if(lesson == null){
            throw new IllegalArgumentException("Error! Please enter an appropriate lesson ID.");
        }
        // checks if the lesson entered by the user is the one that user has actually booked and not any other available ID's
        if(!learner.getBookedLessonIds().contains(lessonId)){
            throw new IllegalArgumentException("Error! You cannot change/cancel a lesson that you haven't booked yet.");
        }

        System.out.println("Choose what action you wish to perform by selecting appropriate number: "+"\n1. Cancel."+"\n2. Change this booking.");
        int choice = sc.nextInt();

        // to Cancel
        if (choice == 1) {
            if (cancelBooking(learnerId, lessonId)) {
                System.out.println("Booking cancelled successfully.");
            }
        }
        // to change
        else if (choice == 2) {
            //displays list of available lessons according to learners grade level
            displayAvailableLessonsForLearner(learnerId);

            System.out.println("\nFrom the list of lessons given above, enter one lesson ID for booking: ");
            int newLessonId = sc.nextInt();

            SwimmingLesson newLesson = findLessonById(newLessonId);
            // checks if learner enters the same lessonId that he/she wanted to change after selecting the change booking option
            if(lessonId == newLessonId) {
                System.out.println("Error! Attempting to change to the same lesson, i.e, LessonID: " + lessonId + ". No action taken.");
                return;
            }
            // checks if learners grade is appropriate for the new lesson
            if(!learner.canBookLesson(newLesson.getGrade())){
                System.out.println("Error! The chosen lesson's grade is not appropriate for the learner's current grade."
                                    +"\nTry it again with lessons of same grade as learner's.");
                return;
            }
            // Check if the new lesson can accommodate the learner
            if (newLesson.isFull()) {
                System.out.println("Error! The chosen lesson is at full capacity.");
                return;
            }
            if (changeBooking(learnerId, lessonId, newLessonId)) {
                System.out.println("Booking changed successfully.");
            }
            else {
                System.out.println("Failed to change booking.");
            }
        }
        else {
            System.out.println("Invalid choice.");
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
        System.out.println("Monthly Learner Report:");
        for (Learner learner : learners) {
            System.out.println("\nLearner Name: " + learner.getName());
            System.out.println("Learner ID: " + learner.getId());

            // Shows current booked Lessons
            System.out.println("Current Booked Lessons: " + learner.getBookedLessonIds().size());
            for (Integer lessonId : learner.getBookedLessonIds()) {
                SwimmingLesson lesson = findLessonById(lessonId);
                if (lesson != null) {
                    System.out.println("\tLesson ID: " + lesson.getId() + ", Grade: " + lesson.getGrade() + ", Day: " + lesson.getDay() + ", Time: " + lesson.getTimeSlot());
                }
            }

            // Attended Lessons
            System.out.println("Attended Lessons: " + learner.getAttendedLessonIds().size());
            for (Integer lessonId : learner.getAttendedLessonIds()) {
                SwimmingLesson lesson = findLessonById(lessonId);
                if (lesson != null) {
                    System.out.println("\tLesson ID: " + lesson.getId() + ", Grade: " + lesson.getGrade() + ", Day: " + lesson.getDay() + ", Time: " + lesson.getTimeSlot());
                }
            }

            // Canceled Lessons
            System.out.println("Canceled Lessons: " + learner.getCanceledLessonIds().size());
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
        System.out.println("Monthly Coach Report:");
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
}
