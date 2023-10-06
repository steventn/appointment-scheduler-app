package appointmentscheduler.dao;

import appointmentscheduler.model.Contacts;
import appointmentscheduler.model.Countries;
import appointmentscheduler.helper.DBConnection;
import appointmentscheduler.helper.Query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDao {
    public static Countries getCountry(int countryId) throws SQLException, Exception {
        DBConnection.openConnection();
        String sqlStatement = "select * FROM countries WHERE Country_ID  = '" + countryId + "'";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        Countries countryResult = null;
        while (result.next()) {
            int result_countryId = result.getInt("Country_ID");
            String result_country = result.getString("Country");

            countryResult = new Countries(result_countryId, result_country);
        }
        DBConnection.closeConnection();
        return countryResult;
    }
}
