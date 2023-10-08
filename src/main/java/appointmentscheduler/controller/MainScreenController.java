package appointmentscheduler.controller;

import appointmentscheduler.dao.AppointmentDao;
import appointmentscheduler.dao.CustomerDao;
import appointmentscheduler.model.Appointments;
import appointmentscheduler.model.Customers;
import static appointmentscheduler.helper.DBConnection.connection;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
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
    private Button deleteCustomerButton;

    @FXML
    private Button modifyCustomerButton;

    @FXML
    private Button addCustomerButton;

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
    private Button deleteAppointmentButton;

    @FXML
    private Button modifyAppointmentButton;

    @FXML
    private Button addAppointmentButton;

    @FXML
    private Button exitButton;

    private ObservableList<Customers> customersList = FXCollections.observableArrayList();
    private ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    @FXML
    public void openAppointmentForm(ActionEvent event) throws IOException {
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

    @FXML
    private void exitApplication(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
