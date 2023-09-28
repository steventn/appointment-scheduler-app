package appointmentscheduler.controller;

import appointmentscheduler.dao.UserDao;
import appointmentscheduler.dao.DAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import appointmentscheduler.model.Users;


public class LoginController {
    private static DAO<UserDao> userDao;
    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label locationLabel;

    private Users userModel;

    private boolean loginStatus(String username, String password) {
        try {
            String username_field = usernameField.getText();
            String password_field = passwordField.getText();
            int loginResult = UserDao.validateUser(username_field, password_field);
            if (loginResult != -1) {

            }


            Users user = new Users(username, password);

        }

        catch (Exception e) {

        }
        return false;
    }

}
