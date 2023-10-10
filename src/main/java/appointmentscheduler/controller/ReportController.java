package appointmentscheduler.controller;

import appointmentscheduler.dao.ReportDao;
import appointmentscheduler.model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
    @FXML
    private TableView<ReportsB> reportATableView;

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

    @FXML
    private void navigateToMainView(ActionEvent event) throws Exception {
        Pane mainView = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        Scene scene = new Scene(mainView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ReportDao reportDao = new ReportDao();
        ObservableList<ReportsB> reportBAppointments = reportDao.getTotalAppointmentsByTypeMonth();
        ObservableList<ReportsC> reportCAppointments = reportDao.getTotalAppointmentsByDuration();

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
