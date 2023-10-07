package appointmentscheduler.dao;

import appointmentscheduler.model.Appointments;
import appointmentscheduler.helper.DBConnection;
import appointmentscheduler.helper.Query;

import appointmentscheduler.model.Customers;
import appointmentscheduler.model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static appointmentscheduler.helper.DBConnection.connection;

public class AppointmentDao {
    public static Appointments getAppointment(int userId) throws SQLException {
        Appointments appointmentResult = null;
        try {
            String sqlStatement = "select * FROM appointments WHERE User_ID  = ?";
            try (PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
                statement.setInt(1, userId);
                ResultSet result = statement.executeQuery();

                while (result.next()) {
                    appointmentResult = createAppointmentsFromResultSet(result);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentResult;
    }

    public ObservableList<Appointments> getAllAppointments() throws SQLException {
        ObservableList<Appointments> appointmentList = FXCollections.observableArrayList();
        try {
            String getAllAppointmentsSQL = "SELECT * FROM appointments";
            try (PreparedStatement statement = connection.prepareStatement(getAllAppointmentsSQL);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Appointments appointments = createAppointmentsFromResultSet(resultSet);
                    appointmentList.add(appointments);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentList;
    }

    public void addAppointment(Appointments appointment) throws SQLException {
        try {
            String insertAppointmentSQL = "INSERT INTO appointments (Customer_ID, User_ID, Contact_ID, Title, Description, Location, Type, Start, End, Created_By, Last_Updated_By) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertAppointmentSQL)) {
                statement.setInt(1, appointment.getCustomerId());
                statement.setInt(2, appointment.getUserId());
                statement.setInt(3, appointment.getContactId());
                statement.setString(4, appointment.getTitle());
                statement.setString(5, appointment.getDescription());
                statement.setString(6, appointment.getLocation());
                statement.setString(7, appointment.getType());
                statement.setTimestamp(8, Timestamp.valueOf(appointment.getStart()));
                statement.setTimestamp(9, Timestamp.valueOf(appointment.getEnd()));
                statement.setString(10, appointment.getCreatedBy());
                statement.setString(11, appointment.getLastUpdatedBy());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Users updateAppointment(String username) throws SQLException {
        return null;
    }

    public static Users deleteAppointment(String username) throws SQLException {
        return null;
    }

    private static Appointments createAppointmentsFromResultSet(ResultSet resultSet) throws SQLException {
        int appointmentId = resultSet.getInt("Appointment_ID");
        int customerId = resultSet.getInt("Customer_ID");
        int userId = resultSet.getInt("User_ID");
        int contactId = resultSet.getInt("Contact_ID");
        String title = resultSet.getString("Title");
        String location = resultSet.getString("Location");
        String type = resultSet.getString("Type");
        String description = resultSet.getString("Description");
        String createdBy = resultSet.getString("Created_By");
        String lastUpdatedBy = resultSet.getString("Last_Updated_By");
        LocalDateTime start = resultSet.getTimestamp("Start").toLocalDateTime();
        LocalDateTime end = resultSet.getTimestamp("End").toLocalDateTime();

        return new Appointments(appointmentId, customerId, userId, contactId, title, description, location, type, createdBy, lastUpdatedBy, start, end);
    }
}
