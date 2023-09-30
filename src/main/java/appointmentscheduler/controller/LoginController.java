package appointmentscheduler.controller;

import appointmentscheduler.dao.UserDao;
import appointmentscheduler.dao.DAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import appointmentscheduler.model.Users;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class LoginController {
    private static DAO<UserDao> userDao;
    @FXML
    private Button loginButton;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label locationLabel;

    private Users userModel;

    @FXML
    private void loginStatus(ActionEvent event) {
        try {
            String username_field = usernameField.getText();
            String password_field = passwordField.getText();
            int loginResult = UserDao.validateUser(username_field, password_field);
            System.out.println(loginResult);
            if (loginResult != -1) {
                Pane mainView = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
                Scene scene = new Scene(mainView);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            } else {
                
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
