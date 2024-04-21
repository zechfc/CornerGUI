package corner_gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.*;

public class StudentHomeController implements Initializable{
    @FXML private Label semesterPlanText;
    @FXML private Label label_title;
    @FXML private Button logoutButton;
    @FXML private Button returnButton;
    @FXML private Button see_notes_button;
    @FXML private Label user_advisor;
    @FXML private Label user_class;
    @FXML private Label user_conc;
    @FXML private Label user_email;
    @FXML private Label user_gpa;
    @FXML private Label user_major;
    @FXML private Label user_name;
    @FXML private DialogPane user_notes_box;
    @FXML private Button editNote;
    @FXML private TextField note_text;
    @FXML private ImageView student_image;
    @FXML private TextField course_search_text;
    @FXML private Button closeCourseBox;
    @FXML private DialogPane course_box;
    @FXML private Button addStudentButton;
    @FXML private Button closeNoteBox;
    
    private Application application;
    private Student user;
    private Advisor advisorUser;
    private ArrayList<Student> students;
    private String fxml;
    private boolean box_visible = false;
    private Course course;

    @FXML
    void onLogoutClicked(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    void onNotesClicked(ActionEvent event) throws IOException{
        box_visible = !box_visible;
        if(box_visible){
            closeNoteBox.setVisible(true);
            user_notes_box.setVisible(box_visible);
            user_notes_box.setContentText(user.getAdvisorNote());
            if(application.getUser() instanceof Advisor){
                editNote.setVisible(true);
            }
        }else {
            user_notes_box.setVisible(false);
            closeNoteBox.setVisible(false);
            editNote.setVisible(false);
            note_text.setVisible(false);
        }    
    }

    @FXML
    void onCloseNoteBox(ActionEvent event) {
        user_notes_box.setVisible(false);
        closeNoteBox.setVisible(false);
        editNote.setVisible(false);
        note_text.setVisible(false);
        box_visible = false;
    }

    @FXML
    void onEditNoteClicked(ActionEvent event) throws IOException{
        note_text.setVisible(true);
        note_text.setOnKeyPressed(new EventHandler<KeyEvent>() {

            public void handle(KeyEvent e){
                if(e.getCode().equals(KeyCode.ENTER)){
                    note_text.setVisible(false);
                    String note = note_text.getText();
                    user.editAdvisorNote(note);
                    user_notes_box.setContentText(user.getAdvisorNote());
                }
            }
        });
    }

    @FXML
    void onCourseSearchClicked(ActionEvent event) throws IOException{
        String text = course_search_text.getText();

        course_search_text.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e){
                if(e.getCode().equals(KeyCode.ENTER)){
                    if(!application.getClass(text)){
                        return;
                    }
                    course = application.getCourse();
                    course_box.setVisible(true);
                    closeCourseBox.setVisible(true);
                    course_box.setContentText("Course Name: " + course.getCourseName() + "\n"
                                                + "Course Code: " + course.getCourseKey() + "\n"
                                                + "Course Description: " + course.getDescription());
                }
            }
        });
    }

    @FXML
    void onReturnClicked(ActionEvent event) throws IOException{
        if(application.getUser() instanceof Advisor){
            App.setRoot("advisorhome");
        }else {
            if(fxml == null){
                fxml = "studenthome";
            }
            App.setRoot(fxml);
            if(fxml == "studenthome"){
                App.setRoot("home");
            }
        }
    }

    @FXML
    void onCloseCourseBox(ActionEvent event) throws IOException {
        course_box.setVisible(false);
        closeCourseBox.setVisible(false);
    }

    @FXML
    void onAddStudentClicked(ActionEvent event) {
        application.addStudentList(advisorUser.getUserID(), user.getUserID());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        application = Application.getInstance();
        if (application.getUser() instanceof Student){
            user = (Student) application.getUser();
            addStudentButton.setVisible(false);
        }else {
            user = application.getStudentUser();
            advisorUser = application.getAdvisorUser();
            if(advisorUser != null && !advisorUser.getStudentList().contains(user.getUserID())){
                addStudentButton.setVisible(true);
            }else {
                addStudentButton.setVisible(false);
            }
        }
        label_title.setText(user.getFirstName() + "'s Profile");
        
        user_name.setText("Name: " + user.getFullName());
        user_email.setText("Email: " + user.getEmail());
        user_major.setText("Major: " + user.getRealMajorName(user.getMajorName()));
        user_gpa.setText("GPA: "); //no get gpa method atm
        user_class.setText("Class Level: " + user.getClassification());

        if(user.getAdvisorID().equals("")){
            user_advisor.setText("No advisor");
        } else {
            user_advisor.setText("Advisor: " + user.getRealAdvisorName(user.getAdvisorID()));
        }
            

        // user_advisor.setText("Advisor: " + user.getRealAdvisorName(user.getAdvisorID())); 

        user_notes_box.setVisible(false);
        editNote.setVisible(false);
        note_text.setVisible(false);
        closeNoteBox.setVisible(false);
        course_box.setVisible(false);
        closeCourseBox.setVisible(false);

        // semesterPlanText 
        semesterPlanText.setText(user.getSemesterPlan());

        //Student Image
        Image image = new Image(getClass().getResourceAsStream("/image/" + user.getUserImage()));
        student_image.setImage(image);
    }
}