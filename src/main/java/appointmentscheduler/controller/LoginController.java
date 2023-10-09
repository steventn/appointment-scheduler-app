package appointmentscheduler.controller;

import appointmentscheduler.dao.UserDao;
import appointmentscheduler.helper.ErrorUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import appointmentscheduler.model.Users;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button exitButton;

    @FXML
    private Label titleField;

    @FXML
    private Label usernameFieldLabel;

    @FXML
    private Label passwordFieldLabel;

    @FXML
    private Button signInButton;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label locationLabel;

    @FXML
    private Label locationField;

    private ResourceBundle messages;

    private Users userModel;

    @FXML
    private void loginStatus(ActionEvent event) {
        ErrorUtil errorUtil = new ErrorUtil();
        try {
            String username_field = usernameField.getText();
            String password_field = passwordField.getText();
            if (username_field.isEmpty() || password_field.isEmpty()) {
                errorUtil.displayAlert("alert.loginError.title", "alert.loginError.emptyFields", "alert.loginError.emptyFieldsContext");
                return;
            }
            int loginResult = UserDao.validateUser(username_field, password_field);
            switch (loginResult) {
                case -1:
                    errorUtil.displayAlert("alert.loginError.title", "alert.loginError.invalidCredentials", "alert.loginError.invalidCredentialsContext");
                    break;
                case -2:
                    errorUtil.displayAlert("alert.loginError.title", "alert.loginError.usernameNotFound", "alert.loginError.usernameNotFoundContext");
                    break;
                default:
                    navigateToMainView(event);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void navigateToMainView(ActionEvent event) throws Exception {
        Pane mainView = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        Scene scene = new Scene(mainView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void exitApplication(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle messages) {
        try {
            Locale userLocale = Locale.getDefault();
            Locale.setDefault(userLocale);
            this.messages = ResourceBundle.getBundle("login", userLocale);
            ZoneId zoneId = ZoneId.systemDefault();
            String userTimeZone = zoneId.toString();

            titleField.setText(this.messages.getString("login.label.title"));
            usernameFieldLabel.setText(this.messages.getString("login.label.username"));
            passwordFieldLabel.setText(this.messages.getString("login.label.password"));
            signInButton.setText(this.messages.getString("login.label.signIn"));
            exitButton.setText(this.messages.getString("login.label.exit"));
            locationLabel.setText(this.messages.getString("login.label.location"));
            locationField.setText(userTimeZone);

        } catch (MissingResourceException e) {
            e.printStackTrace();
        }
    }
}
