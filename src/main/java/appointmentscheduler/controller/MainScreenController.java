package appointmentscheduler.controller;

import appointmentscheduler.dao.AppointmentDao;
import appointmentscheduler.dao.CustomerDao;
import appointmentscheduler.helper.AlertUtil;
import appointmentscheduler.model.Appointments;
import appointmentscheduler.model.Customers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * The MainScreenController class controls the logic for the main screen.
 */
public class MainScreenController implements Initializable {
    @FXML
    private RadioButton allAppointmentsFilter;

    @FXML
    private TableView<Customers> customersTableView;

    @FXML
    private TableColumn<Customers, Integer> IDColumn;

    @FXML
    private TableColumn<Customers, String> nameColumn;

    @FXML
    private TableColumn<Customers, String> addressColumn;

    @FXML
    private TableColumn<Customers, String> phoneNumberColumn;

    @FXML
    private TableColumn<Customers, String> stateProvinceColumn;

    @FXML
    private TableColumn<Customers, String> postalCodeColumn;

    @FXML
    private TableColumn<Customers, String> firstLevelDivisionColumn;

    @FXML
    private TableColumn<Customers, String> countryColumn;

    @FXML
    private Button modifyCustomerButton;

    @FXML
    private TableView<Appointments> appointmentsTableView;

    @FXML
    private TableColumn<Appointments, Integer> appointmentIDColumn;

    @FXML
    private TableColumn<Appointments, String> titleColumn;

    @FXML
    private TableColumn<Appointments, String> descriptionColumn;

    @FXML
    private TableColumn<Appointments, String> locationColumn;

    @FXML
    private TableColumn<Appointments, String> contactColumn;

    @FXML
    private TableColumn<Appointments, String> typeColumn;

    @FXML
    private TableColumn<Appointments, String> startDateTimeColumn;

    @FXML
    private TableColumn<Appointments, String> endDateTimeColumn;

    @FXML
    private TableColumn<Appointments, Integer> customerIDColumn;

    @FXML
    private TableColumn<Appointments, Integer> userIDColumn;

    @FXML
    private Button modifyAppointmentButton;

    private ObservableList<Customers> customersList = FXCollections.observableArrayList();
    private ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();
    AlertUtil alertUtil = new AlertUtil();

    /**
     * Filters the appointments to display only those in the current month.
     *
     * The lambda expression in this method is used to filter appointments that occur in the current month.
     * This is done by comparing the month and year values of each appointment's start date with the current month and year.
     * The filtered appointments are then put into an observable list and displayed in the appointmentsTableView.
     */
    @FXML
    public void displayCurrentMonthAppointments() {
        LocalDate now = LocalDate.now();
        int currentMonth = now.getMonthValue();
        int currentYear = now.getYear();

        ObservableList<Appointments> appointmentsInCurrentMonth = appointmentsList.stream()
                .filter(appointment -> {
                    LocalDateTime appointmentDateTime = appointment.getStart();
                    int appointmentMonth = appointmentDateTime.getMonthValue();
                    int appointmentYear = appointmentDateTime.getYear();
                    return appointmentMonth == currentMonth && appointmentYear == currentYear;
                })
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        appointmentsTableView.setItems(appointmentsInCurrentMonth);
    }

    /**
     * Filters the appointments to display only those in the current week.
     *
     * The lambda expression in this method is used to filter appointments that occur in the current week.
     * This is done by comparing the week and year values of each appointment's start date with the current week and year.
     * The filtered appointments are then put into an observable list and displayed in the appointmentsTableView.
     */
    public void displayCurrentWeekAppointments() {
        LocalDate now = LocalDate.now();
        int currentWeek = now.get(WeekFields.of(Locale.getDefault()).weekOfYear());
        int currentYear = now.getYear();

        ObservableList<Appointments> appointmentsInCurrentWeek = appointmentsList.stream()
                .filter(appointment -> {
                    LocalDateTime appointmentDateTime = appointment.getStart();
                    int appointmentWeek = appointmentDateTime.get(WeekFields.of(Locale.getDefault()).weekOfYear());
                    int appointmentYear = appointmentDateTime.getYear();
                    return appointmentWeek == currentWeek && appointmentYear == currentYear;
                })
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        appointmentsTableView.setItems(appointmentsInCurrentWeek);
    }

    /**
     * Opens the customer form for adding or modifying a customer.
     *
     * @param event The event that triggered this method.
     * @throws IOException If an error occurs during the screen transition.
     * @throws SQLException If a database error occurs.
     */
    @FXML
    public void openCustomerForm(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CustomerForm.fxml"));
        Parent customerForm = loader.load();
        CustomerController controller = loader.getController();

        Customers selectedCustomer = customersTableView.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            controller.initializeForm(null);
        } else {
            controller.initializeForm(selectedCustomer);
        }

