package model;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {

	public static void saveStudents() {
		StudentList studentList = StudentList.getInstance();
		ArrayList<Student> students = studentList.getStudents();
		JSONArray jsonStudents = new JSONArray();

		// creating all the json objects
		for (int i = 0; i < students.size(); i++) {
			jsonStudents.add(getStudentJSON(students.get(i)));
		}

		// Write JSON file
		try (FileWriter file = new FileWriter(STUDENT_FILE_NAME)) { //REMOVE TRUE later
			file.write(jsonStudents.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveAdvisors() {
		AdvisorList advisorList = AdvisorList.getInstance();
		ArrayList<Advisor> advisors = advisorList.getAdvisors();
		JSONArray jsonAdvisors = new JSONArray();

		// creating all the json objects
		for (int i = 0; i < advisors.size(); i++) {
			jsonAdvisors.add(getAdvisorJSON(advisors.get(i)));
		}

		// Write JSON file
		try (FileWriter file = new FileWriter(ADVISOR_FILE_NAME)) { // REMOVE TRUE later
			file.write(jsonAdvisors.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static JSONObject getStudentJSON(Student student) {
		JSONObject studentDetails = new JSONObject();

		studentDetails.put(USER_ID, student.getUserID());
		studentDetails.put(ADVISOR_ID, student.getAdvisorID());
		studentDetails.put(USER_FIRST_NAME, student.getFirstName());
		studentDetails.put(USER_MIDDLE_NAME, student.getMiddleName());
		studentDetails.put(USER_LAST_NAME, student.getLastName());
		studentDetails.put(USER_PASSWORD, student.getPassword());
		studentDetails.put(USER_USER_NAME, student.getEmail());
		studentDetails.put(USER_AGE, student.getUserAge());
		studentDetails.put(MAJOR, student.getMajorName());
		studentDetails.put(CLASSIFICATION, student.getClassification());
		studentDetails.put(NOTES, student.getAdvisorNote());
		studentDetails.put(TRANSFER_CREDITS, student.getTransferCredits());
		studentDetails.put(APPLICATION_AREA, student.getApplicationArea());

		JSONArray pastCourses = convertToJsonArray(student.getPastCourses());
		JSONArray currentCourses = convertToJsonArray(student.getCurrentCourses());
		studentDetails.put(COURSES_PRESENT, currentCourses);
		studentDetails.put(COURSES_PAST, pastCourses);

		studentDetails.put(IMAGE, student.getUserImage());


		return studentDetails;
	}

	public static JSONArray convertToJsonArray(ArrayList<?> array){
		JSONArray jsonArray = new JSONArray();

		for(Object obj : array){
			if(obj instanceof pastCourses){
				pastCourses course = (pastCourses) obj;
				JSONObject courseJSON = new JSONObject();
				courseJSON.put(COURSE_ID, course.getPastCourseID());
				courseJSON.put(GRADE, course.getPastCourseGrade());
            	courseJSON.put(SEMESTER, course.getPastCourseSemester());
            	courseJSON.put(YEAR, course.getPastCourseYear());
            	jsonArray.add(courseJSON);
        } else if (obj instanceof currentCourses) {
            currentCourses course = (currentCourses) obj;
            JSONObject courseJSON = new JSONObject();
            courseJSON.put(COURSE_ID, course.getCurrentCourseID());
            courseJSON.put(GRADE, course.getCurrentCourseGrade());
            courseJSON.put(SEMESTER, course.getCurrentCourseSemester());
            courseJSON.put(YEAR, course.getCurrentCourseYear());
            jsonArray.add(courseJSON);
			}
		}
		return jsonArray;
	}

	// todo
	public static JSONObject getCoursesJSON(Course c) {
		JSONObject courses = new JSONObject();
		return courses;
	}

	public static JSONObject getAdvisorJSON(Advisor advisor) {
		JSONObject advisorDetails = new JSONObject();
		
		advisorDetails.put(STUDENT_LIST, advisor.getStudentList());
		advisorDetails.put(USER_ID, advisor.getUserID());
		advisorDetails.put(USER_FIRST_NAME, advisor.getFirstName());
		advisorDetails.put(USER_MIDDLE_NAME, advisor.getMiddleName());
		advisorDetails.put(USER_LAST_NAME, advisor.getLastName());
		advisorDetails.put(USER_PASSWORD, advisor.getPassword());
		advisorDetails.put(USER_AGE, advisor.getUserAge());
		advisorDetails.put(USER_USER_NAME, advisor.getEmail());
		advisorDetails.put(ADMIN, advisor.getAdmin());
		advisorDetails.put(IMAGE, advisor.getUserImage());
		
		return advisorDetails;
	}
}
