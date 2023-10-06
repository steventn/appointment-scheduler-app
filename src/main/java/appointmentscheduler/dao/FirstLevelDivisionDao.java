package appointmentscheduler.dao;

import appointmentscheduler.model.FirstLevelDivisions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static appointmentscheduler.helper.DBConnection.connection;

public class FirstLevelDivisionDao {
    public ObservableList<FirstLevelDivisions> getAllFirstLevelDivisionByCountry(int countryId) throws SQLException, Exception {
        ObservableList<FirstLevelDivisions> firstLevelDivisionsList = FXCollections.observableArrayList();
        String firstLevelDivisionsSQL = "select * FROM first_level_division WHERE Country_ID  = '" + countryId + "'";
        try (PreparedStatement firstLevelDivisionStatement = connection.prepareStatement(firstLevelDivisionsSQL);
             ResultSet resultSet = firstLevelDivisionStatement.executeQuery()) {
            while (resultSet.next()) {
                FirstLevelDivisions firstLevelDivisions = createFirstLevelDivisionsFromResultSet(resultSet);
                firstLevelDivisionsList.add(firstLevelDivisions);
            }
        }
        return firstLevelDivisionsList;
    }

    public String getDivisionNameById(int divisionId) throws SQLException {
        String divisionName = null;
        String divisionSQL = "select Division FROM first_level_division WHERE Division_ID = ?";
        try (PreparedStatement divisionStatement = connection.prepareStatement(divisionSQL)) {
            divisionStatement.setInt(1, divisionId);
            try (ResultSet resultSet = divisionStatement.executeQuery()) {
                if (resultSet.next()) {
                    divisionName = resultSet.getString("Division");
                }
            }
        }
        return divisionName;
    }

    private FirstLevelDivisions createFirstLevelDivisionsFromResultSet(ResultSet resultSet) throws SQLException {
        int divisionId = resultSet.getInt("Division_ID");
        String division = resultSet.getString("Division");
        int countryId = resultSet.getInt("CountryId");

        return new FirstLevelDivisions(divisionId, division, countryId);
    }
}
