package appointmentscheduler.dao;

import appointmentscheduler.model.Countries;
import appointmentscheduler.model.FirstLevelDivisons;
import appointmentscheduler.helper.DBConnection;
import appointmentscheduler.helper.Query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class FirstLevelDivisionDao {
    public static FirstLevelDivisons getFirstLevelDivision(int divisionId) throws SQLException, Exception {
        DBConnection.openConnection();
        String sqlStatement = "select * FROM first_level_division WHERE Division_ID  = '" + divisionId + "'";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        FirstLevelDivisons firstLevelDivisionResult = null;
        while (result.next()) {
            Integer result_divisionId = result.getInt("Division_ID");
            String result_division = result.getString("Division");
            Integer result_countryId = result.getInt("Country_ID");

            firstLevelDivisionResult = new FirstLevelDivisons(result_divisionId, result_division, result_countryId);
        }
        DBConnection.closeConnection();
        return firstLevelDivisionResult;
    }
}

//    private int divisionId;
//    private String division;
//    private Timestamp createDate;
//    private String createdBy;
//    private Timestamp lastUpdate;
//    private String lastUpdatedBy;
//    private int countryId;
