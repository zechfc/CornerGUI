package model;
import java.util.ArrayList;
public class Course {
    private ArrayList<CourseChoice> prerequisite;
    private String courseID;
    private String courseKey;
    private String courseName;
    private String courseDescription;
    private boolean courseAvailability;
    private ArrayList<Semester> seasonOffered;
    private String electiveArea;
    private String learningOutcome;

    private double courseCredits;

    // Not sure all of the things in the constructor are stored.
    public Course(ArrayList<CourseChoice> prereq, String id, String key, String name, String description,
            boolean availability, double credits, ArrayList<String> term, String elecArea, String passingGrade) {
        this.prerequisite = prereq;
        this.courseID = id;
        this.courseKey = key;
        this.courseName = name;
        this.courseDescription = description;
        this.courseAvailability = availability;
        this.seasonOffered = new ArrayList<Semester>();
        for (String s : term)
            this.seasonOffered.add(Semester.valueOf(s));
        this.electiveArea = elecArea;
        this.courseCredits = credits;
    }

    public boolean checkPrerequisites(ArrayList<Course> courses) {
        boolean b = true;
        for (CourseChoice pre : prerequisite) {
            b = b && pre.checkPrerequisites(courses); // This is genius don;t think about it
        }
        return b;
    }
    public boolean checkCoreqs(ArrayList<Course> courses) {
        return true;
    }

    public String setLearningOutcomes(String outcome) {
        return this.learningOutcome=outcome;
    }
    public String getCourseID() {
        return this.courseID;
    }
    public String getCourseKey() {
        return this.courseKey;
    }
    public String getCourseName() {
        return this.courseName;
    }
    public String getDescription() {
        return this.courseDescription;
    }
    public boolean getAvailability() {
        return this.courseAvailability;
    }
    public String getSeasonOffered() {
        String s = "";
        for (Semester sem : this.seasonOffered) 
            s += sem.toString();
        return s;
    }
    public String getElectiveArea() {
        return this.electiveArea;
    }
    public double getCourseCredtis() {
        return this.courseCredits;
    }
    public ArrayList<CourseChoice> getPrereqs() { // Only if you want array itself. For string form look at other method.
        return this.prerequisite;
    }

    public String toString() {
        return this.courseKey + ": " + this.courseName;
    }
    public String getPrereqsToString() {
        String s = "";
        for (CourseChoice cc : this.prerequisite) {
            s += cc.getCoursesToString();
        }
        return s;
    }
}