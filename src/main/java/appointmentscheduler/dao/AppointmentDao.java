package appointmentscheduler.dao;

import appointmentscheduler.model.Appointments;
import appointmentscheduler.helper.DBConnection;
import appointmentscheduler.helper.Query;

import appointmentscheduler.model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AppointmentDao {
    public static Users getAppointment(int userId) throws SQLException, Exception {
        DBConnection.openConnection();
        String sqlStatement = "select * FROM appointments WHERE User_ID  = '" + userId + "'";
        Query.makeQuery(sqlStatement);
        Appointments appointmentResult;
        ResultSet result=Query.getResult();
        while(result.next()){
            String result_appointmentId = result.getString("Appointment_ID");
            String result_title = result.getString("Title");
            String result_description = result.getString("Description");
            String result_location = result.getString("Location");
            String result_contactId = result.getString("Contact_ID");
            String result_type = result.getString("Type");
            Timestamp result_start = result.getTimestamp("Start");
            Timestamp result_end = result.getTimestamp("End");
            String result_customerId = result.getString("Customer_ID");

            appointmentResult = new Appointments(result_appointmentId, result_customerId, userId, result_contactId,
                    result_title, result_description, result_location, result_type);
            return appointmentResult;
        }
        DBConnection.closeConnection();
        return null;
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
