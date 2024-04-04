import java.util.ArrayList;
public class CourseReccommended {
    private String courseID;
    private String recommendedTime;
    private String recommendedTerm;
    

    public CourseReccommended(String courseID, String recommendedTime, String recommendedTerm) {
        this.courseID = courseID;
        this.recommendedTime = recommendedTime;
        this.recommendedTerm = recommendedTerm;
        
    }
    public String getCourseRecID(){
        return this.courseID;
       }

   public String getCourseRecTime(String courseID){
    return this.recommendedTime;
   }

   public String getCourseRecTerm(String courseID){
    return this.recommendedTerm;
   }
   public Course getCourseRec(String courseID){
       return CourseList.getInstance().getCourse(courseID);
}

}