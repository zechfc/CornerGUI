package model;
import java.util.ArrayList;

import org.json.simple.JSONArray;

public class StudentList {
    private static StudentList students;
    private ArrayList<Student> studentList;

    public StudentList() { // constructor
        this.studentList = DataLoader.getStudents();
    }

    public static StudentList getInstance() {
        if (students == null) {
            students = new StudentList();
        }
        return students;
    }

    public ArrayList<Student> getStudents() {
        return studentList;
    }

    public Student getVerifiedStudent(String email, String password) {
        for (Student student : studentList) {
            if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                return student;
            }
        }
        return null;
    }

    //Will need to change JSONArray to ArrayList
    public void addStudent(String userID, String email, String firstName, String middleName, String lastName, String age, String password,  
        String major, String classification, int transferCredits, String applicationArea, JSONArray currentCourses, JSONArray pastCourses, 
        String advisorID, String advisorNote, String image) {

        studentList.add(new Student(userID, email, firstName, middleName, lastName, age, password, major, classification,
                transferCredits, applicationArea, currentCourses, pastCourses, advisorID, advisorNote, image));
        DataWriter.saveStudents();
    }

    public void addStudent(Student student) {
        studentList.add(student);
        DataWriter.saveStudents();
    }

    public boolean haveStudent(String email) {
        for (Student student : studentList) {
            if (student.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public Student getStudent(String key) {
        for (Student student : studentList) {
            if (student.getEmail().equals(key) || student.getUserID().equals(key)) {
                return student;
            }
        }
        return null;
    }

    public Student getStudentID(String userID) {
        for (Student student : studentList) {
            if (student.getUserID().equals(userID)) {
                return student;
            }
        }
        return null;
    }

    public Student getStudent(String firstName, String lastName) {
        for (Student student : studentList) {
            if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)) {
                return student;
            }
        }
        return null;
    }

    public boolean emailTaken(String email) {
        for (Student student : studentList) {
            if (student.getEmail().equals(email)) {
                return true;
            }
        }
        return false; // email not taken
    }
}
