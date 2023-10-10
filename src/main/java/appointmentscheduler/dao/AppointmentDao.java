package appointmentscheduler.dao;

import appointmentscheduler.model.Appointments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.time.LocalDateTime;

import static appointmentscheduler.helper.DBConnection.connection;

public class AppointmentDao {
    public static Appointments getAppointmentByCustomerId(int customerId) throws SQLException {
        Appointments appointmentResult = null;
        try {
            String sqlStatement = "select * FROM appointments WHERE Customer_ID  = ?";
            try (PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
                statement.setInt(1, customerId);
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

    public void updateAppointment(Appointments appointment) throws SQLException {
        String updateCustomerSQL = "UPDATE appointments " +
                "SET Customer_ID = ?, User_ID = ?, Contact_ID = ?, Title = ?, Description = ?, " +
                "Location = ?, Type = ?, Start = ?, End = ? , Created_By = ?, Last_Updated_By = ? " +
                "WHERE Appointment_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateCustomerSQL)) {
            statement.setInt(12, appointment.getAppointmentId());
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLatestAppointmentId() throws SQLException {
        String getLatestAppointmentId = "SELECT Appointment_ID FROM appointments " +
                "ORDER BY Appointment_ID DESC LIMIT 1";
        int appointmentId = 0;
        try (PreparedStatement customerStatement = connection.prepareStatement(getLatestAppointmentId);
             ResultSet resultSet = customerStatement.executeQuery()) {
            if (resultSet.next()) {
                appointmentId = resultSet.getInt("Appointment_ID");
            }
        }
        return appointmentId;
    }

    public void deleteAppointment(int appointmentId) throws SQLException {
        String deleteAppointmentSQL = "DELETE FROM appointments WHERE Appointment_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteAppointmentSQL)) {
            statement.setInt(1, appointmentId);
            statement.executeUpdate();
        }
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

