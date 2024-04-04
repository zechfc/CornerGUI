import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataLoaderTest {
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
        //JSONArrays will be changed to arraylists
        studentList.add(new Student("abcd", "safeemail@email.sc.edu", "Anthony", "John", "Simmons",
            "21", "password1", "CS", "Senior", 14, null, new JSONArray(), new JSONArray(), "abcg", "..."));
        studentList.add(new Student("abce", "safeemail2@email.sc.edu", "Sally", "Ann", "Seashell",
            "19", "password2", "CIS", "Sophomore", 6, null, new JSONArray(), new JSONArray(), "", "..."));
        
        advisorList.clear();
        advisorList.add(new Advisor("abcf", new ArrayList<String>(), "Connor", "Bill", "Johnson",
            "35", "safeemail3@email.sc.edu", false, "password3"));
        ArrayList<String> studentsSupervising = new ArrayList<String>();
        studentsSupervising.add("abcd");
        advisorList.add(new Advisor("abcg", studentsSupervising, "Allie", "Sam", "null", 
            "31", "safeemail4@email.sc.edu", true, "password4"));

        DataWriter.saveStudents();
        DataWriter.saveAdvisors();
    }

    @AfterEach
    public void tearDown(){
        students.getInstance().getStudents().clear();
        DataWriter.saveStudents();
        advisors.getInstance().getAdvisors().clear();
        DataWriter.saveStudents();
    }

    @Test
    void testGetStudentSize(){
        studentList = DataLoader.getStudents();
        assertEquals(2, studentList.size());
    }

    @Test
    void testGetStudentSizeZero(){
        students.getInstance().getStudents().clear();
        DataWriter.saveStudents();
        assertEquals(0, studentList.size());
    }

    @Test
    void testGetAdvisorSize(){
        advisorList = DataLoader.getAdvisors();
        assertEquals(2, advisorList.size());
    }

    @Test
    void testGetAdvisorSizeZero(){
        advisors.getInstance().getAdvisors().clear();
        DataWriter.saveAdvisors();
        assertEquals(0, advisorList.size());
    }

    @Test
    void testStudentSupervisingSizeOne(){
        advisorList = DataLoader.getAdvisors();
        Advisor advisor2 = AdvisorList.getInstance().getAdvisor("abcg");
        assertEquals(1, advisor2.getStudentList().size());
    }

    @Test
    void testStudentSupervisingSizeZero(){
        advisorList = DataLoader.getAdvisors();
        Advisor advisor1 = AdvisorList.getInstance().getAdvisor("abcf");
        assertEquals(0, advisor1.getStudentList().size());
    }
}
