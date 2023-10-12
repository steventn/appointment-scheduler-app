package appointmentscheduler.dao;

import appointmentscheduler.model.Contacts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import static appointmentscheduler.helper.DBConnection.connection;

public class ContactDao {

    /**
     * Gets all contacts.
     *
     * @return a list of contacts
     * @throws SQLException if a database access error occurs
     */
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

    /**
     * Gets a contact by contact ID.
     *
     * @param contactId the ID of the contact
     * @return a contact
     * @throws SQLException if a database access error occurs
     */
    public Contacts getContact(int contactId) throws SQLException {
        Contacts contactResult = null;
        String sqlStatement = "select * FROM contacts WHERE Contact_ID  = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
            statement.setInt(1, contactId);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                contactResult = createContactFromResultSet(result);
            }
        }
        return contactResult;
    }

    /**
     * Creates a Contact object from a result set.
     *
     * @param resultSet the result set
     * @return a Contact object
     * @throws SQLException if a database access error occurs
     */
    private Contacts createContactFromResultSet(ResultSet resultSet) throws SQLException {
        int contactId = resultSet.getInt("Contact_ID");
        String contactName = resultSet.getString("Contact_Name");
        String email = resultSet.getString("Email");

        return new Contacts(contactId, contactName, email);
    }
}