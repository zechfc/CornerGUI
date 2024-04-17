package model;
import java.util.ArrayList;


public class Advisor extends User{
    private boolean admin;
    private ArrayList<String> studentsSupervising;

    public Advisor(String userID, ArrayList<String> studentsSupervising, String firstName, String middleName, String lastName, String age, String email, 
        boolean admin, String password){
        super(userID, firstName, middleName, lastName, age, email, password, image);
        this.admin = admin;
        this.studentsSupervising = studentsSupervising;
    }

    //Can't have overloaded methods with the same param types
    public Student getStudent(String userID){
        StudentList studentList= StudentList.getInstance();
        ArrayList<Student> students = studentList.getStudents();
        for (Student student : students) {        
            if(student.getUserID().equals(userID)){
                return student;
            }
        }
        return null;
    }
    //public void getStudent(String lastName){}

    //Removing student from system, must be admin, checked by Application.java
    //Returns true if student removed from system (default false)

    public String getName(){
        return this.firstName + " " + this.middleName + " " + this.lastName;
    }

    public boolean removeStudent(String userID){
        StudentList students = StudentList.getInstance();
        //will call Advisors.removeStudent()
        return false;
    }

    public boolean addStudent(String userID){
        Student student = StudentList.getInstance().getStudentID(userID);
        if(student != null && !hasStudent(student.userID)){
            studentsSupervising.add(student.userID);
            DataWriter.saveAdvisors();
            return true;
        }
        return false;

    }

    public ArrayList<String> getStudentList(){
        return studentsSupervising;
    }

    public boolean hasStudent(String student){
        for (String students: studentsSupervising){
            if(students.equals(student))
            {
                return true;

            }
        }
        return false;
    }


      public Boolean getAdmin() {
        return admin;
      }
}
