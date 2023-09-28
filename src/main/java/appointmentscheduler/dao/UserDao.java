package appointmentscheduler.dao;

import appointmentscheduler.model.Users;
import appointmentscheduler.helper.DBConnection;
import appointmentscheduler.helper.Query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public static Users getUser(String username) throws SQLException, Exception{
        DBConnection.openConnection();
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
        DBConnection.closeConnection();
        return null;
    }
    public static ObservableList<Users> getAllUsers() throws SQLException, Exception{
        ObservableList<Users> allUsers= FXCollections.observableArrayList();
        DBConnection.openConnection();
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
        DBConnection.closeConnection();
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
        DBConnection.openConnection();
        try {
            String sqlStatement = "select * FROM users WHERE User_Name  = '" + username+ "' and Password = '" + password + "'";
            Query.makeQuery(sqlStatement);
            ResultSet result = Query.getResult();
            if ((result.getString("User_Name") == username) && (result.getString("Password") == password)) {
                Integer result_userId = result.getInt("User_ID");
                return result_userId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}