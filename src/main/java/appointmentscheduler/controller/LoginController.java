package appointmentscheduler.controller;

import appointmentscheduler.dao.AppointmentDao;
import appointmentscheduler.dao.UserDao;
import appointmentscheduler.helper.AlertUtil;
import appointmentscheduler.model.Appointments;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * The LoginController class controls the logic for the login form.
 */
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

    AlertUtil alertUtil = new AlertUtil();

    /**
     * Handles the login process when the sign in button is clicked.
     *
     * @param event The event that triggered this method.
     */
    @FXML
    private void loginStatus(ActionEvent event) {
        AlertUtil alertUtil = new AlertUtil();
        try {
            String username_field = usernameField.getText();
            String password_field = passwordField.getText();
            if (username_field.isEmpty() || password_field.isEmpty()) {
                alertUtil.displayErrorAlert("alert.loginError.title", "alert.loginError.emptyFields", "alert.loginError.emptyFieldsContext");
                logActivity(username_field, false);
                return;
            }
            int loginResult = UserDao.validateUser(username_field, password_field);
            switch (loginResult) {
                case -1:
                    alertUtil.displayErrorAlert("alert.loginError.title", "alert.loginError.invalidCredentials", "alert.loginError.invalidCredentialsContext");
                    logActivity(username_field, false);
                    break;
                case -2:
                    alertUtil.displayErrorAlert("alert.loginError.title", "alert.loginError.usernameNotFound", "alert.loginError.usernameNotFoundContext");
                    logActivity(username_field, false);
                    break;
                default:
                    navigateToMainView(event);
                    checkForUpcomingAppointment();
                    logActivity(username_field, true);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks for any upcoming appointments and displays an alert if any are found.
     *
     * @throws SQLException If a database error occurs.
     */
    public void checkForUpcomingAppointment() throws SQLException {
        AppointmentDao appointmentDao = new AppointmentDao();
        ObservableList<Appointments> allAppointments = appointmentDao.getAllAppointments();

        for (Appointments appointment : allAppointments) {
            LocalDateTime appointmentTime = appointment.getStart();
            ZonedDateTime localAppointmentTime = appointmentTime.atZone(ZoneId.systemDefault());
            ZonedDateTime localTime = ZonedDateTime.now();
            Duration duration = Duration.between(localAppointmentTime, localTime).abs();

            if (duration.compareTo(Duration.of(15, ChronoUnit.MINUTES)) <= 0) {
                alertUtil.displaySuccessAlert("alert.confirmation.confirmation", "alert.appointmentSuccess.upcomingAppointment", "alert.appointmentSuccess.upcomingAppointment");
            }
        }
        alertUtil.displaySuccessAlert("alert.confirmation.confirmation", "alert.appointmentSuccess.noUpcomingAppointment", "alert.appointmentSuccess.noUpcomingAppointment");
    }

    /**
     * Navigates to the main view screen.
     *
     * @param event The event that triggered this method.
     * @throws Exception If an error occurs during the screen transition.
     */
    private void navigateToMainView(ActionEvent event) throws Exception {
        Pane mainView = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        Scene scene = new Scene(mainView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     * Exits the application when the exit button is clicked.
     *
     * @param event The event that triggered this method.
     */
    @FXML
    private void exitApplication(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void logActivity(String username, boolean success) {
        String logEntry = LocalDateTime.now() + "\n - Username: " + username + " - Success: " + success + "\n\n";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("login_activity.txt", true))) {
            writer.write(logEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
