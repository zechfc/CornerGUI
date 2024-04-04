package model;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataWriterTest {
    private StudentList students = StudentList.getInstance();
    private ArrayList<Student> studentList = students.getStudents();
    private AdvisorList advisors = AdvisorList.getInstance();
    private ArrayList<Advisor> advisorList = advisors.getAdvisors();
    private CourseList courses = CourseList.getInstance();
    private ArrayList<Course> courseList = courses.getCourses();
    private MajorList majors = MajorList.getInstance();
    private ArrayList<Major> majorList = majors.getMajors();

    @BeforeEach
    public void setup(){
        studentList.clear();
        DataWriter.saveStudents();
        advisorList.clear();
        DataWriter.saveAdvisors();
    }

    @AfterEach
    public void tearDown(){
        students.getInstance().getStudents().clear();
        DataWriter.saveStudents();
        advisors.getInstance().getAdvisors().clear();
        DataWriter.saveStudents();
    }
}
