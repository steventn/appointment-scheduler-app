package appointmentscheduler.dao;

import appointmentscheduler.helper.TimeUtil;
import appointmentscheduler.model.Appointments;
import appointmentscheduler.model.ReportsB;
import appointmentscheduler.model.ReportsC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import static appointmentscheduler.helper.DBConnection.connection;

public class ReportDao extends AppointmentDao {

    /**
     * Gets total appointments by type and month.
     *
     * @return a list of ReportsB objects
     * @throws SQLException if a database access error occurs
     */
    public ObservableList<ReportsB> getTotalAppointmentsByTypeMonth() throws SQLException {
        ObservableList<ReportsB> reportsBList = FXCollections.observableArrayList();
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
        return reportsBList;
    }

    /**
     * Gets total appointments by duration.
     *
     * @return a list of ReportsC objects
     * @throws SQLException if a database access error occurs
     */
    public ObservableList<ReportsC> getTotalAppointmentsByDuration() throws SQLException {
        ObservableList<ReportsC> reportsCList = FXCollections.observableArrayList();
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
        return reportsCList;
    }

    /**
     * Gets total appointments sorted by customer.
     *
     * @return a list of Appointments objects
     * @throws SQLException if a database access error occurs
     */
    public ObservableList<Appointments> getTotalAppointmentsSortedByCustomer() throws SQLException {
        ObservableList<Appointments> reportsAList = FXCollections.observableArrayList();
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
        return reportsAList;
    }

    /**
     * Creates a ReportsB object from a result set.
     *
     * @param resultSet the result set
     * @return a ReportsB object
     * @throws SQLException if a database access error occurs
     */
    private static ReportsB createAppointmentReportBFromResultSet(ResultSet resultSet) throws SQLException {
        String month = resultSet.getString("month");
        String monthName = TimeUtil.getMonthName(Integer.parseInt(month));
        String type = resultSet.getString("type");
        int totalAppointments = resultSet.getInt("total_appointments");
        return new ReportsB(monthName, type, totalAppointments);
    }

    /**
     * Creates a ReportsC object from a result set.
     *
     * @param resultSet the result set
     * @return a ReportsC object
     * @throws SQLException if a database access error occurs
     */
    private static ReportsC createAppointmentReportCFromResultSet(ResultSet resultSet) throws SQLException {
        String month = resultSet.getString("month");
        String monthName = TimeUtil.getMonthName(Integer.parseInt(month));
        String customerId = resultSet.getString("customer_id");
        int averageDuration = resultSet.getInt("average_duration");
        return new ReportsC(customerId, monthName, averageDuration);
    }
}