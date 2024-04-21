package model;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SemesterPlan {
        private SemesterTextFormatter studentRequirements;
        protected ArrayList<String> SemesterPlan;
        private String major;
        private String name;
        private int semestart;

        public SemesterPlan(String major, ArrayList<pastCourses> pastCourses, String name) {
                this.studentRequirements = new SemesterTextFormatter();
                this.SemesterPlan = new ArrayList<>();
                this.major = major;
                this.name = name;
                // Generate semester plan based on completed courses and major requirements
        }

        private class SemesterTextFormatter {
                private Map<Integer, List<String>> plan;

                public SemesterTextFormatter() {
                        this.plan = new HashMap<>();
                }

                public void addCourse(int semester, String course) {
                        for (List<String> courses : plan.values()) {
                                if (courses.contains(course.toString())) {
                                        return; // Course already exists in the plan, no need to add it again
                                }
                        }
                        plan.computeIfAbsent(semester, k -> new ArrayList<>()).add(course);
                } // I am abusing the fact that we can assume they are following the right order

                public void addCourseRegardless(int semester, String course) {
                        plan.computeIfAbsent(semester, k -> new ArrayList<>()).add(course);
                }

                public String buildForSemester(int i) {
                        StringBuilder builder = new StringBuilder();
                        builder.append("Semester ").append(i).append("\n");
                        List<String> courses = plan.getOrDefault(i, new ArrayList<>());
                        for (String course : courses) {
                                if (course != null) {
                                        builder.append(course).append("\n");
                                }
                        }
                        return builder.toString();
                }

                public String generatePlan() {
                        StringBuilder builder = new StringBuilder();
                        String sem = "";
                        for (int semester = 1; semester <= 8; semester++) {
                                if (semester % 2 == 0) sem = "Spring "; 
                                else sem = "Fall ";
                                builder.append(sem).append((int)semestart+(semester/2)).append(":\n");
                                List<String> courses = plan.getOrDefault(semester, new ArrayList<>());
                                for (String course : courses) {
                                        if (course != null) {
                                                builder.append(course).append("\n");
                                        }
                                }
                        }
                        return builder.toString();
                }
        }


        // 8 semester plan
        public String generatePlan() {
                // Logic to generate semester plan based on completed courses and major
                // requirements
                // This can include checking prerequisites, corequisites, and other major
                // requirements
                // and then planning out the upcoming semesters accordingly

                // csce major map is
                /*
                 * Student student = new Student("u123456", "john.doe@example.com", "John",
                 * "William", "Doe", "25", "securePassword123",
                 * "Computer Science", "Senior", 30, new JSONArray(),
                 * new JSONArray(), "a987654",
                 * "John is doing well in his courses and is on track to graduate next semester."
                 * );
                 */
                Student student = StudentList.getInstance().getStudent(name);
                if (major.equals("Computer Science")) {
                        // find what year and semester student is in
                        //cant hard code major ID 
                        Major m = MajorList.getInstance().getMajor("a31c3094-3470-4c46-a45f-3b1001d15da2");
                        //Need to fix the above
                        CourseList cList = CourseList.getInstance();
                        ArrayList <pastCourses> coursesTaken = student.getPastCourses();
                        ArrayList <CourseReccommended> coursesRequired = m.getprogramRequirements();
                        // iterate and find the specific course
                        // String[] courseTypes = {"getCarolinacoreCourses","getMajorCourses"};
                        semestart = (int) (coursesTaken.get(0).getPastCourseYear());
                        
                        int semester = 0;
                        int currentSemester = 0;
                        for (int i = 0; i < coursesTaken.size(); i++) {
                                pastCourses currCourse = coursesTaken.get(i);
                                Course w = cList.getCourse(coursesTaken.get(i).getPastCourseID());
                                if (w == null) continue;
                                String term = currCourse.getPastCourseSemester();
                                semester = ((int)(currCourse.getPastCourseYear()) - semestart)* 2;
                                if (term.equals("fall"))
                                        semester += 1;
                                this.studentRequirements.addCourseRegardless(semester,
                                                w.toString() + "\t" + w.getCourseCredtis() + "\t" + currCourse.getPastCourseGrade());
                        }
                        ArrayList <currentCourses> coursesTaking = student.getCurrentCourses();
                        for (int i = 0; i < coursesTaking.size(); i++) {
                                currentCourses currCourse = coursesTaking.get(i);
                                Course w = cList.getCourse(coursesTaking.get(i).getCurrentCourseID());
                                if (w == null) continue;
                                String term = currCourse.getCurrentCourseSemester();
                                semester = (((int)currCourse.getCurrentCourseYear()) - semestart)
                                                * 2;
                                if (term.equals("fall"))
                                        semester += 1;
                                currentSemester = semester;
                                this.studentRequirements.addCourseRegardless(semester,
                                                w.toString() + "\t" + w.getCourseCredtis());
                        }
                        long h = 0;
                        long require = m.getProgramReqHours();
                        for (int i = 0; i < coursesRequired.size() && h < require; i++) {
                                String time = (String) coursesRequired.get(i).getCourseRecTime();
                                if (time == null)
                                        continue;
                                int sem = 0;
                                if (time.equals("Senior")) {
                                        sem = 6;
                                } else if (time.equals("Junior")) {
                                        sem = 4;
                                } else if (time.equals("Sophmore")) {
                                        sem = 2;
                                }
                                if (coursesRequired.get(i).getCourseRecTerm().equals("Spring"))
                                        sem += 1;
                                if (sem < currentSemester) continue;
                                Course c = cList.getCourse(coursesRequired.get(i).getCourseRecID());
                                //h += c.getCourseCredtis();
                                this.studentRequirements.addCourse(sem + 1, c.toString() + "\t" + c.getCourseCredtis());
                        }
                        ArrayList<CourseReccommended> carolinaCores = m.getCarolinacoreCoursesReq();
                        h = 0;
                        require = m.getcarolinaHours();
                        for (int i = 0; i < carolinaCores.size(); i++) {
                                String time = (String) carolinaCores.get(i).getCourseRecTime();
                                int sem = 0;
                                if (time.equals("Senior")) {
                                        sem = 6;
                                } else if (time.equals("Junior")) {
                                        sem = 4;
                                } else if (time.equals("Sophmore")) {
                                        sem = 2;
                                }
                                if ((carolinaCores.get(i).getCourseRecTerm()).equals("Spring"))
                                        sem += 1;
                                if (sem < currentSemester) continue;
                                Course c = cList.getCourse(carolinaCores.get(i).getCourseRecID());
                                //h += c.getCourseCredtis();
                                this.studentRequirements.addCourse(sem + 1, c.toString() + "\t" + c.getCourseCredtis());
                        }
                        ArrayList<CourseReccommended> majorReqs = m.getMajorCourses();
                        h = 0;
                        require = m.getcarolinaHours();
                        for (int i = 0; i < majorReqs.size() && h < require; i++) {
                                String time = majorReqs.get(i).getCourseRecTime();
                                int sem = 0;
                                if (time.equals("Senior")) {
                                        sem = 6;
                                } else if (time.equals("Junior")) {
                                        sem = 4;
                                } else if (time.equals("Sophmore")) {
                                        sem = 2;
                                }
                                if ((majorReqs.get(i).getCourseRecTerm()).equals("Spring"))
                                        sem += 1;
                                if (sem < currentSemester) continue;
                                Course c = cList.getCourse(majorReqs.get(i).getCourseRecID());
                                //h += c.getCourseCredtis();
                                this.studentRequirements.addCourse(sem + 1, c.toString() + "\t" + c.getCourseCredtis());
                        }
                        String s = this.studentRequirements.generatePlan();
                        return s;
                }
                return "";
        }

        /*
         * Semester 1
         * ENGL 101: Critical Reading and Composition
         * MATH 141: Calculus
         * Prereq: C CC-CMW
         * Prereq: C or better in MATH 112/115/116 or Math placement test score
         * CSCE 145: Algorithmic Design I
         * Prereq or Coreq: MATH 111 or 115
         * CSCE 190: Computing in the Modern World
         * Prereq or Coreq: CSCE 145, 204, 205, or 206
         * 
         * Semester 2
         * ENGL 102: Rhetoric and Composition
         * Prereq: ENGL 101
         * MATH 142: Calculus II
         * Prereq: MATH 141
         * CHEM 111: General Chemistry I
         * Prereq: MATH 111/115/122/141 or higher math or Math placement test score
         * Coreq: CHEM 111L (CHEM 111 only)
         * Coreq: MATH 141
         * Prereq or coreq: PHYS 211L (PHYS 211 only)
         * CHEM 111L: General Chemistry I Lab
         * Prereq: MATH 111 or 115
         * Prereq or Coreq: CHEM 111 (CHEM 111L only)
         * Prereq or Coreq: PHYS 211 (PHYS 211L only)
         * CSCE 146: Algorithmic Design II
         * Prereq: CSCE 145
         * Prereq or Coreq: MATH 122 or 141
         * CSCE 215: UNIX/Linux Fundamentals
         * Prereq: CSCE 145
         * 
         * Semester 3
         * CSCE 211: Digital Logic Design
         * Prereq: MATH 141
         * CSCE 240: Advanced Programming Techniques
         * Prereq: D in CSCE 215 & CSCE 146
         * MATH 374: Discrete Structures
         * Prereq: MATH 142 & CSCE 146
         * CHEM 112: General Chemistry II
         * Prereq: CHEM 111 or 141 & MATH 111/115/122/141 or higher math
         * Coreq: CHEM 112L (CHEM 112 only)
         * Coreq: PHYS 211 and MATH 142
         * Coreq: PHYS 212L (PHYS 212 only)
         * CHEM 112L: General Chemistry II Lab
         * Prereq: CHEM 111/111L/141
         * Prereq or Coreq: CHEM 112 (CHEM 112L only)
         * Prereq or Coreq: PHYS 212 (PHYS 212L only)
         * SPCH 140: Public Communication
         * Coreq: SPCH 145 or SPCH 230
         * SPCH 145: Online Public Communication
         * Coreq: SPCH 140 or SPCH 230
         * SPCH 230: Business and Professional Speaking
         * Coreq: SPCH 140 or SPCH 145
         * 
         * Semester 4
         * CSCE 212: Intro. to Computer Architecture
         * Prereq: D in CSCE 211 & either CSCE 145 or 206
         * CSCE 247: Software Engineering
         * Prereq: CSCE 146
         * Laboratory Science Elective
         * See Bulletin listing
         * MATH 241: Vector Calculus
         * Prereq: MATH 142
         * Carolina Core GSS
         * 
         * Semester 5
         * CSCE 311: Operating Systems
         * Prereq: CSCE 240 & CSCE 210 or 212
         * CSCE 330: Programming Language Structures
         * Prereq: CSCE 240; MATH 174 or 374 or 574
         * CSCE 350: Data Structures & Algorithms
         * Prereq: CSCE 240; MATH 174 or 374 or 574
         * CSCE 390: Prof. Issues in Computer Science Engr.
         * Carolina Core GSS
         * ENGL 462: Technical Writing
         * Prereq: ENGL 101 & 102
         * ENGL 463: Business Writing
         * Prereq: ENGL 101 & 102
         * Application Area Elective
         * See Bulletin listing
         * 
         * Semester 6
         * CSCE 416: Introduction to Computer Networks
         * Prereq: CSCE 146
         * CSCE Major Elective
         * See Bulletin listing
         * STAT 509: Statistics for Engineers
         * Prereq: MATH 142
         * Liberal Arts Elective
         * See Bulletin listing
         * Application Area Elective
         * See Bulletin listing
         */

        public String getSemesterPlanSemester(int i) {
                return studentRequirements.buildForSemester(i);
        }

        public ArrayList<String> getStudentReq(String majorReq) {
                // Logic to retrieve specific student requirements based on major
                return new ArrayList<>(); // Placeholder
        }

        public ArrayList<Course> remainingCourses(String input) {
                // Logic to calculate remaining courses based on input
                return new ArrayList<>(); // Placeholder
        }

        public int totalCourses(String input) {
                // Logic to calculate total number of courses based on input
                return 0; // Placeholder
        }

        public ArrayList<String> output(String semesterPlan) {
                // Logic to output semester plan
                return new ArrayList<>(); // Placeholder
        }

        public int progress(double remainingCourses, double totalCourses) {
                // Logic to calculate progress based on remaining and total courses
                return 0; // Placeholder
        }
}
