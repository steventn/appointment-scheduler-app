package appointmentscheduler.dao;

import appointmentscheduler.helper.DBConnection;
import appointmentscheduler.model.Customers;
import static appointmentscheduler.helper.DBConnection.connection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    // Method to fetch all customers from the database
    public List<Customers> getAllCustomers() throws SQLException {
        List<Customers> customersList = new ArrayList<>();
        String getAllCustomersSQL = "SELECT * FROM customers";
        try (PreparedStatement customerStatement = connection.prepareStatement(getAllCustomersSQL);
             ResultSet resultSet = customerStatement.executeQuery()) {
            while (resultSet.next()) {
                Customers customer = createCustomerFromResultSet(resultSet);
                customersList.add(customer);
            }
        }
        return customersList;
    }

    // Utility method to create a Customers object from a ResultSet
    private Customers createCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        int customerId = resultSet.getInt("Customer_ID");
        int divisionId = resultSet.getInt("Division_ID");
        String name = resultSet.getString("Customer_Name");
        String address = resultSet.getString("Address");
        String postalCode = resultSet.getString("Postal_Code");
        String phone = resultSet.getString("Phone");
        String createdBy = resultSet.getString("Created_By");
        String lastUpdatedBy = resultSet.getString("Last_Updated_By");
        Timestamp createDate = resultSet.getTimestamp("Create_Date");
        Timestamp lastUpdate = resultSet.getTimestamp("Last_Update");

        return new Customers(customerId, divisionId, name, address, postalCode, phone, createdBy, lastUpdatedBy, createDate, lastUpdate);
    }

    // Add more methods for inserting, updating, and deleting customers
}

