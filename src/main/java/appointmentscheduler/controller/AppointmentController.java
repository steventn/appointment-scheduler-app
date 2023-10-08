package appointmentscheduler.controller;

import appointmentscheduler.dao.AppointmentDao;
import appointmentscheduler.dao.ContactDao;
import appointmentscheduler.dao.CustomerDao;
import appointmentscheduler.dao.UserDao;
import appointmentscheduler.helper.TimeUtil;
import appointmentscheduler.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
    private ComboBox<String> contactField;

    @FXML
    private TextField typeField;

    @FXML
    private Button saveButton;

    @FXML
    private Label titleLabel;

    @FXML
    private Label appointmentLabel;

    @FXML
    private Label startDateLabel;

    @FXML
    private Label startTimeLabel;

    @FXML
    private Label endDateLabel;

    @FXML
    private Label endTimeLabel;

    @FXML
    private DatePicker startDateField;

    @FXML
    private ComboBox<String> startTimeField;

    @FXML
    private DatePicker endDateField;

    @FXML
    private ComboBox<String> endTimeField;

    @FXML
    private Label customerLabel1;

    @FXML
    private ComboBox<Integer> customerIdField;

    @FXML
    private Label userLabel;

    @FXML
    private ComboBox<Integer> userIdField;

    ObservableList<Appointments> allAppointments = null;
    int latestAppointmentId = 0;


    public void initializeForm(Appointments appointment) throws SQLException {
        ContactDao contactDao = new ContactDao();
        AppointmentDao appointmentDao = new AppointmentDao();
        latestAppointmentId = appointmentDao.getLatestAppointmentId() + 1;

        if (appointment != null) {
            appointmentIDField.setText(String.valueOf(appointment.getAppointmentId()));
            appointmentTitleField.setText(appointment.getTitle());
            appointmentDescriptionField.setText(appointment.getDescription());
            appointmentLocationField.setText(appointment.getLocation());
            customerIdField.setValue(appointment.getCustomerId());
            userIdField.setValue(appointment.getUserId());
            contactField.setValue(contactDao.getContact(appointment.getContactId()).getContactName());
            typeField.setText(appointment.getType());
            startDateField.setValue(appointment.getStartDate());
            startTimeField.setValue(String.valueOf(appointment.getStartTime()));
            endDateField.setValue(appointment.getEndDate());
            endTimeField.setValue(String.valueOf(appointment.getEndTime()));
        } else {
            appointmentIDField.setText(String.valueOf(latestAppointmentId));
            appointmentTitleField.clear();
            appointmentDescriptionField.clear();
            appointmentLocationField.clear();
            typeField.clear();
        }
    }

    @FXML
    public void addAppointment(ActionEvent event) throws IOException {
        int appointmentId = Integer.parseInt(appointmentIDField.getText());
        int customerId = customerIdField.getValue();
        int userId = userIdField.getValue();
        int contactId = 0;
        String title = appointmentTitleField.getText();
        String description = appointmentDescriptionField.getText();
        String location = appointmentLocationField.getText();
        String type = typeField.getText();
        LocalDateTime start = startDateField.getValue().atStartOfDay();
        startTimeField.getValue();
        LocalDateTime end = endDateField.getValue().atStartOfDay();
        endTimeField.getValue();

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
        ContactDao contactDao = new ContactDao();
        AppointmentDao appointmentDao = new AppointmentDao();
        UserDao userDao = new UserDao();
        CustomerDao customerDao = new CustomerDao();

        try {
            allAppointments = appointmentDao.getAllAppointments();
            ObservableList<Contacts> allContacts = contactDao.getAllContacts();
            ObservableList<String> allContactNames = allContacts.stream()
                    .map(Contacts::getContactName)
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));

            ObservableList<Customers> allCustomers = customerDao.getAllCustomers();
            ObservableList<Integer> allCustomerIds = allCustomers.stream()
                    .map(Customers::getCustomerId)
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));

            ObservableList<Users> allUsers = userDao.getAllUsers();
            ObservableList<Integer> allUserId = allUsers.stream()
                    .map(Users::getUserId)
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));

            ObservableList<String> businessHours = TimeUtil.generateBusinessHours();

            contactField.setItems(allContactNames);
            customerIdField.setItems(allCustomerIds);
            userIdField.setItems(allUserId);
            startTimeField.setItems(businessHours);
            endTimeField.setItems(businessHours);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
