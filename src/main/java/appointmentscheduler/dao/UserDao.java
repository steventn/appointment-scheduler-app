package appointmentscheduler.dao;

import appointmentscheduler.model.Users;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static appointmentscheduler.helper.DBConnection.connection;

public class UserDao {
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

    public static Users addUser(String username) throws SQLException, Exception {
        return null;
    }

    public static Users updateUser(String username) throws SQLException, Exception {
        return null;
    }

    public static Users deleteUser(String username) throws SQLException, Exception {
        return null;
    }

    public static Integer validateUser(String username, String password) throws SQLException {
        try {
            String checkUsernameSQL = "SELECT * FROM users WHERE User_Name = ?";
            PreparedStatement usernameStatement = connection.prepareStatement(checkUsernameSQL);
            usernameStatement.setString(1, username);
            ResultSet usernameResult = usernameStatement.executeQuery();

            if (usernameResult.next()) {
                String storedPassword = usernameResult.getString("Password");

                if (storedPassword.equals(password)) {
                    int result_userId = usernameResult.getInt("User_ID");
                    return result_userId;
                } else {
                    return -1;
                }
            } else {
                return -2;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

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