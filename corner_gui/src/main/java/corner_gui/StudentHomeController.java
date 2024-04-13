package corner_gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.*;

public class StudentHomeController implements Initializable{
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
    @FXML private Button see_notes_button;
    @FXML private Button semesterPlanButton;
    @FXML private Label user_advisor;
    @FXML private Label user_class;
    @FXML private Label user_conc;
    @FXML private Label user_email;
    @FXML private Label user_gpa;
    @FXML private Label user_major;
    @FXML private Label user_majorgpa;
    @FXML private Label user_name;
    @FXML private DialogPane user_notes_box;
    @FXML private Button editNote;
    @FXML private TextField note_text;
    @FXML private TextField course_search_text;
    private Application application;
    private Student user;
    private String fxml;

    @FXML
    void onEmailAdvisorClicked(ActionEvent event) throws IOException{

    }

    @FXML
    void onGPAClicked(ActionEvent event) throws IOException{

    }

    @FXML
    void onGradesClicked(ActionEvent event) throws IOException{

    }

    @FXML
    void onLogoutClicked(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    void onMajorMapClicked(ActionEvent event) throws IOException{

    }

    @FXML
    void onNotesClicked(ActionEvent event) throws IOException{
        user_notes_box.setVisible(true);
        user_notes_box.setContentText(user.getAdvisorNote());

        if(application.getUser() instanceof Advisor){
            editNote.setVisible(true);
        }
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
                    try {
                        App.setRoot("coursepage");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
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
        }
    }

    @FXML
    void onScheduleClicked(ActionEvent event) throws IOException{

    }

    @FXML
    void onSemesterPlanClicked(ActionEvent event) throws IOException{
        App.setRoot("semesterplan");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        application = Application.getInstance();
        if (application.getUser() instanceof Student){
            user = (Student) application.getUser();
        }else {
            user = application.getStudentUser();
        }
        label_title.setText(user.getFirstName() + "'s Profile");
        
        //user info
        user_name.setText("Name: " + user.getFullName());
        user_email.setText("Email: " + user.getEmail());
        user_major.setText("Major: " + user.getRealMajorName(user.getMajorName()));
        user_gpa.setText("GPA: "); //no get gpa method atm
        user_majorgpa.setText("Major GPA: "); //no get major gpa method atm
        user_class.setText("Class Level: " + user.getClassification());
        user_advisor.setText("Advisor: " + user.getRealAdvisorName(user.getAdvisorID())); //this needs to get the advisor's name, not ID

        user_notes_box.setVisible(false);
        editNote.setVisible(false);
        note_text.setVisible(false);
    }
}