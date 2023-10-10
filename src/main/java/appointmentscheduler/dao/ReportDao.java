package appointmentscheduler.dao;

import appointmentscheduler.model.Appointments;
import appointmentscheduler.model.ReportsB;
import appointmentscheduler.model.ReportsC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static appointmentscheduler.helper.DBConnection.connection;

public class ReportDao extends AppointmentDao {
    public ObservableList<ReportsB> getTotalAppointmentsByTypeMonth() {
        ObservableList<ReportsB> reportsBList = FXCollections.observableArrayList();
        try {
            String getReportBSQL = "SELECT type, EXTRACT(MONTH FROM start) as month, COUNT(*) as total_appointments " +
                    "FROM appointments " +
                    "GROUP BY type, month";
            try (PreparedStatement statement = connection.prepareStatement(getReportBSQL);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ReportsB appointmentReportsB = createAppointmentReportBFromResultSet(resultSet);
                    reportsBList.add(appointmentReportsB);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportsBList;
    }

    public ObservableList<ReportsC> getTotalAppointmentsByDuration() {
        ObservableList<ReportsC> reportsCList = FXCollections.observableArrayList();
        try {
            String getReportCSQL = "SELECT MONTH(start) as month, customer_id, AVG(TIMESTAMPDIFF(MINUTE, start, end)) as average_duration " +
                    "FROM appointments " +
                    "GROUP BY MONTH(start), customer_id";
            try (PreparedStatement statement = connection.prepareStatement(getReportCSQL);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ReportsC appointmentReportsC = createAppointmentReportCFromResultSet(resultSet);
                    reportsCList.add(appointmentReportsC);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportsCList;
    }

    public ObservableList<Appointments> getTotalAppointmentsSortedByCustomer() {
        ObservableList<Appointments> reportsAList = FXCollections.observableArrayList();
        try {
            String getReportASQL = "SELECT * " +
                    "FROM appointments " +
                    "ORDER BY contact_id, start";
            try (PreparedStatement statement = connection.prepareStatement(getReportASQL);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Appointments appointmentReportsA = AppointmentDao.createAppointmentsFromResultSet(resultSet);
                    reportsAList.add(appointmentReportsA);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportsAList;
    }

    private static ReportsB createAppointmentReportBFromResultSet(ResultSet resultSet) throws SQLException {
        String month = resultSet.getString("month");
        String type = resultSet.getString("type");
        int totalAppointments = resultSet.getInt("total_appointments");
        return new ReportsB(month, type, totalAppointments);
    }

    private static ReportsC createAppointmentReportCFromResultSet(ResultSet resultSet) throws SQLException {
        String month = resultSet.getString("month");
        String customerId = resultSet.getString("customer_id");
        int averageDuration = resultSet.getInt("average_duration");
        return new ReportsC(customerId, month, averageDuration);
    }
}
