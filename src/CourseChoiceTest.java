import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.util.ArrayList;
import java.util.Arrays;

@TestInstance(Lifecycle.PER_CLASS)
public class CourseChoiceTest {
    private CourseChoice cc;
    private ArrayList<Course> coursesTaken;
    private Course prereq;
    private Course classRequiringPrereq;
    

    @BeforeEach
    public void setUp() {
        coursesTaken = new ArrayList<>();
        prereq = new Course(new ArrayList<>(), "47c4e89e-9000-4203-ba31-77441dff5028", "ACCT404",
                "Accounting Information Systems I",
                "Accounting systems for business decision-making and effective control of the business enterprise.   FS: 06/01/2022.",
                true, 3.0, new ArrayList<>(Arrays.asList("SPRING", "SUMMER", "FALL")), null, "C");

        classRequiringPrereq = new Course(new ArrayList<>(), "3a9b678a-8dcb-4d3c-9269-3a997710fdf8", "ACCT475",
                "Integrated Business Processes with Enterprise Systems",
                "The integration, configuration, and operation of accounting information within enterprise resource planning and other databases as applied to current business practices.   FS: 02/03/2021.",
                true, 3.0, new ArrayList<>(Arrays.asList("SPRING", "SUMMER", "FALL")), null, "C");

        cc = new CourseChoice("OR", new ArrayList<>(Arrays.asList(prereq.getCourseID(), classRequiringPrereq.getCourseID())));

        coursesTaken.add(prereq);
        coursesTaken.add(classRequiringPrereq);

        cc.linkFromUUIDRelatedClasses(coursesTaken);
    }

    @Nested
    class linkFromUUIDRelatedClasses {
        @BeforeEach
        public void setUp() {
            cc = new CourseChoice("PRE_OR_COREQ", new ArrayList<>(Arrays.asList(prereq.getCourseID(), classRequiringPrereq.getCourseID())));
            // cc.linkFromUUIDRelatedClasses(coursesTaken);
        }

        @Test
        public void test_linkFromUUIDRelatedClasses_Valid() { //Bug on AND
            cc.linkFromUUIDRelatedClasses(coursesTaken);
            assertEquals(coursesTaken, cc.getCourses());        
        }

        @Test
        public void test_linkFromUUIDRelatedClasses_Invalid() {
            cc.linkFromUUIDRelatedClasses(new ArrayList<Course>());
            assertFalse(coursesTaken.equals(cc.getCourses()));
        }

        @Test
        public void test_linkFromUUIDRelatedClasses__Null_Invalid() {    
            

            assertThrows(NullPointerException.class,
            ()->{
                cc.linkFromUUIDRelatedClasses(null);
            });

        }
    }

    //checkPrerequisites
    @Test
    public void test_checkPrerequisites_OR_Valid() {
        ArrayList<Course> courselist = new ArrayList<>(Arrays.asList(prereq));
        assertTrue(cc.checkPrerequisites(courselist));
    }

    @Test
    public void test_checkPrerequisites_OR_Invalid() {
        ArrayList<Course> courselist = new ArrayList<>(Arrays.asList(new Course(new ArrayList<>(),"","","","",false,0.0,new ArrayList<>(),"","")));
        assertFalse(cc.checkPrerequisites(courselist));
    }

    @Nested
    class TestPrereqsAnd {
        @BeforeEach
        public void setUp() {
            cc = new CourseChoice("AND", new ArrayList<>(Arrays.asList(prereq.getCourseID(), classRequiringPrereq.getCourseID())));
            cc.linkFromUUIDRelatedClasses(coursesTaken);
        }

        @Test
        public void test_checkPrerequisites_AND_Valid() { //Bug on AND
            ArrayList<Course> courselist = new ArrayList<>(Arrays.asList(prereq, classRequiringPrereq));
            assertTrue(cc.checkPrerequisites(courselist));
        }

        @Test
        public void test_checkPrerequisites_AND_Invalid() {
            assertFalse(cc.checkPrerequisites(new ArrayList<>()));
        }
    }

    @Nested
    class TestPrereqsPreCoreq {
        @BeforeEach
        public void setUp() {
            cc = new CourseChoice("PRE_OR_COREQ", new ArrayList<>(Arrays.asList(prereq.getCourseID(), classRequiringPrereq.getCourseID())));
            cc.linkFromUUIDRelatedClasses(coursesTaken);
        }

        @Test
        public void test_checkPrerequisites_PRE_OR_COREQ_Valid() { //Bug on AND
            ArrayList<Course> courselist = new ArrayList<>(Arrays.asList(prereq, classRequiringPrereq));
            assertTrue(cc.checkPrerequisites(courselist));
        }

        @Test
        public void test_checkPrerequisites_PRE_OR_COREQ_Invalid() {
            assertFalse(cc.checkPrerequisites(new ArrayList<>()));
        }
    }

    @AfterEach
    public void tearDown() {
        coursesTaken.clear();
    }
}
