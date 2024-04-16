package model;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Major {
    private String name;
    private String description;
    private ArrayList<CourseReccommended> programRequirements;
    private ArrayList<CourseReccommended> carolinacoreCoursesReq;
    private ArrayList<Course> carolinacoreCourses;
    private ArrayList<CourseReccommended> majorCourses;
    private long totalhours;
    private long carolinaHours;
    private long carolinaReqHours;
    private long majorHours;
    private long totalHoursProgramRequirements;
    private String majorID;

    public Major(String name, ArrayList<CourseReccommended> programRequirements, ArrayList<CourseReccommended> carolinacoreCoursesReq, ArrayList<CourseReccommended> majorCourses,
    String majorID, String description, long carolinaHours,long majorHours,long carolinaReqHours,long totalHoursProgramRequirements, long totalhours)
    {
        this.name = name;
        this.programRequirements = programRequirements;
        this.carolinacoreCoursesReq = carolinacoreCoursesReq;
        this.description = description;
        this.majorCourses = majorCourses;
        this.carolinaReqHours = carolinaReqHours;
        this.majorID = majorID;
        this.majorHours = majorHours;
        this.carolinaHours = carolinaHours;
        this.totalHoursProgramRequirements = totalHoursProgramRequirements;
        this.totalhours = totalhours;

        //System.out.println(programRequirements.get(0));


    }

    public String getMajor() {
		return name;
	}

    public String getMajorID() {
		return majorID;
	}

    public String getDescription() {
		return description;
	}
    public long getTotalHours() {
		return totalhours;
	}

    public ArrayList<CourseReccommended> getprogramRequirements() {    
        return programRequirements;
	}

    public ArrayList<CourseReccommended> getCarolinacoreCoursesReq() {    
        return carolinacoreCoursesReq;
	}

    public ArrayList<CourseReccommended> getCarolinacoreCourses() {    
        return null;
	}

    public ArrayList<CourseReccommended> getMajorCourses() {    
        return majorCourses;
	}


    public String getprogramRequirementsValues(String email){
        String past = compareCoursesPast(programRequirements, email);
        String current = compareCoursesPresent(programRequirements, email);
        String full = past + "\n" + current;
        return full;

    }
    
    
    public String getRemaningProgramReq(String email){
        return compareCoursesPast(programRequirements, email);
    }



    public String getMajorRequirementsValues(String email) {
        String past = compareCoursesPast(majorCourses, email);
        String current = compareCoursesPresent(majorCourses, email);
        String full = past + "\n" + current;
        return full;


    }

        
    public String getRemaningMajorRequirementsValues(String email){
        return compareCoursesPast(programRequirements, email);
    }
    
    public String getCarolinaCoreRequirementsValues(String email) {
        String past = compareCoursesPast(carolinacoreCoursesReq, email);
        String current = compareCoursesPresent(carolinacoreCoursesReq, email);
        String full = past + "\n" + current;
        return full;

    }

    public String getRemaningCarolinaCoreRequirementsValues(String email){
        return compareCoursesPast(programRequirements, email);
    }
    
    

    public String getCarolinaCoreValues(String email) {
        return null;
    }


    public String compareCoursesPast(ArrayList<CourseReccommended> requirements,String email){
        String toString;
        String list ="";
        ArrayList<pastCourses> pastCourses = new ArrayList<>();
        StudentList studentList = StudentList.getInstance();
        ArrayList<Student> students = studentList.getStudents();
        for(Student student : students){
            if (email.equals(student.getEmail())) {
                pastCourses = student.getPastCourses();  
                break; 
            }
        }
        for(int i = 0; i < requirements.size(); i++){
            String id = requirements.get(i).getCourseRecID();
            CourseList courseList = CourseList.getInstance();
            ArrayList<Course> courses =courseList.getCourses();           
            for(int j=0; j<pastCourses.size(); j++){
                String idPastCourse = pastCourses.get(j).getPastCourseID();
                if (idPastCourse.equals(id)) {
                String courseName;
                try {
                     courseName = pastCourses.get(j).getPastCourse(idPastCourse).getCourseName();
                } catch (Exception e) {
                     courseName = "not in JSON " + idPastCourse;
                }                
                String courseGrade = pastCourses.get(j).getPastCourseGrade();    
                String courseSemester = pastCourses.get(j).getPastCourseSemester();             
                long courseYear = pastCourses.get(j).getPastCourseYear();             
                String pass = "";
                    if (courseGrade.compareToIgnoreCase("C") <= 0) 
                    {
                         pass = "fail";    
                    }
                    if(courseGrade.equalsIgnoreCase("C+") || courseGrade.equalsIgnoreCase("B+")){
                        pass = "pass";    
                    }
                    else{
                        pass="pass";
                    }  
                    toString = (courseName + " Grade: " + courseGrade+ " " + courseSemester + " " + courseYear + " " + pass);
                    list += (toString + "\n");
                    }
                }
            }          
            
            if(list.equals(""))
            {
                list = "you have not made any progress in this section yet\n";
            }
            return list;
    }

    public String compareCoursesPresent(ArrayList<CourseReccommended> requirements,String email){
        String toString;
        String list ="Assuming you pass all of your current classes \n";
        ArrayList<currentCourses> currentCourses = new ArrayList<>();
        StudentList studentList = StudentList.getInstance();
        ArrayList<Student> students = studentList.getStudents();
        for(Student student : students){
            if (email.equals(student.getEmail())) {
                currentCourses = student.getCurrentCourses();  
                break; 
            }
        }
        for(int i = 0; i < requirements.size(); i++){
            String id = requirements.get(i).getCourseRecID();
            CourseList courseList = CourseList.getInstance();
            ArrayList<Course> courses =courseList.getCourses();           
            for(int j=0; j<currentCourses.size(); j++){
                String idPastCourse = currentCourses.get(j).getCurrentCourseID();
                if (idPastCourse.equals(id)) {
                String courseName;
                try {
                     courseName = currentCourses.get(j).getCurrentCourse(idPastCourse).getCourseName();
                } catch (Exception e) {
                     courseName = "not in JSON " + idPastCourse;
                }                
                String courseGrade = currentCourses.get(j).getCurrentCourseGrade();             
                String courseSemester = currentCourses.get(j).getCurrentCourseSemester();             
                long courseYear = currentCourses.get(j).getCurrentCourseYear();             

                   
                    toString = (courseName + " Grade: " + courseGrade + " " + courseSemester + " " + courseYear);
                    list += (toString + "\n");
                    }
                }
            }          
            
            if(list.equals(""))
            {
                list = "you are not making any progress in this section yet\n";
            }
            return list;
    }


    public long getProgramReqHours() {
        return this.totalHoursProgramRequirements;
    }

    public long getcarolinaHours() {
        return this.carolinaHours;
    }

    }
