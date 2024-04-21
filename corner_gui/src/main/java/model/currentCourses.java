// Model class for representing current courses
package model;
import java.util.ArrayList;
public class currentCourses {
    private String courseID;// Unique identifier for the course
    private String grade;// Grade obtained in the course
    private String semester;// Semester in which the course was taken
    private long year;// Year in which the course was taken

    
    // Constructor for CurrentCourses class
    public currentCourses(String courseID, String grade, String semester,long year) {
        this.courseID = courseID;
        this.grade = grade;
        this.semester = semester;
        this.year = year;
        
    }

   public String getCurrentCourseID(){
    return this.courseID;
   }

   public String getCurrentCourseGrade(){
    return this.grade;
   }

   public String getCurrentCourseSemester(){
    return this.semester;
   }

   public long getCurrentCourseYear(){
    return this.year;
   }
   public Course getCurrentCourse(String courseID){
       return CourseList.getInstance().getCourse(courseID);
}

}