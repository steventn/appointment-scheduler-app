package appointmentscheduler.dao;

import appointmentscheduler.model.Users;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import static appointmentscheduler.helper.DBConnection.connection;

public class UserDao {

    /**
     * Gets all users.
     *
     * @return a list of Users objects
     * @throws SQLException if a database access error occurs
     */
    public ObservableList<Users> getAllUsers() throws SQLException {
        ObservableList<Users> usersList = FXCollections.observableArrayList();
        String getAllUsersSQL = "SELECT * FROM users";
        try (PreparedStatement usersStatement = connection.prepareStatement(getAllUsersSQL);
             ResultSet resultSet = usersStatement.executeQuery()) {
            while (resultSet.next()) {
                Users users = createUsersFromResultSet(resultSet);
                usersList.add(users);
            }
        }
        return usersList;
    }

    /**
     * Validates a user.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return the user id if the user is valid, otherwise a negative integer indicating the error
     * @throws SQLException if a database access error occurs
     */
    public static Integer validateUser(String username, String password) throws SQLException {
        try {
            String checkUsernameSQL = "SELECT * FROM users WHERE User_Name = ?";
            try (PreparedStatement usernameStatement = connection.prepareStatement(checkUsernameSQL)) {
                usernameStatement.setString(1, username);
                try (ResultSet usernameResult = usernameStatement.executeQuery()) {
                    if (usernameResult.next()) {
                        String storedPassword = usernameResult.getString("Password");

                        if (storedPassword.equals(password)) {
                            return usernameResult.getInt("User_ID");
                        } else {
                            return -1;
                        }
                    } else {
                        return -2;
                    }
                }
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * Creates a Users object from a result set.
     *
     * @param resultSet the result set
     * @return a Users object
     * @throws SQLException if a database access error occurs
     */
    private Users createUsersFromResultSet(ResultSet resultSet) throws SQLException {
        int userId = resultSet.getInt("User_ID");
        String username = resultSet.getString("User_name");
        String password = resultSet.getString("Password");
        String createdBy = resultSet.getString("Created_By");
        String lastUpdatedBy = resultSet.getString("Last_Updated_By");
        Timestamp createDate = resultSet.getTimestamp("Create_Date");
        Timestamp lastUpdate = resultSet.getTimestamp("Last_Update");

        return new Users(userId, username, password, createdBy, lastUpdatedBy, createDate, lastUpdate);
    }
}