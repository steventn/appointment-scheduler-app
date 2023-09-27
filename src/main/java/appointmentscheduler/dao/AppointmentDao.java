package appointmentscheduler.dao;

import appointmentscheduler.model.Appointments;
import appointmentscheduler.helper.DBConnection;
import appointmentscheduler.helper.Query;

import appointmentscheduler.model.Users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

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

    public static Users addAppointment(String username) throws SQLException, Exception {
        return null;
    }

    public static Users updateAppointment(String username) throws SQLException, Exception {
        return null;
    }

    public static Users deleteAppointment(String username) throws SQLException, Exception {
        return null;
    }
}
