package model;
import java.util.ArrayList;

public class CourseList {
    private static CourseList courses;
    private static ArrayList<Course> courseList;

    private CourseList() {
        courseList = DataLoader.getCourses();
    }

    public static CourseList getInstance() {
        if (courses == null) {
            courses = new CourseList();
        }
        return courses;
    }

    public ArrayList<Course> getCourses() {
        return courseList;
    }

    public Course getCourse(String key) {
        for (Course course : courseList) {
            if (course.getCourseName().equals(key) || course.getCourseID().equals(key)) {
                return course;
            }
        }
        return null;
    }

    public ArrayList<Course> getCarolinaCoreCourses() {
        for (Course course : courseList) {
            if (course.getCourseName().equals(key) || course.getCourseID().equals(key)) {
                return course;
            }
        }
        return null;
    }

    public Course checkPrereq(Course course, double grade) {
        //Course c = new Course(null, null, null, null, null, false, 0, null, null, grade);
        return null;
    }
}
