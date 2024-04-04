package model;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdvisorTest {
    private boolean admin;
    private ArrayList<String> studentsSupervising;

    @BeforeEach
    public void AdvisorTest () {
       
        admin = false;
        studentsSupervising = new ArrayList<>();
        studentsSupervising.add("0a119e07-a0aa-435e-9b9d-21e5b91b1c39"); // Adding student IDs to the list
        studentsSupervising.add("513b2857-a10f-4aac-87a8-b094604a3001");

        new Student("0a119e07-a0aa-435e-9b9d-21e5b91b1c39", "hillt@email.sc.edu", "Tawnie", "Bo", "Hill", "20", "password", "cd28e093-e17f-49e2-b11c-163cbf993b26", "Sophomore", 6, null, null, null, null, null);
    }  

    @Test
    void testEvaluate_Valid_ShouldPass() {
        // Creating a Calculator object
        Calculator calculator = new Calculator();
        // Evaluating expression
        int sum = calculator.evaluate("1+2+3");
        // Asserting expected result
        assertEquals(6, sum);
    }

    // Additional test cases for Advisor methods

    @Test
    void getStudent_ValidUserID_ReturnsStudent() {
        // Creating an Advisor object
        Advisor advisor = new Advisor("advisor123", studentsSupervising, "John", "", "Doe", "30", "john.doe@example.com", true, "password");
        // Retrieving student
        Student result = advisor.getStudent("0a119e07-a0aa-435e-9b9d-21e5b91b1c39");
        // Verifying result
        assertNotNull(result);
        assertEquals("0a119e07-a0aa-435e-9b9d-21e5b91b1c39", result.getUserID());
    }

    @Test
    void removeStudent_NonAdmin_ReturnsFalse() {
        // Creating an Advisor object
        Advisor advisor = new Advisor("advisor123", studentsSupervising, "John", "", "Doe", "30", "john.doe@example.com", false, "password");
        // Removing student
        boolean result = advisor.removeStudent("513b2857-a10f-4aac-87a8-b094604a3001");
        // Verifying result
        assertFalse(result);
    }

    @Test
    void addStudent_ValidUserID_ReturnsTrue() {
        // Creating an Advisor object
        Advisor advisor = new Advisor("advisor123", studentsSupervising, "John", "", "Doe", "30", "john.doe@example.com", true, "password");
        // Adding student
        boolean result = advisor.addStudent("test123");
        // Verifying result
        assertTrue(result);
        assertTrue(advisor.hasStudent("test123"));
    }

    @Test
    void getStudentList_ReturnsStudentsSupervised() {
        // Creating an Advisor object
        Advisor advisor = new Advisor("advisor123", studentsSupervising, "John", "", "Doe", "30", "john.doe@example.com", true, "password");
        // Getting list of students
        ArrayList<String> result = advisor.getStudentList();
        // Verifying result
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void hasStudent_ExistingStudent_ReturnsTrue() {
        // Creating an Advisor object
        Advisor advisor = new Advisor("advisor123", studentsSupervising, "John", "", "Doe", "30", "john.doe@example.com", true, "password");
        // Checking for existing student
        boolean result = advisor.hasStudent("0a119e07-a0aa-435e-9b9d-21e5b91b1c39");
        // Verifying result
        assertTrue(result);
    }

    @Test
    void hasStudent_NonExistingStudent_ReturnsFalse() {
        // Creating an Advisor object
        Advisor advisor = new Advisor("advisor123", studentsSupervising, "John", "", "Doe", "30", "john.doe@example.com", true, "password");
        // Checking for non-existing student
        boolean result = advisor.hasStudent("nonexisting123");
        // Verifying result
        assertFalse(result);
    }

    @Test
    void getAdmin_ReturnsAdminStatus() {
        // Creating an Advisor object
        Advisor advisor = new Advisor("advisor123", studentsSupervising, "John", "", "Doe", "30", "john.doe@example.com", true, "password");
        // Getting admin status
        boolean result = advisor.getAdmin();
        // Verifying result
        assertTrue(result);
    }
}
