package corner_gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.*;

public class AdvisorHomeController implements Initializable{
    @FXML private MenuItem email_student;
    @FXML private Label label_title;
    @FXML private Button logoutButton;
    @FXML private VBox prim_info;
    @FXML private Button returnButton;
    @FXML private MenuItem schedule;
    @FXML private MenuButton search_menu;
    @FXML private MenuButton studentMenu;
    @FXML private MenuItem studentSearch1;
    @FXML private MenuItem studentSearch2;
    @FXML private MenuItem studentSearch3;
    @FXML private MenuItem studentSearch4;
    @FXML private MenuItem studentSearch5;
    @FXML private VBox tert_info;
    @FXML private Label user_email;
    @FXML private Label user_name;
    @FXML private TextField course_search_text;
    @FXML private Button closeCourseBox;
    @FXML private DialogPane course_box;
    
    @FXML private Button addStudentButton;
    @FXML private TextField advisorIDField;
    @FXML private TextField studentIDField;
    @FXML private Label advisorIDLabel;
    @FXML private Button closeAddStudentButton;
    @FXML private Label listOfStudents;
    @FXML private ImageView advisor_image;
 

    private Application application;
    private Advisor user;
    private String fxml = "advisorhome";
    private Student studentUser;
    private Course course;

    @FXML
    void onLogoutClicked(ActionEvent event) throws IOException{
        App.setRoot("home");
    }

    @FXML
    void onReturnClicked(ActionEvent event) throws IOException{
        App.setRoot(fxml);
    }

    @FXML
    void onStudent1Clicked(ActionEvent event) throws IOException{
        studentUser = Application.getInstance().getStudent("jlDoe@email.sc.edu");
        Application.getInstance().setStudentUser(studentUser);
        App.setRoot("studenthome");
    }

    @FXML
    void onStudent2Clicked(ActionEvent event) throws IOException{
        studentUser = Application.getInstance().getStudent("masue@email.sc.edu");
        Application.getInstance().setStudentUser(studentUser);
        App.setRoot("studenthome");
    }

    @FXML
    void onStudent3Clicked(ActionEvent event) throws IOException {
        studentUser = Application.getInstance().getStudent("tdjohnson@email.sc.edu");
        Application.getInstance().setStudentUser(studentUser);
        App.setRoot("studenthome");
    }

    @FXML
    void onStudent4Clicked(ActionEvent event) throws IOException{
        studentUser = Application.getInstance().getStudent("westb@email.sc.edu");
        Application.getInstance().setStudentUser(studentUser);
        App.setRoot("studenthome");
    }

    @FXML
    void onStudent5Clicked(ActionEvent event) throws IOException{
        studentUser = Application.getInstance().getStudent("hillt@email.sc.edu");
        Application.getInstance().setStudentUser(studentUser);
        App.setRoot("studenthome");
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
    void onCloseCourseBox(ActionEvent event) throws IOException {
        course_box.setVisible(false);
        closeCourseBox.setVisible(false);
    }

    @FXML
    void onAddStudentClicked(ActionEvent event) throws IOException{
        // listOfStudents.setText();
    }

    @FXML
    void onCloseAddStudent(ActionEvent event) throws IOException{
        studentIDField.setVisible(false);
        advisorIDField.setVisible(false);
        advisorIDLabel.setVisible(false);
        listOfStudents.setVisible(false);
        closeAddStudentButton.setVisible(false);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        application = Application.getInstance();
        user = (Advisor) application.getUser();

        label_title.setText(user.getFirstName() + "'s Profile");

        user_name.setText("Name: " + user.getFirstName() + " " + user.getLastName());
        user_email.setText("Email: " + user.getEmail());

        course_box.setVisible(false);
        closeCourseBox.setVisible(false);

        studentIDField.setVisible(false);
        advisorIDField.setVisible(false);
        advisorIDLabel.setVisible(false);
        listOfStudents.setVisible(false);
        closeAddStudentButton.setVisible(false);

        Image image = new Image(getClass().getResourceAsStream("/images/" + user.getUserImage()));
        ImageView advisor_image = new ImageView(image);
        advisor_image.setImage(image);
    }

}