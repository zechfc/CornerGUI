 /**
     * Method to set the major name
     * This method does not currently change the functionality of the code
     * It returns the input major string without making any changes
     */
package model;
import java.util.ArrayList;

public class MajorList {
    private static MajorList majors;
    private static ArrayList<Major> majorList;

    private MajorList() {
        this.majorList = DataLoader.getMajor();
    }

    public static MajorList getInstance() {
        if (majors == null) {
            majors = new MajorList();
        }
        return majors;
    }

    public ArrayList<Major> getMajors(){
        return majorList;
    }

    public Major getMajor(String id) {
        for (Major major : majorList) {
            if (major.getMajorID().equals(id)) {
                return major;
            }
        }
        return null;
    }

    /**
     *
     * @param major String to change major name to
     * @return String containing major name
     */
    public String setMajor(String major){
        // iterate list, when major is found, set major
        return major;
    }
}
