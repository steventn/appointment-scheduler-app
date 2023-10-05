package appointmentscheduler.controller;

import appointmentscheduler.dao.AppointmentDao;
import appointmentscheduler.model.Appointments;
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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {
    @FXML
    private Label appointmentIDLabel;

    @FXML
    private Label appointmentTitleLabel;

    @FXML
    private Label appointmentDescriptionLabel;

    @FXML
    private Label appointmentLocationLabel;

    @FXML
    private Label firstLevelDivisionLabel;

    @FXML
    private Label countryLabel;

    @FXML
    private TextField appointmentIDField;

    @FXML
    private TextField appointmentTitleField;

    @FXML
    private TextField appointmentDescriptionField;

    @FXML
    private TextField appointmentLocationField;

    @FXML
    private TextField contactField;

    @FXML
    private TextField typeField;

    @FXML
    private Button saveButton;

    @FXML
    private Label titleLabel;

    @FXML
    private Label customerLabel;

    @FXML
    private Label customerPhoneNumberLabel;

    @FXML
    private TextField customerPhoneNumberField;

    @FXML
    private Label startDateLabel;

    @FXML
    private Label startTimeLabel;

    @FXML
    private Label endDateLabel;

    @FXML
    private Label endTimeLabel;

    @FXML
    private TextField startDateField;

    @FXML
    private TextField startTimeField;

    @FXML
    private TextField endDateField;

    @FXML
    private TextField endTimeField;

    @FXML
    private Label customerLabel1;

    @FXML
    private TextField customerField;

    @FXML
    private Label userLabel;

    @FXML
    private TextField userField;

    public void initializeForm(Appointments appointment) {
        if (appointment != null) {
            appointmentIDField.setText(String.valueOf(appointment.getAppointmentId()));
            appointmentTitleField.setText(appointment.getTitle());
            appointmentDescriptionField.setText(appointment.getDescription());
            appointmentLocationField.setText(appointment.getLocation());
            contactField.setText(String.valueOf(appointment.getContactId()));
            typeField.setText(appointment.getType());
//            startDateField.setText(appointment.getStartDate());
//            startTimeField.setText(appointment.getStartTime());
//            endDateField.setText(appointment.getEndDate());
//            endTimeField.setText(appointment.getEndTime());
            customerField.setText(String.valueOf(appointment.getCustomerId()));
            userField.setText(String.valueOf(appointment.getUserId()));
        } else {
            appointmentIDField.setText("Auto Gen - Disabled");
            appointmentTitleField.clear();
            appointmentDescriptionField.clear();
            appointmentLocationField.clear();
            contactField.clear();
            typeField.clear();
            startDateField.clear();
            startTimeField.clear();
            endDateField.clear();
            endTimeField.clear();
            customerField.clear();
            userField.clear();
        }
    }

    @FXML
    public void addAppointment(ActionEvent event) throws IOException {
        int appointmentId = Integer.parseInt(appointmentIDField.getText());
        int customerId = Integer.parseInt(customerField.getText());
        int userId = Integer.parseInt(userField.getText());
        int contactId = Integer.parseInt(contactField.getText());
        String title = appointmentTitleField.getText();
        String description = appointmentDescriptionField.getText();
        String location = appointmentLocationField.getText();
        String type = typeField.getText();
        LocalDateTime start = LocalDateTime.parse(startDateField.getText());
        startTimeField.getText();
        LocalDateTime end = LocalDateTime.parse(endDateField.getText());
        endTimeField.getText();

        Appointments newAppointment = new Appointments(appointmentId, customerId, userId, contactId, title, description, location, type, start, end);

        AppointmentDao appointmentDao = new AppointmentDao();
        try {
            appointmentDao.addAppointment(newAppointment);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }
    @FXML
    public void exitToMainViewAction(ActionEvent event) throws IOException {
        Pane mainScreen = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        Scene scene = new Scene(mainScreen);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
