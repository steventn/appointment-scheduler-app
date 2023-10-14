package appointmentscheduler.controller;

import appointmentscheduler.dao.CustomerDao;
import appointmentscheduler.dao.ReportDao;
import appointmentscheduler.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * The ReportController class controls the logic for the report screen.
 */
public class ReportController implements Initializable {
    @FXML
    private TableView<Appointments> reportATableView;

    @FXML
    private ComboBox<Customers> customerIdFilter;

    @FXML
    private TableColumn<ReportsB, Integer> appointmentIDColumnA;

    @FXML
    private TableColumn<ReportsB, String> titleColumnA;

    @FXML
    private TableColumn<ReportsB, String> typeColumn;

    @FXML
    private TableColumn<ReportsB, String> descriptionColumn;

    @FXML
    private TableColumn<ReportsB, String> startDateTimeColumnA;

    @FXML
    private TableColumn<ReportsB, String> endDateTimeColumnA;

    @FXML
    private TableColumn<ReportsB, Integer> customerIDColumnA;

    @FXML
    private TableView<ReportsB> reportBTableView;

    @FXML
    private TableColumn<ReportsB, String> monthColumn;

    @FXML
    private TableColumn<ReportsB, String> typeColumnB;

    @FXML
    private TableColumn<ReportsB, Integer> totalAppointmentsColumnB;

    @FXML
    private TableView<ReportsC> reportCTableView;

    @FXML
    private TableColumn<ReportsC, String> customerColumnC;

    @FXML
    private TableColumn<ReportsC, String> monthColumnC;

    @FXML
    private TableColumn<ReportsC, Integer> durationColumn;

    /**
     * Navigates to the main view screen.
     *
     * @param event The event that triggered this method.
     * @throws Exception If an error occurs during the screen transition.
     */
    @FXML
    private void navigateToMainView(ActionEvent event) throws Exception {
        Pane mainView = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        Scene scene = new Scene(mainView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     * Sets the Report A tableview based on customer selextion.
     *
     */
    @FXML
    private void setReportATableView() {
        ReportDao reportDao = new ReportDao();
        ObservableList<Appointments> reportAAppointments = FXCollections.observableArrayList();
        Customers customerSelected = customerIdFilter.getSelectionModel().getSelectedItem();

        try {
            reportAAppointments = reportDao.getAppointmentsByCustomerId(customerSelected.getCustomerId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        reportATableView.setItems(reportAAppointments);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ReportDao reportDao = new ReportDao();
        CustomerDao customerDao = new CustomerDao();
        ObservableList<ReportsB> reportBAppointments = FXCollections.observableArrayList();;
        ObservableList<ReportsC> reportCAppointments = FXCollections.observableArrayList();;
        ObservableList<Appointments> reportAAppointments = FXCollections.observableArrayList();;
        ObservableList<Customers> allCustomers;

        try {
            reportBAppointments = reportDao.getTotalAppointmentsByTypeMonth();
            reportCAppointments = reportDao.getTotalAppointmentsByDuration();
            reportAAppointments = reportDao.getTotalAppointmentsSortedByCustomer();
            allCustomers = customerDao.getAllCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        appointmentIDColumnA.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumnA.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        startDateTimeColumnA.setCellValueFactory(new PropertyValueFactory<>("start"));
        endDateTimeColumnA.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIDColumnA.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        reportATableView.setItems(reportAAppointments);
        customerIdFilter.setItems(allCustomers);

        monthColumn.setCellValueFactory(new PropertyValueFactory<>("month"));
        typeColumnB.setCellValueFactory(new PropertyValueFactory<>("type"));
        totalAppointmentsColumnB.setCellValueFactory(new PropertyValueFactory<>("totalAppointments"));
        reportBTableView.setItems(reportBAppointments);

        customerColumnC.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        monthColumnC.setCellValueFactory(new PropertyValueFactory<>("month"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("averageDuration"));
        reportCTableView.setItems(reportCAppointments);
    }
}
