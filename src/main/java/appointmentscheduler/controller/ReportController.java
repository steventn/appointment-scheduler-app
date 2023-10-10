package appointmentscheduler.controller;

import appointmentscheduler.dao.ReportDao;
import appointmentscheduler.model.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
    @FXML
    private TableView<Reports> reportATableView;

    @FXML
    private TableColumn<Reports, Integer> appointmentIDColumnA;

    @FXML
    private TableColumn<Reports, String> titleColumnA;

    @FXML
    private TableColumn<Reports, String> typeColumn;

    @FXML
    private TableColumn<Reports, String> descriptionColumn;

    @FXML
    private TableColumn<Reports, String> startDateTimeColumnA;

    @FXML
    private TableColumn<Reports, String> endDateTimeColumnA;

    @FXML
    private TableColumn<Reports, Integer> customerIDColumnA;

    @FXML
    private TableView<Reports> reportBTableView;

    @FXML
    private TableColumn<Reports, String> monthColumn;

    @FXML
    private TableColumn<Reports, String> typeColumnB;

    @FXML
    private TableColumn<Reports, Integer> totalAppointmentsColumnB;

    @FXML
    private TableView<Reports> reportCTableView;

    @FXML
    private TableColumn<Reports, String> customerColumnC;

    @FXML
    private TableColumn<Reports, String> monthColumnC;

    @FXML
    private TableColumn<Reports, String> durationColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ReportDao reportDao = new ReportDao();
        ObservableList<Reports> reportBAppointments = reportDao.getTotalAppointmentsByTypeMonth();

        monthColumn.setCellValueFactory(new PropertyValueFactory<>("month"));
        typeColumnB.setCellValueFactory(new PropertyValueFactory<>("type"));
        totalAppointmentsColumnB.setCellValueFactory(new PropertyValueFactory<>("totalAppointments"));
        reportBTableView.setItems(reportBAppointments);
    }
}
