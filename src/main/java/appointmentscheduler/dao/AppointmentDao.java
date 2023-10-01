package appointmentscheduler.dao;

import appointmentscheduler.model.Appointments;
import appointmentscheduler.helper.DBConnection;
import appointmentscheduler.helper.Query;

import appointmentscheduler.model.Customers;
import appointmentscheduler.model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static appointmentscheduler.helper.DBConnection.connection;

public class AppointmentDao {
    public static Appointments getAppointment(int userId) throws SQLException, Exception {
        DBConnection.openConnection();
        String sqlStatement = "select * FROM appointments WHERE User_ID  = '" + userId + "'";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        Appointments appointmentResult = null;
        while (result.next()) {
            String result_title = result.getString("Title");
            String result_description = result.getString("Description");
            String result_location = result.getString("Location");
            String result_type = result.getString("Type");
            String result_createdBy = result.getString("Created_By");
            String result_lastUpdatedBy = result.getString("Last_Updated_By");
            LocalDateTime result_start = result.getTimestamp("Start").toLocalDateTime();
            LocalDateTime result_end = result.getTimestamp("End").toLocalDateTime();
            Integer result_appointmentId = result.getInt("Appointment_ID");
            Integer result_customerId = result.getInt("Customer_ID");
            Integer result_userId = result.getInt("User_ID");
            Integer result_contactId = result.getInt("Contact_ID");

            appointmentResult = new Appointments(result_appointmentId, result_customerId, result_userId, result_contactId, result_title, result_description, result_location, result_type, result_createdBy, result_lastUpdatedBy, result_start, result_end);
        }
        DBConnection.closeConnection();
        return appointmentResult;
    }

    public List<Appointments> getAllAppointments() throws SQLException {
        List<Appointments> appointmentsList = new ArrayList<>();
        String getAllCustomersSQL = "SELECT * FROM appointments";
        try (PreparedStatement customerStatement = connection.prepareStatement(getAllCustomersSQL);
             ResultSet resultSet = customerStatement.executeQuery()) {
            while (resultSet.next()) {
                Appointments appointments = createAppointmentsFromResultSet(resultSet);
                appointmentsList.add(appointments);
            }
        }
        return appointmentsList;
    }

    public static Users addAppointment(String username) throws SQLException, Exception {
        return null;
    }

    public static Users updateAppointment(String username) throws SQLException, Exception {
        return null;
    }

    public static Users deleteAppointment(String username) throws SQLException, Exception {
        return null;
    }

    private Appointments createAppointmentsFromResultSet(ResultSet resultSet) throws SQLException {
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