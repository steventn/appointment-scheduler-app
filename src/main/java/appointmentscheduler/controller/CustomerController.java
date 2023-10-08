package appointmentscheduler.controller;

import appointmentscheduler.dao.AppointmentDao;
import appointmentscheduler.dao.CountryDao;
import appointmentscheduler.dao.CustomerDao;
import appointmentscheduler.dao.FirstLevelDivisionDao;
import appointmentscheduler.model.Appointments;
import appointmentscheduler.model.Countries;
import appointmentscheduler.model.Customers;
import appointmentscheduler.model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CustomerController implements Initializable {
    @FXML
    private Label customerIDLabel;

    @FXML
    private Label customerNameLabel;

    @FXML
    private Label customerAddressLabel;

    @FXML
    private Label customerPostalCodeLabel;

    @FXML
    private Label firstLevelDivisionLabel;

    @FXML
    private Label countryLabel;

    @FXML
    private TextField customerIDField;

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField customerAddressField;

    @FXML
    private TextField customerPostalCodeField;

    @FXML
    private ComboBox<String> firstLevelDivisionField;

    @FXML
    private ComboBox<String> countryField;

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

    ObservableList<FirstLevelDivisions> allFirstLevelDivisions;
    int latestCustomerId = 0;

    public void initializeForm(Customers customer) throws SQLException {
        CustomerDao customerDao = new CustomerDao();
        latestCustomerId = customerDao.getLatestCustomerId() + 1;

        if (customer != null) {
            customerIDField.setText(String.valueOf(customer.getCustomerId()));
            customerNameField.setText(customer.getName());
            customerAddressField.setText(customer.getAddress());
            customerPostalCodeField.setText(customer.getPostalCode());
            countryField.setValue(customer.getCountry());
            firstLevelDivisionField.setValue(customer.getDivision());
            customerPhoneNumberField.setText(customer.getPhone());
        } else {
            customerIDField.setText(String.valueOf(latestCustomerId));
            customerNameField.clear();
            customerAddressField.clear();
            customerPostalCodeField.clear();
            customerPhoneNumberField.clear();
        }
    }

    @FXML
    public void addCustomer(ActionEvent event) throws Exception {
        String divisionName = firstLevelDivisionField.getValue();
        int divisionId = 0;

        FirstLevelDivisions selectedDivision = allFirstLevelDivisions.stream()
                .filter(division -> division.getDivision().equals(divisionName))
                .findFirst()
                .orElse(null);

        if (selectedDivision != null) {
            divisionId = selectedDivision.getDivisionId();
        }

        int customerId = Integer.parseInt(customerIDField.getText());
        String country = countryField.getValue();
        String name = customerNameField.getText();
        String address = customerAddressField.getText();
        String postalCode = customerPostalCodeField.getText();
        String phone = customerPhoneNumberField.getText();
        String createdBy = "admin";
        String lastUpdatedBy = "admin";
        Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
        Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());

        Customers newCustomer = new Customers(customerId, divisionId, country, name, address, postalCode, phone, createdBy, lastUpdatedBy,  createDate, lastUpdate);
        CustomerDao customerDao = new CustomerDao();

        try {
            if (customerId < latestCustomerId) {
                customerDao.updateCustomer(newCustomer);
            } else {
                customerDao.addCustomer(newCustomer);
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
        try {
            CountryDao countriesDao = new CountryDao();
            FirstLevelDivisionDao firstLevelDivisionDao = new FirstLevelDivisionDao();
            ObservableList<Countries> allCountries = countriesDao.getAllCountries();
            ObservableList<String> allCountryNames = allCountries.stream()
                    .map(Countries::getCountry)
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
            allFirstLevelDivisions = firstLevelDivisionDao.getAllFirstLevelDivisions();
            ObservableList<String> allFirstLevelDivisionNames = allFirstLevelDivisions.stream()
                    .map(FirstLevelDivisions::getDivision)
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
            countryField.setItems(allCountryNames);
            firstLevelDivisionField.setItems(allFirstLevelDivisionNames);

            // Event listener created to dynamically assign First Level Divisions with selected Country
            countryField.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    try {
                        ObservableList<FirstLevelDivisions> divisionsForCountry = firstLevelDivisionDao.getFirstLevelDivisionByCountry(newValue);
                        ObservableList<String> divisionsPerCountryNames = divisionsForCountry.stream()
                                .map(FirstLevelDivisions::getDivision)
                                .collect(Collectors.toCollection(FXCollections::observableArrayList));
                        firstLevelDivisionField.getItems().setAll(divisionsPerCountryNames);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
