package appointmentscheduler.controller;

import appointmentscheduler.dao.UserDao;
import appointmentscheduler.dao.DAO;
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
    private static DAO<UserDao> userDao;

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
        try {
            Locale userLocale = Locale.getDefault();
            this.messages = ResourceBundle.getBundle("error_message", userLocale);

            String username_field = usernameField.getText();
            String password_field = passwordField.getText();
            if (username_field == "" | password_field == "") {
                displayAlert(2, this.messages);
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
                displayAlert(1, this.messages);
            } else if (loginResult == -2) {
                displayAlert(3, this.messages);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayAlert(int alertType, ResourceBundle error_message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        String titleKey = "alert.loginError.title";
        String headerTextKey = "";
        String contentTextKey = "";

        switch (alertType) {
            case 1 -> {
                headerTextKey = "alert.loginError.invalidCredentials";
                contentTextKey = "alert.loginError.invalidCredentialsContext";
            }
            case 2 -> {
                headerTextKey = "alert.loginError.emptyFields";
                contentTextKey = "alert.loginError.emptyFieldsContext";
            }
            case 3 -> {
                headerTextKey = "alert.loginError.usernameNotFound";
                contentTextKey = "alert.loginError.usernameNotFoundContext";
            }
        }

        alert.setTitle(error_message.getString(titleKey));
        alert.setHeaderText(error_message.getString(headerTextKey));
        alert.setContentText(error_message.getString(contentTextKey));

        alert.showAndWait();
    }

    @FXML
    private void exitApplication(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle messages) {
        {
            try {
                Locale userlocale = Locale.getDefault();
                Locale.setDefault(userlocale);
                this.messages = ResourceBundle.getBundle("login", userlocale);
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
}
