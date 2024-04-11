package corner_gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
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
    private Application application;
    private Advisor user;
    private String fxml = "advisorhome";
    private Student studentUser;

    @FXML
    void onEmailStudentClicked(ActionEvent event) throws IOException{

    }

    @FXML
    void onLogoutClicked(ActionEvent event) throws IOException{
        App.setRoot("home");
    }

    @FXML
    void onReturnClicked(ActionEvent event) throws IOException{
        App.setRoot(fxml);
    }

    @FXML
    void onScheduleClicked(ActionEvent event) throws IOException {

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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        application = Application.getInstance();
        user = (Advisor) application.getUser();

        label_title.setText(user.getFirstName() + "'s Profile");

        user_name.setText("Name: " + user.getFirstName() + " " + user.getLastName());
        user_email.setText("Email: " + user.getEmail());
    }

}