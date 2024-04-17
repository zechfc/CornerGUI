package model;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User {
    private ArrayList<pastCourses> pastCourses;
    private ArrayList<currentCourses> currentCourses;
    private SemesterPlan semesterPlan;
    private MajorMap majorMap;
    private String major;
    private double majorGPA;
    private double overallGPA;
    private boolean failureRisk;
    private int totalCredits;
    private int transferCredits;
    private String applicationArea;
    private Advisor advisor;
    public String advisorID;
    private String advisorNote;
    private String classification;

    public Student(String userID, String email, String firstName, String middleName, String lastName, String age, String password,  
        String major, String classification, int transferCredits, String applicationArea, ArrayList<currentCourses> currentCourses, ArrayList<pastCourses> pastCourses, 
        String advisorID, String advisorNote, String image){
            
        super(userID, firstName, middleName, lastName, age, email, password,image);
        this.major = major;
        this.classification = classification; 
        this.advisorNote = advisorNote;
        this.transferCredits = transferCredits;
        this.totalCredits = 0;
        this.applicationArea = applicationArea;
        this.classification = classification;
        this.currentCourses = currentCourses;
        this.pastCourses = pastCourses;
        this.advisorID = advisorID;
        
        makeSemesterPlan();
    }

    public boolean studentProfile(String userID){

        Student student = StudentList.getInstance().getStudent(userID);
        if(student != null){
            String firstName = student.getFirstName();
            String middleName = student.getMiddleName();
            String lastName = student.getLastName();
            String email = student.getEmail();
            String classification = student.getClassification();
            String college = "UofSC";
            System.out.println(firstName + " " + middleName + " " + lastName + 
                "\nEmail: " + email +
                "\nClassification: " + classification + " at " + college);
            return true;
        }
        return false;
    }

    //this should prob be done in student

    public MajorMap getMajorMap(String userID) {
        if (student() != null && student().getUserID().equals(userID)) {
            if (student() instanceof Student) {
                MajorMap majorMap =(((Student) student()).getMajorMap());
                return majorMap;
            }
        }
        return null;
    }



    public void totalCompletedCredits(int transferCredits, int USC_CREDITS) {
        totalCredits = transferCredits + USC_CREDITS;
    }

    public double overallGPA(double overallGPA){
        return overallGPA;
    }

    public double majorGPA(double majorGPA){
        return majorGPA;
    }

    // public void calcMajorGPA(String major){
    //     ArrayList<Course> temp = MajorList.getInstance().getMajor(major).getMajorCourses();
    //     int length = temp.size();

    //     //looping through the major courses array
    //     for (int i=0; i<length; i++) {
    //         //checking if the name matches a course a student has taken
    //         Course 
    //         if(temp.getCourseName)
    //     };



    // }

    public void updateGPA(double gpa, int credits) {

    }

    public void whatIf(String major, String concentration) {

    }

    public void contactAdvisor(String firstName, String lastName) {
        // is this a priority?
    }

    public void courseFeedback(String courseName) {
        // is this a priority?
    }

    public boolean atFailure(Course course, double grade) {
        return false;
    }

    public double updateGrade(Course course, double grade) {
        return 0.0;
    }

    // This does not work since DataWriter is calling this method, and this is returning
    // a type Advisor, which the DataWriter line is setting the ADVISOR_ID, which is a string
    // public Advisor getAdvisor(){
    //     for(Advisor advisor: AdvisorList.getInstance().getAdvisors()){
    //         Student temp = this.student();
    //         if(advisor.hasStudent(temp))
    //         {
    //             return advisor;
    //         }
    //     }
    //     return null;
    // }

    //The DataWriter should be calling this, no? (I have changed)
    public String getAdvisorID(){
        return advisorID;
    }

    public String getMajorName() {
        return major;
    }

    public String getRealMajorName (String id) {
        return MajorList.getInstance().getMajor(id).getMajor();
    }

    public String getRealAdvisorName(String id){
        return AdvisorList.getInstance().getAdvisor(id).getFirstName() + " " + AdvisorList.getInstance().getAdvisor(id).getLastName();
    }

    public MajorMap getMajorMap() {
        return majorMap;
    }

    // todo - implement functionality
    public boolean updateFailureRisk() {
        failureRisk = true;
        return failureRisk;
    }

    public void editMajorMap(MajorMap newMajorMap) {
        majorMap = newMajorMap;
    }

    public void makeSemesterPlan() {
        this.semesterPlan = new SemesterPlan("Computer Science", pastCourses, this.userID);
    }

    // public SemesterPlan getSemesterPlan() {
    //     return new SemesterPlan(major, (ArrayList<pastCourses>)pastCourses, this.userID);
        

    // }
    public String getSemesterPlan() {
        return semesterPlan.generatePlan();
    }

    public void editAdvisorNote(String note) {
        this.advisorNote = note;
        DataWriter.saveStudents();
    }

    public String getAdvisorNote() {
        return advisorNote;
    }


    public int getTransferCredits() {
        return transferCredits;
    }

    public String getClassification(){
        return classification;
    }

    public ArrayList<pastCourses> getPastCourses(){
        return pastCourses;
    }

    public ArrayList<currentCourses> getCurrentCourses(){
        return currentCourses;
    }

    public String getApplicationArea() {
        return applicationArea;
    }

    public void setApplicationArea(String area) {
        this.applicationArea = area;
    }

    // public void getPastCourseValues(){
    //     for(int i = 0; i < pastCourses.size(); i++){
    //         JSONObject temp = (JSONObject) pastCourses.get(i);
    //         //Gets values from JSON array from student JSON
    //         String id = (String) temp.get("courseID");
    //         String grade = (String) temp.get("grade");
    //         String semester = (String) temp.get("semester");
    //         int year = ((Long) temp.get("year")).intValue();

    //         CourseList courseList = CourseList.getInstance();
    //         ArrayList<Course> courses = courseList.getCourses();
    //         for(Course course : courses){
    //             //Goes through EVERY course (from JSON) and finds matching ID
    //             if(course.getCourseID().equals(id)){
    //                 System.out.println(course.getCourseName() + ": " + grade + ", taken in " + semester + " " + year);
    //             }
    //         }
    //     }
    // }

    // public void getCurrentCourseValues(){
    //     for(int i = 0; i < currentCourses.size(); i++){
    //         JSONObject temp = (JSONObject) currentCourses.get(i);
    //         //Gets values from JSON array from student JSON
    //         String id = (String) temp.get("courseID");
    //         String grade = (String) temp.get("grade");
    //         String semester = (String) temp.get("semester");
    //         int year = ((Long) temp.get("year")).intValue();

    //         CourseList courseList = CourseList.getInstance();
    //         ArrayList<Course> courses = courseList.getCourses();
    //         for(Course course : courses){
    //             //Goes through EVERY course and matches ID
    //             if(course.getCourseID().equals(id)){
    //                 System.out.println(course.getCourseName() + ": " + grade + ", taking " + semester + " " + year);
    //             }
    //         }
    //     }
    // }

    public Student student(){
        return student();
    }

    public string getImage(){
        return image;
    }

    public void editAdvisorID(String advisorID){
        if(this.advisorID == ""){
            this.advisorID = advisorID;
            DataWriter.saveStudents();
        }
    }
}
