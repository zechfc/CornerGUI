package model;
import java.util.ArrayList;
import org.json.simple.JSONArray;

public class Application {
    private StudentList studentList;
    private AdvisorList advisorList;
    private CourseList classList;
    private MajorList majorList;
    private Student student;
    private Advisor advisorUser;
    private Student studentUser;
    private static Application application;
    private Course course;
    private User user;

    public Application() {
        this.studentList = StudentList.getInstance();
        this.advisorList = AdvisorList.getInstance();
        this.classList = CourseList.getInstance();
        this.majorList = MajorList.getInstance();
    }

    public static Application getInstance(){
        if(application == null){
            application = new Application();
        }
        return application;
    }

    public User getUser(){
        return user;
    }

    public int checkUser(String input) {
        if (input == "student") {
            return 1;
        }
        return 2;
    }

    public boolean login(String email, String password) {
        ArrayList<Student> students = DataLoader.getStudents();
        ArrayList<Advisor> advisors = DataLoader.getAdvisors();
        for (Student student : students) {
            if (student != null && student.getEmail().equals(email) && student.getPassword().equals(password)) {
                user = student;
                return true;
            }
        }
        
        for (Advisor advisor : advisors) {
            if (advisor != null && advisor.getEmail().equals(email) && advisor.getPassword().equals(password)) {
                user = advisor;
                return true;
            }
        }
        
        return false;
    }

    public void logout(){
        user = null;
    }

    public void setStudentUser(Student student){
        this.studentUser = student;
    }

    public Student getStudentUser(){
        return studentUser;
    }

    public void setAdvisorUser(Advisor advisor){
        this.advisorUser = advisor;
    }

    public Advisor getAdvisorUser(){
        return advisorUser;
    }

    public Course getCourse(){
        return course;
    }

    public User createStudentAccount(String userID, String firstName, String middleName, String lastName, String age,
            String email, String password, String major, String classification, int transferCredits, String applicationArea, String advisorID, 
            String advisorNote, ArrayList<currentCourses> currentCourses, ArrayList<pastCourses> pastCourses, String image) {
        // Email (username) already used
        if (studentList.emailTaken(email) || advisorList.emailTaken(email)){
            return null;
        }
        Student newStudent = new Student(userID, email, firstName, middleName, lastName, age, password, major, classification, 
                transferCredits, applicationArea, currentCourses, pastCourses, advisorID, advisorNote, image);
        studentList.addStudent(newStudent);
        return newStudent; 
    }

    public User createAdvisorAccount(String userID, String firstName, String middleName, String lastName, String age, String email, String password,
        ArrayList<String> studentsSupervising, boolean admin, String image){
            if (advisorList.emailTaken(email) || studentList.emailTaken(email)){
                return null;
            }
        Advisor newAdvisor = new Advisor(userID, studentsSupervising, firstName, middleName, lastName, age, email, admin, password, image);
        advisorList.addAdvisor(newAdvisor);
        return newAdvisor;
    }

    //this should prob be done in student
    //is this what she ment
    public boolean studentProfile(String userID){
        Student student = studentList.getStudent(userID);
        return student.studentProfile(userID);

    }

    //is this what she ment
    public boolean getMajorMap(String userID) {
        Student student = studentList.getStudent(userID);
        if(student.getMajorMap(userID) != null){
            System.out.println(student.getMajorMap(userID));
            return true;
        }
        return false;
    }

    //what is this suppose to do?
    public boolean editMajorMap(String userID, MajorMap map) {
        Student student = studentList.getStudent(userID);
        if (user != null && user.getUserID().equals(userID)) {
            if (user instanceof Student) {
                ((Student) user).editMajorMap(map);
                return true;
            }
        }
        return false;
    }

    //I dont think this part is needed?
    public boolean getSemesterPlan() {
        if (user != null) {
            if (user instanceof Student) {
                System.out.println(((Student) user).getSemesterPlan());
                return true;
            }
        }
        return false;
    }

    public boolean editAdvisorNote(String userID, String note) {
        User user = studentList.getStudent(userID);
        if (user != null) {
            if (user instanceof Student) {
                ((Student) user).editAdvisorNote(note);
                return true;
            }
        }
        return false;
    }

    public void getAdvisorNote(String userID) {
        User user = studentList.getStudent(userID);
        if (user != null && user.getUserID().equals(userID)) {
            if (user instanceof Student) {
                String advisorNote = ((Student) user).getAdvisorNote();
                System.out.print(advisorNote);
            }
        }
    }

