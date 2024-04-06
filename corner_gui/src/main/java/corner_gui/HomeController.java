import java.io.IOException;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import model.*;

public class HomeController implements Initializable{
    @FXML private Button loginAdvisor;
    @FXML private Button loginStudent;
    @FXML private TextField password_text;
    @FXML private TextField username_text;
    @FXML private Label error_label;


    @FXML
    void onLoginAdvisorClicked(ActionEvent event) throws IOException{
        String username = username_text.getText();
        String password = password_text.getText();

        Application app = Application.getInstance();

        if(!app.login(2, username, password)){
            error_label.setText("Incorrect login. Try again.");
            return;
        } //need to remove the i since there are two different login buttons now
        App.setRoot("advisor_home"); //need to figure this out
    }

    @FXML
    void onLoginStudentClicked(ActionEvent event) {
        String username = username_text.getText();
        String password = password_text.getText();

        Application app = Application.getInstance();

        if(!app.login(1, username, password)){
            error_label.setText("Incorrect login. Try again.");
            return;
        }//again, need to remove i
        App.setRoot("student_home"); //need to figure this out
    }

}
