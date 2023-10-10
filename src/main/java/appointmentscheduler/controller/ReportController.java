package appointmentscheduler.controller;

import appointmentscheduler.dao.AppointmentDao;
import appointmentscheduler.dao.ContactDao;
import appointmentscheduler.dao.CustomerDao;
import appointmentscheduler.dao.UserDao;
import appointmentscheduler.helper.TimeUtil;
import appointmentscheduler.model.Appointments;
import appointmentscheduler.model.Contacts;
import appointmentscheduler.model.Customers;
import appointmentscheduler.model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ReportController implements Initializable {
    @FXML
    private TableView<Appointments> reportATableView;

    @FXML
    private TableColumn<Appointments, Integer> appointmentIDColumnA;

    @FXML
    private TableColumn<Appointments, String> titleColumnA;

    @FXML
    private TableColumn<Appointments, String> typeColumn;

    @FXML
    private TableColumn<Appointments, String> descriptionColumn;

    @FXML
    private TableColumn<Appointments, String> startDateTimeColumnA;

    @FXML
    private TableColumn<Appointments, String> endDateTimeColumnA;

    @FXML
    private TableColumn<Appointments, Integer> customerIDColumnA;

    @FXML
    private TableView<Appointments> reportBTableView;

    @FXML
    private TableColumn<Appointments, String> monthColumn;

    @FXML
    private TableColumn<Appointments, String> typeColumnB;

    @FXML
    private TableColumn<Appointments, Integer> totalAppointmentsColumnB;

    @FXML
    private TableView<Appointments> reportCTableView;

    @FXML
    private TableColumn<Appointments, String> customerColumnC;

    @FXML
    private TableColumn<Appointments, String> monthColumnC;

    @FXML
    private TableColumn<Appointments, String> durationColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AppointmentDao appointmentDao = new AppointmentDao();
        ObservableList<Appointments> reportBAppointments = appointmentDao.getTotalAppointmentsByTypeMonth();

        appointmentIDColumnA.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumnA.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateTimeColumnA.setCellValueFactory(new PropertyValueFactory<>("start"));
        endDateTimeColumnA.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIDColumnA.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        reportBTableView.setItems(reportBAppointments);

    }
}