        Scene scene = new Scene(customerForm);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     * Opens the appointment form for adding or modifying an appointment.
     *
     * @param event The event that triggered this method.
     * @throws IOException If an error occurs during the screen transition.
     * @throws SQLException If a database error occurs.
     */
    @FXML
    public void openAppointmentForm(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AppointmentForm.fxml"));
        Parent customerForm = loader.load();
        AppointmentController controller = loader.getController();

        Appointments selectedAppointment = appointmentsTableView.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            controller.initializeForm(null);
        } else {
            controller.initializeForm(selectedAppointment);
        }

        Scene scene = new Scene(customerForm);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     * Deletes the selected appointment and refreshes the window.
     *
     * @throws SQLException If a database error occurs.
     */
    @FXML
    public void setDeleteAppointmentButton() throws SQLException {
        AppointmentDao appointmentDao = new AppointmentDao();
        Appointments selectedAppointment = appointmentsTableView.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            alertUtil.displayErrorAlert("alert.appointmentError.appointmentError","alert.appointmentError.noAppointmentSelected", "alert.loginError.invalidCredentialsContext");
        } else {
            selectedAppointment= AppointmentDao.getAppointmentByCustomerId(selectedAppointment.getCustomerId());
            String selectedAppointmentId = String.valueOf(selectedAppointment.getAppointmentId());
            String selectedAppointmentType = selectedAppointment.getType();
            String deletionMessage = String.format("\n Appointment Id: %s, Type: %s", selectedAppointmentId, selectedAppointmentType);
            appointmentDao.deleteAppointment(selectedAppointment.getAppointmentId());
            refreshWindow();
            alertUtil.displaySuccessAlert("alert.success.title", "alert.confirmation.confirmation", "alert.appointmentSuccess.deletion", deletionMessage);
        }

    }

    /**
     * Deletes the selected customer and refreshes the window.
     *
     * @throws SQLException If a database error occurs.
     */
    @FXML
    public void setDeleteCustomerButton() throws SQLException {
        CustomerDao customerDao = new CustomerDao();
        Customers selectedCustomer = customersTableView.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            alertUtil.displayErrorAlert("alert.customerError.customerError","alert.customerError.noCustomerSelected", "alert.loginError.invalidCredentialsContext");
        } else {
            Appointments activeAppointment = AppointmentDao.getAppointmentByCustomerId(selectedCustomer.getCustomerId());
            if (activeAppointment == null) {
                customerDao.deleteCustomer(selectedCustomer.getCustomerId());
                refreshWindow();
                alertUtil.displaySuccessAlert("alert.success.title", "alert.confirmation.confirmation", "alert.customerSuccess.deletion");
            } else {
                alertUtil.displayErrorAlert("alert.customerError.customerError","alert.customerError.activeAppointment", "alert.loginError.invalidCredentialsContext");
            }
        }
    }

    /**
     * Refreshes the window by reloading the appointments and customers.
     *
     * @throws SQLException If a database error occurs.
     */
    @FXML
    private void refreshWindow() throws SQLException {
        AppointmentDao appointmentDao = new AppointmentDao();
        CustomerDao customerDao = new CustomerDao();
        appointmentsList = appointmentDao.getAllAppointments();
        customersList = customerDao.getAllCustomers();
        appointmentsTableView.setItems(appointmentsList);
        customersTableView.setItems(customersList);
    }

    /**
     * Opens the report view when report button is clicked.
     *
     * @param event The event that triggered this method.
     */
    @FXML
    private void openReports(ActionEvent event) throws IOException {
        Pane mainView = FXMLLoader.load(getClass().getResource("/ReportScreen.fxml"));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allAppointmentsFilter.setSelected(true);

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        stateProvinceColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        firstLevelDivisionColumn.setCellValueFactory(new PropertyValueFactory<>("division"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        customersTableView.setItems(customersList);

        appointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        appointmentsTableView.setItems(appointmentsList);

        customersTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            modifyCustomerButton.setDisable(newSelection == null);
        });

        appointmentsTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            modifyAppointmentButton.setDisable(newSelection == null);
        });

        try {
            CustomerDao customersDAO = new CustomerDao();
            AppointmentDao appointmentDao = new AppointmentDao();
            ObservableList<Customers> allCustomers = customersDAO.getAllCustomers();
            ObservableList<Appointments> allAppointments = appointmentDao.getAllAppointments();
            customersList.addAll(allCustomers);
            appointmentsList.addAll(allAppointments);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
