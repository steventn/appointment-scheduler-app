package appointmentscheduler.dao;

import appointmentscheduler.model.Appointments;
import appointmentscheduler.model.Contacts;
import appointmentscheduler.helper.DBConnection;
import appointmentscheduler.helper.Query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDao {
    public static Contacts getContact(int contactId) throws SQLException, Exception {
        String sqlStatement = "select * FROM contacts WHERE Contact_Name  = '" + contactId + "'";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        Contacts contactResult = null;
        while (result.next()) {
            int result_contactId = result.getInt("Contact_ID");
            String result_contactName = result.getString("Contact_Name");
            String result_email = result.getString("Email");

            contactResult = new Contacts(result_contactId, result_contactName, result_email);
        }
        return contactResult;
    }
}
