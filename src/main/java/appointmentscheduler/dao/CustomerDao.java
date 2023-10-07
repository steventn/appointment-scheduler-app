package appointmentscheduler.dao;

import appointmentscheduler.helper.DBConnection;
import appointmentscheduler.model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

import static appointmentscheduler.helper.DBConnection.connection;

public class CustomerDao {

    // Method to fetch all customers from the database
    public ObservableList<Customers> getAllCustomers() throws SQLException {
        ObservableList<Customers> customersList = FXCollections.observableArrayList();
        String getAllCustomersSQL = "SELECT * FROM customers JOIN first_level_divisions ON customers.Division_Id = first_level_divisions.Division_ID JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID;";
        try (PreparedStatement customerStatement = connection.prepareStatement(getAllCustomersSQL);
             ResultSet resultSet = customerStatement.executeQuery()) {
            while (resultSet.next()) {
                Customers customer = createCustomerFromResultSet(resultSet);
                customersList.add(customer);
            }
        }
        return customersList;
    }

    public void addCustomer(Customers customer) throws SQLException {
        try {
            String insertCustomerSQL = "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                statement.setString(10, customer.getDivision());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Customers createCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        int customerId = resultSet.getInt("Customer_ID");
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

        return new Customers(customerId, division, country, name, address, postalCode, phone, createdBy, lastUpdatedBy, createDate, lastUpdate);
    }
}

