package appointmentscheduler.controller;

import appointmentscheduler.dao.UserDao;
import appointmentscheduler.dao.DAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import appointmentscheduler.model.Users;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class LoginController {
    private static DAO<UserDao> userDao;

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
            if (username_field == "" | password_field == "") {
                displayAlert(2);
                return;
            }
            int loginResult = UserDao.validateUser(username_field, password_field);
            if (loginResult > 0) {
                Pane mainView = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
                Scene scene = new Scene(mainView);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            } else if (loginResult == -1) {
                displayAlert(1);
            } else if (loginResult == -2) {
                displayAlert(3);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayAlert(int alertType) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alertType) {
            case 1:
                alert.setTitle("Login Error");
                alert.setHeaderText("Invalid Username or Password");
                alert.setContentText("Please try again.");
                break;
            case 2:
                alert.setTitle("Login Error");
                alert.setHeaderText("Empty Fields");
                alert.setContentText("Please fill in both Username and Password fields.");
                break;
            case 3:
                alert.setTitle("Login Error");
                alert.setHeaderText("Username Not Found");
                alert.setContentText("Username does not exist, please check your username.");
                break;
        }
        alert.showAndWait();
    }
}
