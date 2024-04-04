package model;
import java.util.ArrayList;

public class AdvisorList {
    private static AdvisorList advisors;
    private ArrayList<Advisor> advisorList;

    private AdvisorList() {
        advisorList = DataLoader.getAdvisors();
    }

    public static AdvisorList getInstance() {
        if (advisors == null) {
            advisors = new AdvisorList();
        }
        return advisors;
    }

    public boolean haveAdvisor(String email) {
        for (Advisor advisor : advisorList) {
            if (advisor.getEmail().equals(email)) {
                return true;
            }
        }
        return false;

    }

    // need this one
    public ArrayList<Advisor> getAdvisors() {
        return advisorList;
    }

    public Advisor getVerfiedAdvisor(String email, String password) {
        // Checks each Advisor in Advisors array list
        for (Advisor Advisor : advisorList) {
            if (Advisor.getEmail().equals(email) && Advisor.getPassword().equals(password)) {
                return Advisor;
            } // checks if Advisornames are equals and passwords are equal
        }
        return null;
    }

    // method for adding to the JSON
    public void addAdvisor(String userId, ArrayList<String> studentsSupervising, String email, String firstName,
            String middleName, String lastName, String age, Boolean admin, String password) {
        advisorList.add(new Advisor(userId, studentsSupervising, email, firstName, middleName, lastName, age, admin, password));
        DataWriter.saveAdvisors();
    }

    public void addAdvisor(Advisor advisor){
        advisorList.add(advisor);
        DataWriter.saveAdvisors();
    }

    public void removeStudent(Student student) {
        // Calls data writer to remove Student from system
    }

    public Advisor getAdvisor(String userID) {
        for (Advisor advisor : advisorList) {
            if (advisor.getUserID().equals(userID)) {
                return advisor;
            }
        }
        return null;
    }

    public boolean emailTaken(String email) {
        for (Advisor advisor : advisorList) {
            if (advisor.getEmail().equals(email)) {
                return true;
            }
        }
        return false; // email not taken
    }
}
