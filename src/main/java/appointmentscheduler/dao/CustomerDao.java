package appointmentscheduler.dao;

import appointmentscheduler.helper.DBConnection;
import appointmentscheduler.helper.Query;
import appointmentscheduler.model.Users;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {
    public static Users getCustomer(String username) throws SQLException, Exception{
        DBConnection.openConnection();
        String sqlStatement = "select * FROM users WHERE User_Name  = '" + username+ "'";
        Query.makeQuery(sqlStatement);
        Users userResult;
        ResultSet result=Query.getResult();
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
}
