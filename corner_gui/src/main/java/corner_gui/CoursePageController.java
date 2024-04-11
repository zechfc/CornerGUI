package corner_gui;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.*;

public class CoursePageController implements Initializable {
    @FXML private Label courseCode;
    @FXML private Label courseDescription;
    @FXML private Label courseName;
    @FXML private Button logoutButton;
    @FXML private Button returnButton;
    private Application application;
    private User user;
    private CourseList courseList;
    private ArrayList<Course> courses;
    private String fxml;


    @FXML
    void onLogoutButtonClicked(ActionEvent event) throws IOException{
        App.setRoot("home");
    }

    @FXML
    void onReturnButtonClicked(ActionEvent event) throws IOException{
        if(user instanceof Student){
            fxml = "studentpage";
            App.setRoot(fxml);
        }else if (user instanceof Advisor){
            fxml = "advisorpage";
            App.setRoot(fxml);
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        courseList = CourseList.getInstance();
        application = Application.getInstance();
        user = application.getUser();

        


    }

}
