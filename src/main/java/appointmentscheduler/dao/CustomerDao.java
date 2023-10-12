package appointmentscheduler.dao;

import appointmentscheduler.model.Customers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import static appointmentscheduler.helper.DBConnection.connection;

public class CustomerDao {

    /**
     * Gets all customers.
     *
     * @return a list of customers
     * @throws SQLException if a database access error occurs
     */
    public ObservableList<Customers> getAllCustomers() throws SQLException {
        ObservableList<Customers> customersList = FXCollections.observableArrayList();
        String getAllCustomersSQL = "SELECT * FROM customers " +
                "JOIN first_level_divisions ON customers.Division_Id = first_level_divisions.Division_ID " +
                "JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID";
        try (PreparedStatement customerStatement = connection.prepareStatement(getAllCustomersSQL);
             ResultSet resultSet = customerStatement.executeQuery()) {
            while (resultSet.next()) {
                Customers customer = createCustomerFromResultSet(resultSet);
                customersList.add(customer);
            }
        }
        return customersList;
    }

    /**
     * Adds a new customer.
     *
     * @param customer the customer to add
     * @throws SQLException if a database access error occurs
     */
    public void addCustomer(Customers customer) throws SQLException {
        String insertCustomerSQL = "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, " +
                "Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertCustomerSQL)) {
            statement.setInt(1, customer.getCustomerId());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getPostalCode());
            statement.setString(5, customer.getPhone());
            statement.setTimestamp(6, customer.getCreateDate());
            statement.setString(7, customer.getCreatedBy());
            statement.setTimestamp(8, customer.getLastUpdate());
            statement.setString(9, customer.getLastUpdatedBy());
            statement.setInt(10, customer.getDivisionId());

            statement.executeUpdate();
        }
    }

    /**
     * Updates an existing customer.
     *
     * @param customer the customer to update
     * @throws SQLException if a database access error occurs
     */
    public void updateCustomer(Customers customer) throws SQLException {
        String updateCustomerSQL = "UPDATE customers " +
                "SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Create_Date = ?, " +
                "Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? " +
                "WHERE Customer_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateCustomerSQL)) {
            statement.setInt(10, customer.getCustomerId());
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getPostalCode());
            statement.setString(4, customer.getPhone());
            statement.setTimestamp(5, customer.getCreateDate());
            statement.setString(6, customer.getCreatedBy());
            statement.setTimestamp(7, customer.getLastUpdate());
            statement.setString(8, customer.getLastUpdatedBy());
            statement.setInt(9, customer.getDivisionId());

            statement.executeUpdate();
        }
    }

    /**
     * Deletes a customer.
     *
     * @param customerId the ID of the customer to delete
     * @throws SQLException if a database access error occurs
     */
    public void deleteCustomer(int customerId) throws SQLException {
        String deleteCustomerSQL = "DELETE FROM customers WHERE Customer_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteCustomerSQL)) {
            statement.setInt(1, customerId);
            statement.executeUpdate();
        }
    }

    /**
     * Gets the latest customer ID.
     *
     * @return the latest customer ID
     * @throws SQLException if a database access error occurs
     */
    public int getLatestCustomerId() throws SQLException {
        String getLastCustomerId = "SELECT Customer_ID FROM customers " +
                "ORDER BY Customer_ID DESC LIMIT 1";
        int customerId = 0;
        try (PreparedStatement customerStatement = connection.prepareStatement(getLastCustomerId);
             ResultSet resultSet = customerStatement.executeQuery()) {
            if (resultSet.next()) {
                customerId = resultSet.getInt("Customer_ID");
            }
        }
        return customerId;
    }

    /**
     * Creates a Customers object from a result set.
     *
     * @param resultSet the result set
     * @return a Customers object
     * @throws SQLException if a database access error occurs
     */
    private Customers createCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        int customerId = resultSet.getInt("Customer_ID");
        int divisionId = resultSet.getInt("Division_ID");
        String division = resultSet.getString("Division");
        String country = resultSet.getString("Country");
        String name = resultSet.getString("Customer_Name");
        String address = resultSet.getString("Address");
        String postalCode = resultSet.getString("Postal_Code");
        String phone = resultSet.getString("Phone");
        String createdBy = resultSet.getString("Created_By");
        String lastUpdatedBy = resultSet.getString("Last_Updated_By");
        Timestamp createDate = resultSet.getTimestamp("Create_Date");
        Timestamp lastUpdate = resultSet.getTimestamp("Last_Update");

        return new Customers(customerId, divisionId, division, country, name, address, postalCode, phone, createdBy, lastUpdatedBy, createDate, lastUpdate);
    }
}