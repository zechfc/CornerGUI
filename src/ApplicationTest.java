import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.json.simple.JSONArray;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ApplicationTest {
    private Application app;

    private StudentList students = StudentList.getInstance();
    private ArrayList<Student> studentList = students.getStudents();
    private AdvisorList advisors = AdvisorList.getInstance();
    private ArrayList<Advisor> advisorList = advisors.getAdvisors();

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
        advisorList.add(new Advisor("abcg", studentsSupervising, "Allie", "Sam", "Locke", 
            "31", "safeemail4@email.sc.edu", true, "password4"));

        DataWriter.saveStudents();
        DataWriter.saveAdvisors();
        app = new Application();
    }

    @AfterEach
    public void tearDown(){
        students.getInstance().getStudents().clear();
        DataWriter.saveStudents();
        advisors.getInstance().getAdvisors().clear();
        DataWriter.saveStudents();
    }

    @Test
    void testCheckUserforStudent(){
        assertEquals(1, app.checkUser("student"));
    }

    @Test
    void testCheckUserforAdvisor(){
        assertEquals(2, app.checkUser("advisor"));
    }

    @Test
    void testLoginForStudentSuccess(){
        assertTrue(app.login(1, "safeemail@email.sc.edu", "password1"));
    }

    @Test
    void testLoginForStudentFailure(){
        assertFalse(app.login(2, "safeemail@email.sc.edu", "password1"));
    }

    @Test
    void testLoginForAdvisorSuccess(){
        assertTrue(app.login(2, "safeemail3@email.sc.edu", "password3"));
    }

    @Test
    void testLoginForAdvisorFailure(){
        assertFalse(app.login(1, "safeemail3@email.sc.edu", "password3"));
    }

    @Test
    void testCreateStudentAccountSuccess(){
        assertNotNull(app.createStudentAccount("abch", "Isaac", "Andrew", "Mayernik", "20", 
        "safeemail5@email.sc.edu", "password5", "CS", "Senior", 48, 
        null, "abcg", "...", new JSONArray(), new JSONArray()));
    }

    @Test
    void testCreateStudentAccountEmailTaken(){
        assertNull(app.createStudentAccount("abch", "Isaac", "Andrew", "Mayernik", "20", 
        "safeemail3@email.sc.edu", "password5", "CS", "Senior", 48, 
        null, "abcg", "...", new JSONArray(), new JSONArray()));
        //safeemail3@email.sc.edu is taken, so account should not be created
    }

    @Test
    void testCreateAdvisorAccountSuccess(){
        assertNotNull(app.createAdvisorAccount("abci", "First", "Real", "Last", "54", 
        "safeemail6@email.sc.edu", "password6", new ArrayList<String>(), false));
    }

    @Test
    void testCreateAdvisorEmailTaken(){
        assertNull(app.createAdvisorAccount("abcj", "Holl", "Mond", "Oats", "37", 
        "safeemail7@email.sc.edu", "password7", new ArrayList<String>(), false));
    }

    @Test
    void testStudentProfile(){
        //userid must exist
        assertTrue(app.studentProfile("abcd"));
    }

    @Test
    void testStudentProfileFakeID(){
        //userid does not exist
        assertFalse(app.studentProfile(".sdfl,ca"));
    }

    @Test
    void testStudentProfileAdvisorID(){
        //should not make profile for advisor, but could be a new method?
        assertFalse(app.studentProfile("abcf"));
    }

    @Test
    void testEditAdvisorNote(){
        assertTrue(app.editAdvisorNote("abcd", "Student advised for CSCE211, CSCE247, and MATH217."));
    }

    @Test
    void testEditAdvisorNoteFakeID(){
        assertFalse(app.editAdvisorNote("sklxksl", "Advised by ..."));
        //that userID does not exist, should not edit their adivsorNote
    }

    @Test 
    void testEditAdvisorNoteOnAdvisor(){
        //should fail, an advisor doesn't have an advisor note
        assertFalse(app.editAdvisorNote("abcf", "Advised for whatever"));
    }

    @Test
    void testAddStudentListSuccess(){
        assertTrue(app.addStudentList("abcf", "abce"));
    }

    @Test
    void testAddStudentListNullAdvisor(){
        assertFalse(app.addStudentList(null, "abce"));
    }

    @Test
    void testAddStudentListAdvisorDNE(){
        assertFalse(app.addStudentList("sdgjcs", "abce"));
    }

    @Test
    void testIsAdminNoOneLoggedIn(){
        assertFalse(app.isAdmin(null));
    }

    @Test
    void testIsAdminAdvisorNotAdmin(){
        app.login(2, "safeemail3@email.sc.edu", "password3");
        assertFalse(app.isAdmin("abcf"));
        //this user is not an admin, should return false, but boolean provided is true

        //looking back at the method, it literally just checks if true, 
        // it does not check through the advisor's id if their boolean is true...
    }

    @Test
    void testIsAdminAdvisorAdmin(){
        app.login(2, "safeemail4@email.sc.edu", "password4");
        assertTrue(app.isAdmin("abcg"));
    }

    @Test
    void testStudentIsNotAdmin(){
        assertFalse(app.isAdmin("abce"));
    }
}
