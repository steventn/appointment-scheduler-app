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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AppointmentController implements Initializable {
    @FXML
    private TextField appointmentIDField;

    @FXML
    private TextField appointmentTitleField;

    @FXML
    private TextField appointmentDescriptionField;

    @FXML
    private TextField appointmentLocationField;

    @FXML
    private ComboBox<Contacts> contactField;

    @FXML
    private TextField typeField;

    @FXML
    private Button saveButton;

    @FXML
    private DatePicker startDateField;

    @FXML
    private ComboBox<String> startTimeField;

    @FXML
    private DatePicker endDateField;

    @FXML
    private ComboBox<String> endTimeField;

    @FXML
    private ComboBox<Integer> customerIdField;

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
            contactField.setValue(contactDao.getContact(appointment.getContactId()));
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
        int contactId = contactField.getValue().getContactId();
        String title = appointmentTitleField.getText();
        String description = appointmentDescriptionField.getText();
        String location = appointmentLocationField.getText();
        String type = typeField.getText();
        LocalDate start = startDateField.getValue();
        LocalTime startTime = LocalTime.parse(startTimeField.getValue());
        LocalDate end = endDateField.getValue();
        LocalTime endTime = LocalTime.parse(endTimeField.getValue());
        LocalDateTime finalStart = start.atTime(startTime);
        LocalDateTime finalEnd = end.atTime(endTime);

        Appointments newAppointment = new Appointments(appointmentId, customerId, userId, contactId, title, description, location, type, finalStart, finalEnd);
        AppointmentDao appointmentDao = new AppointmentDao();

        try {
            if (appointmentId < latestAppointmentId) {
                appointmentDao.updateAppointment(newAppointment);
            } else {
                appointmentDao.addAppointment(newAppointment);
            }
            exitToMainViewAction(event);
        } catch (SQLException e) {
            e.printStackTrace();
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
//            ObservableList<String> allContactNames = allContacts.stream()
//                    .map(Contacts::getContactName)
//                    .collect(Collectors.toCollection(FXCollections::observableArrayList));

            ObservableList<Customers> allCustomers = customerDao.getAllCustomers();
            ObservableList<Integer> allCustomerIds = allCustomers.stream()
                    .map(Customers::getCustomerId)
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));

            ObservableList<Users> allUsers = userDao.getAllUsers();
            ObservableList<Integer> allUserId = allUsers.stream()
                    .map(Users::getUserId)
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));

            ObservableList<String> businessHours = TimeUtil.generateBusinessHours();

            contactField.setItems(allContacts);
            customerIdField.setItems(allCustomerIds);
            userIdField.setItems(allUserId);
            startTimeField.setItems(businessHours);
            endTimeField.setItems(businessHours);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
