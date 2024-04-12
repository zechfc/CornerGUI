package corner_gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.*;

public class CoursePageController implements Initializable {
    @FXML private Label courseCode;
    @FXML private Label courseDescription;
    @FXML private Label courseName;
    @FXML private Button logoutButton;
    @FXML private Button returnButton;
    @FXML private TextField course_search_text;
    private Application application;
    private Course course;
    private String fxml;


    @FXML
    void onLogoutButtonClicked(ActionEvent event) throws IOException{
        App.setRoot("home");
    }

    @FXML
    void onReturnButtonClicked(ActionEvent event) throws IOException{
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        application = Application.getInstance();
        course = application.getCourse();

        courseName.setText("Course Name: " + course.getCourseName());
        courseCode.setText("Course Code: " + course.getCourseKey());
        courseDescription.setText("Course Description: " + course.getDescription());
        courseDescription.setWrapText(true);
    }
}
