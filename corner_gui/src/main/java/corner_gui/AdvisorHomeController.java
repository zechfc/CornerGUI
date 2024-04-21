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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.*;

public class AdvisorHomeController implements Initializable{
    @FXML private Label label_title;
    @FXML private Button logoutButton;
    @FXML private Button returnButton;
    @FXML private Label user_email;
    @FXML private TextField course_search_text;
    @FXML private TextField student_search_text;
    @FXML private Button closeCourseBox;
    @FXML private DialogPane course_box;
    @FXML private Button addStudentButton;
    @FXML private TextField studentIDField;
    @FXML private Label advisorIDLabel;
    @FXML private Button closeAddStudentButton;
    @FXML private Label listOfStudents;
    @FXML private ImageView advisor_image;
    @FXML private Label label_error;
    @FXML private AnchorPane listBox;

    private Application application;
    private Advisor user;
    private String fxml = "advisorhome";
    private Student studentUser;
    private Course course;
    public int num;

    @FXML
    void onLogoutClicked(ActionEvent event) throws IOException{
        App.setRoot("home");
    }

    @FXML
    void onReturnClicked(ActionEvent event) throws IOException{
        App.setRoot(fxml);
    }

    @FXML
    void onCourseSearchClicked(ActionEvent event) throws IOException{
        String text = course_search_text.getText();
        System.out.println(course);

        course_search_text.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e){
                if(e.getCode().equals(KeyCode.ENTER)){
                    if(!application.getClass(text)){
                        return;
                    }
                    course = application.getCourse();
                    System.out.println(course);
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
    void onStudentSearchClicked(ActionEvent event) throws IOException{
        String text = student_search_text.getText();

        student_search_text.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e){
                System.out.println(text);

                if(e.getCode().equals(KeyCode.ENTER)){

                    //dont know why the below breaks its when uncommented
                    // if((application.getStudent(text).equals(null) || application.getStudentName(text).equals(null))){
                    //     return;
                    // }
                    if(text.contains("email")){
                        studentUser = Application.getInstance().getStudent(text);
                    }
                    else{
                        studentUser = Application.getInstance().getStudentName(text);
                    }
                    Application.getInstance().setStudentUser(studentUser);
                    Application.getInstance().setAdvisorUser(user);
                    try {
                        App.setRoot("studenthome");
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    @FXML
    void onCloseCourseBox(ActionEvent event) throws IOException {
        course_box.setVisible(false);
        closeCourseBox.setVisible(false);
    }

    // This has been deprecated and added to StudentHomeController
    // @FXML
    // void onAddStudentClicked(ActionEvent event) throws IOException{
    //     addStudentBox.setVisible(true);
    //     studentIDField.setVisible(true);
    //     listOfStudents.setVisible(true);
    //     closeAddStudentButton.setVisible(true);
    //     String list = "";
    //     for(Student student : students){
    //         list += student.getFullName() + "Emails's: " + student.getEmail() + "\n";
    //     }
    //     listOfStudents.setText(list);
    //     advisorIDLabel.setText("Your ID: " + user.getUserID());

    //     studentIDField.setOnKeyPressed(new EventHandler<KeyEvent>() {
    //         public void handle(KeyEvent e){
    //             if(e.getCode().equals(KeyCode.ENTER)){
    //                 String studentID = studentIDField.getText();
    //                 String advisorID = application.getUser().getUserID();
    //                 if(studentID != null && advisorID != null){
    //                     if(!application.addStudentList(user.getUserID(), studentID)){
    //                         return;
    //                     }
    //                     addStudentBox.setVisible(false);
    //                     studentIDField.setVisible(false);
    //                     closeAddStudentButton.setVisible(false);
    //                     listOfStudents.setVisible(false);
    //                 }else {
    //                     label_error.setVisible(true);
    //                     return;
    //                 }
    //             }
    //         }
    //     });
    // }

    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        application = Application.getInstance();
        user = (Advisor) application.getUser();

        label_title.setText(user.getFirstName() + "'s Profile");
        user_email.setText("Email: " + user.getEmail());
        course_box.setVisible(false);
        closeCourseBox.setVisible(false);

        //Advisor Image
        System.out.println(user.getUserImage());
        System.out.println(user.getUserID());
        System.out.println(user.getFullName());
        Image image = new Image(getClass().getResourceAsStream("/image/" + user.getUserImage()));

        advisor_image.setImage(image);
        addSupervisees();
    }


    public void addSupervisees(){
        listBox.getChildren().clear();
        ArrayList<String> supervisees = user.getStudentList();
        for(int i = 0; i < supervisees.size(); i++){
            VBox vbox = new VBox();
            HBox hbox = new HBox();
            String studentID = supervisees.get(i);
            Student superviseeStudent = user.getStudent(studentID);
            Hyperlink studentLink = new Hyperlink(superviseeStudent.getFullName());
            studentLink.setStyle("-fx-font-size: 15pt; ");
            studentLink.getStyleClass().add("studentLink");
            
            studentLink.setOnAction(e -> {
                Application.getInstance().setStudentUser(superviseeStudent);
                try{
                    App.setRoot("studenthome");
                } catch(IOException e1){
                    e1.printStackTrace();
                }
            });
            hbox.getChildren().add(studentLink);
            vbox.getChildren().add(hbox);
            listBox.getChildren().add(vbox);
        }
    }

}