    // This adds a student to the system, do not worry about right now
    // public boolean addStudent(Advisor advisor, String userID){
    //     if(user != null && user.equals(advisor) && advisor.getAdmin() && advisor != null){
    //         Student studentToAdd = studentList.addStudent(userID);
    //         if(studentToAdd != null){
    //             advisorList.addStudent(studentToAdd);
    //         return true;
    //         }
    //     }
    //     return false;
    // }

    public boolean addStudentList(String advisorID, String studentID){
        Student student = studentList.getStudent(studentID);
        Advisor advisor = advisorList.getAdvisor(advisorID);
        if (advisor != null){
            advisor.addStudent(studentID);
            student.editAdvisorID(advisorID);
            return true;
        }
        return false;
    }

    //This removes a student from the system, do not worry about right now
    // public boolean removeStudent(Advisor advisor, String userID, boolean admin) {
    //     if (user != null && user.equals(advisor) && advisor.getAdmin() && advisor != null) {
    //         return advisor.removeStudent(userID);
    //     }
    //     return false;
    // }

    public Student getStudentById(String userID) {
        User user = studentList.getStudentID(userID);
        if (user != null && user instanceof Student) {
            return (Student) user;
        }
        return null;
    }

    public void getStudentApplicationArea(String userID) {
        Student student = studentList.getStudent(userID);
        if (student != null)
            if (student.getApplicationArea() == null) {
                System.out.println("Student has no application area.");
                return;
            }
            System.out.println(student.getApplicationArea());
    }

    public Student getStudent(String email) {
        User user = studentList.getStudent(email);
        if (user != null && user instanceof Student) {
            return (Student) user;
        }
        return null;
    }

    public void getPastClasses(String userID){
        Student student = studentList.getStudent(userID);
        if(student != null){
            student.getPastCourses();
        }
    }

    public String getMajorRequirements(String majorID, String email){
        Major major = majorList.getMajor(majorID);
        if(major != null){
           return major.getMajorRequirementsValues(email);

        }
        return null;
    }

    public String getProgramRequirements(String majorID, String email){
        Major major = majorList.getMajor(majorID);
        if(major != null){
            return major.getprogramRequirementsValues(email);

        }
        return null;

    }
    public String getRemaningProgramRequirements(String majorID, String email){
        Major major = majorList.getMajor(majorID);
        if(major != null){
            return major.getRemaningProgramReq(email);

        }
        return null;

    }

    public String getCarolinaRequirements(String majorID, String email){
        Major major = majorList.getMajor(majorID);
        if(major != null){
          
            return major.getCarolinaCoreRequirementsValues(email);

        }
        return null;

    }

    public String getCarolinaCore(String majorID, String email){
        Major major = majorList.getMajor(majorID);
        if(major != null){
          
            return  major.getCarolinaCoreValues(email);

        }
        return null;

    }
    public Student getStudent(String firstName, String lastName) {
        User user = studentList.getStudent(firstName, lastName);
        if (user != null && user instanceof Student) {
            return (Student) user;
        }
        return null;
    }

    public Student getStudentName(String Name) {
        String[] firstAndLast = Name.split(" ");
        String First = firstAndLast[0]; // 004
        String Last = firstAndLast[1]; // 034556
        User user = studentList.getStudent(First, Last);
        if (user != null && user instanceof Student) {
            return (Student) user;
        }
        return null;
    }

    public boolean getAdvisorID(String userID) {
        if (user != null && user.getUserID().equals(userID)) {
            if (user instanceof Student) {
                ((Student) user).getAdvisorID();
                return true;
            }
        }
        return false;
    }

    public boolean getClass(String classCode) {
        course = classList.getCourse(classCode);
        if(course != null){
            return true;
        }
        return false;
    }

    public ArrayList<Course> getClasses() {
        return classList.getCourses();
    }

    public ArrayList<Student> getStudents(){
        return studentList.getStudents();
    }

    public Advisor getAdvisor(String userID) {
        User user = advisorList.getAdvisor(userID);
        if (user != null && user instanceof Advisor) {
            return (Advisor) user;
        }
        return null;
    }

    public boolean isAdmin(String advisorID) {
        Advisor advisor = advisorList.getAdvisor(advisorID);
        if(advisor != null && advisor.getAdmin() == true){
            return true;
        }
        return false;
    }
}
