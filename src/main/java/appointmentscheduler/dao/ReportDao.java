package appointmentscheduler.dao;

import appointmentscheduler.model.Appointments;
import appointmentscheduler.model.Reports;
import appointmentscheduler.model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static appointmentscheduler.helper.DBConnection.connection;

public class ReportDao {
    public ObservableList<Reports> getTotalAppointmentsByTypeMonth() {
        ObservableList<Reports> reportsList = FXCollections.observableArrayList();
        try {
            String getReportASQL = "SELECT type, EXTRACT(MONTH FROM start) as month, COUNT(*) as total_appointments " +
                    "FROM appointments " +
                    "GROUP BY type, month";
            try (PreparedStatement statement = connection.prepareStatement(getReportASQL);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Reports appointmentReports = createAppointmentReportBFromResultSet(resultSet);
                    reportsList.add(appointmentReports);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportsList;
    }

    public ObservableList<Reports> getTotalAppointmentsByDuration() {
        ObservableList<Reports> reportsList = FXCollections.observableArrayList();
        try {
            String getReportASQL = "SELECT MONTH(start) as month, customerId, AVG(TIMESTAMPDIFF(MINUTE, start, end)) as average_duration" +
                    "FROM appointments " +
                    "GROUP BY MONTH(start), customerId;";
            try (PreparedStatement statement = connection.prepareStatement(getReportASQL);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Reports appointmentReports = createAppointmentReportBFromResultSet(resultSet);
                    reportsList.add(appointmentReports);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportsList;
    }

    private static Reports createAppointmentReportBFromResultSet(ResultSet resultSet) throws SQLException {
        String month = resultSet.getString("month");
        String type = resultSet.getString("type");
        int totalAppointments = resultSet.getInt("total_appointments");
        return new Reports(month, type, totalAppointments);
    }

}
