package appointmentscheduler.controller;

import appointmentscheduler.model.Customers;
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
import java.util.ResourceBundle;

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
    private TextField firstLevelDivisionField;

    @FXML
    private TextField countryField;

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

    public void initializeForm(Customers customer) {
        if (customer != null) {
            customerIDField.setText(String.valueOf(customer.getCustomerId()));
            customerNameField.setText(customer.getName());
            customerAddressField.setText(customer.getAddress());
            customerPostalCodeField.setText(customer.getPostalCode());
//            firstLevelDivisionField.setText(customer.getFirstLevelDivision()); // You may need to adjust this line
//            countryField.setText(customer.getCountry()); // You may need to adjust this line
            // Set other fields as needed
        } else {
            customerIDField.clear();
            customerNameField.clear();
            customerAddressField.clear();
            customerPostalCodeField.clear();
            firstLevelDivisionField.clear();
            countryField.clear();
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
