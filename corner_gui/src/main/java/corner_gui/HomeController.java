package corner_gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

        if(!app.login(username, password)){
            error_label.setText("Incorrect login. Try again.");
            return;
        }
        App.setRoot("advisorhome"); 
    }

    @FXML
    void onLoginStudentClicked(ActionEvent event) throws IOException {
        String username = username_text.getText();
        String password = password_text.getText();

        Application app = Application.getInstance();

        if(!app.login(username, password)){
            error_label.setText("Incorrect login. Try again.");
            return;
        }
        App.setRoot("studenthome"); 
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {}

}
