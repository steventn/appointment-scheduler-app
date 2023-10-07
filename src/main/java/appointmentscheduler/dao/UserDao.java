package appointmentscheduler.dao;

import appointmentscheduler.model.Users;
import appointmentscheduler.helper.DBConnection;
import appointmentscheduler.helper.Query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static appointmentscheduler.helper.DBConnection.connection;

public class UserDao {
    public static Users getUser(String username) throws SQLException, Exception{
        String sqlStatement = "select * FROM users WHERE User_Name  = '" + username+ "'";
        Query.makeQuery(sqlStatement);
        Users userResult;
        ResultSet result = Query.getResult();
        while(result.next()){
            int userid = result.getInt("User_ID");
            String result_username = result.getString("User_Name");
            String result_password = result.getString("Password");
            userResult = new Users(result_username, result_password);
            return userResult;
        }
        return null;
    }
    public static ObservableList<Users> getAllUsers() throws SQLException, Exception{
        ObservableList<Users> allUsers= FXCollections.observableArrayList();
        String sqlStatement="select * from users";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){
            int userid=result.getInt("User_ID");
            String userNameG=result.getString("User_Name");
            String password=result.getString("Password");
            Users userResult= new Users(userNameG, password);
            allUsers.add(userResult);

        }
        return allUsers;
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
}