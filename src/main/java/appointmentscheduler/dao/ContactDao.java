package appointmentscheduler.dao;

import appointmentscheduler.model.Appointments;
import appointmentscheduler.model.Contacts;
import appointmentscheduler.helper.DBConnection;
import appointmentscheduler.helper.Query;

import appointmentscheduler.model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ContactDao {

    private static final Connection connection = DBConnection.connection;

    public ObservableList<Contacts> getAllContacts() throws SQLException {
        ObservableList<Contacts> contactList = FXCollections.observableArrayList();
        String getAllContactsSQL = "SELECT * FROM contacts";
        try (PreparedStatement contactStatement = connection.prepareStatement(getAllContactsSQL);
             ResultSet resultSet = contactStatement.executeQuery()) {
            while (resultSet.next()) {
                Contacts contacts = createContactFromResultSet(resultSet);
                contactList.add(contacts);
            }
        }
        return contactList;
    }

    private Contacts createContactFromResultSet(ResultSet resultSet) throws SQLException {
        int contactId = resultSet.getInt("Contact_ID");
        String contactName = resultSet.getString("Contact_Name");
        String email = resultSet.getString("Email");

        return new Contacts(contactId, contactName, email);
    }
}
