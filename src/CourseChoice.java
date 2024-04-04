import java.util.ArrayList;

public class CourseChoice {
    private requireType prerequisite;
    private ArrayList<String> courseIDs;
    private ArrayList<Course> courses;

    public CourseChoice(String prereq, ArrayList<String> courseIDs) {
        //System.out.println(prereq);
        this.prerequisite = requireType.valueOf(prereq);
        this.courseIDs = courseIDs;
        this.courses = new ArrayList<Course>();
    }

    public void linkFromUUIDRelatedClasses(ArrayList<Course> classes) {
        for (String s : courseIDs) {
            for (Course c : classes) {
                if (c.getCourseID().equals(s))
                    courses.add(c);
            }
        }
    }

    public boolean checkPrerequisites(ArrayList<Course> classTaken) {
        if (this.prerequisite.equals(requireType.OR)) {
            for (Course cR : courses) {
                for (Course cT : classTaken) { // Could probably just check the courses themselves, they should match.
                    if (cT.getCourseID().equals(cR.getCourseID()))
                        return true; // Dare I say .contains()
                }
            }
        } else if (this.prerequisite.equals(requireType.AND)) {
            for (Course cR : courses) { // Brain broke pls fix
                boolean b = false;
                for (Course cT : classTaken) {
                    if (cT.getCourseID().equals(cR.getCourseID())) {
                        b = true;
                        break;
                    }
                }
                if (!b)
                    return false;
            }
        } else if (this.prerequisite.equals(requireType.PRE_OR_COREQ)) {
            for (Course cR : courses) {
                for (Course cT : classTaken) { // The same as OR here
                    if (cT.getCourseID().equals(cR.getCourseID()))
                        return true;
                }
            }
        }
        return false;
    }

    public boolean checkCoreqs(ArrayList<Course> classTaking) {
        return true;
    }

     public String getCoursesToString() {
         String s = "";
         if (this.prerequisite.equals(requireType.OR)) s += "One of these courses must be completed: \n";
         else if (this.prerequisite.equals(requireType.AND)) s += "All courses listed below must be completed: \n";
         else if (this.prerequisite.equals(requireType.PRE_OR_COREQ)) s += "This can be taken as a prerequisite or a corequisite: \n";
         else if (this.prerequisite.equals(requireType.COREQ)) s += "This must be taken as a corequisite: \n";
         for (Course c : this.courses) {
             s += c.getCourseName() + ": " + c.getCourseName() + "\n";
         }
         return s;
     }
    public ArrayList<Course> getCourses(){
        return courses;
    }
}
