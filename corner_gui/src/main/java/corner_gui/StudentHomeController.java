package corner_gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.*;

public class StudentHomeController implements Initializable{
    @FXML private Label academic_standing;
    @FXML private MenuButton advisor_menu;
    @FXML private ProgressBar degree_progress;
    @FXML private MenuItem email_advisor;
    @FXML private Button gpaButton;
    @FXML private Button gradesButton;
    @FXML private Label label_title;
    @FXML private Button logoutButton;
    @FXML private Button majorMapButton;
    @FXML private VBox prim_info;
    @FXML private Button returnButton;
    @FXML private MenuItem schedule;
    @FXML private Label scholarship_status;
    @FXML private VBox sec_info;
    @FXML private Button see_notes_button;
    @FXML private Button semesterPlanButton;
    @FXML private VBox tert_info;
    @FXML private Label user_advisor;
    @FXML private Label user_class;
    @FXML private Label user_conc;
    @FXML private Label user_email;
    @FXML private Label user_gpa;
    @FXML private Label user_major;
    @FXML private Label user_majorgpa;
    @FXML private Label user_name;
    @FXML private Label user_notes;
    private Application application;
    private Student user;
    private String fxml;

    @FXML
    void onEmailAdvisorClicked(ActionEvent event) {

    }

    @FXML
    void onGPAClicked(ActionEvent event) {

    }

    @FXML
    void onGradesClicked(ActionEvent event) {

    }

    @FXML
    void onLogoutClicked(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    void onMajorMapClicked(ActionEvent event) {

    }

    @FXML
    void onNotesClicked(ActionEvent event) {
        user_notes.setText(user.getAdvisorNote());
    }

    @FXML
    void onReturnClicked(ActionEvent event) throws IOException{
        App.setRoot("studenthome");
    }

    @FXML
    void onScheduleClicked(ActionEvent event) {

    }

    @FXML
    void onSemesterPlanClicked(ActionEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        application = Application.getInstance();
        user = (Student) application.getUser();

        label_title.setText(user.getFirstName() + "'s Profile");
        
        //primary
        user_name.setText("Name: " + user.getFirstName() + " " + user.getLastName()); //can add middle name
        user_email.setText("Email: " + user.getEmail());
        
        //secondary
        //user_major.setText("Major: " + user.getMajorName());
        user_gpa.setText("GPA: "); //no get gpa method atm
        user_majorgpa.setText("Major GPA: "); //no get major gpa method atm
        //user_class.setText("Class Level: " + user.getClassification());
        //user_conc.setText("Concentration: " + user.getApplicationArea()); //are app area and concentration the same thing in our code?

        //tertiary
        //user_advisor.setText("Advisor: " + user.getAdvisorID()); //this needs to get the advisor's name, not ID
        scholarship_status.setText("Scholarship Status: ");
        academic_standing.setText("Academic Standing: "); //do we want this and the one above?
    }
}