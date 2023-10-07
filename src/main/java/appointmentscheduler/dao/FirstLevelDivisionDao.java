package appointmentscheduler.dao;

import appointmentscheduler.model.FirstLevelDivisions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static appointmentscheduler.helper.DBConnection.connection;

public class FirstLevelDivisionDao {
    public ObservableList<FirstLevelDivisions> getAllFirstLevelDivisions() throws Exception {
        ObservableList<FirstLevelDivisions> firstLevelDivisionsList = FXCollections.observableArrayList();
        String firstLevelDivisionsSQL = "select * FROM first_level_divisions";
        try (PreparedStatement firstLevelDivisionStatement = connection.prepareStatement(firstLevelDivisionsSQL);
             ResultSet resultSet = firstLevelDivisionStatement.executeQuery()) {
            while (resultSet.next()) {
                FirstLevelDivisions firstLevelDivisions = createFirstLevelDivisionsFromResultSet(resultSet);
                firstLevelDivisionsList.add(firstLevelDivisions);
            }
        }
        return firstLevelDivisionsList;
    }

    public ObservableList<FirstLevelDivisions> getFirstLevelDivisionByCountry(String country) throws SQLException {
        ObservableList<FirstLevelDivisions> firstLevelDivisionsList = FXCollections.observableArrayList();
        String firstLevelDivisionsSQL = "SELECT * FROM FIRST_LEVEL_DIVISIONS \n" +
                "JOIN COUNTRIES ON FIRST_LEVEL_DIVISIONS.Country_ID = COUNTRIES.Country_ID\n" +
                "WHERE Country = '" + country + "'";
        try (PreparedStatement firstLevelDivisionStatement = connection.prepareStatement(firstLevelDivisionsSQL);
             ResultSet resultSet = firstLevelDivisionStatement.executeQuery()) {
            while (resultSet.next()) {
                FirstLevelDivisions firstLevelDivisions = createFirstLevelDivisionsFromResultSet(resultSet);
                firstLevelDivisionsList.add(firstLevelDivisions);
            }
        }
        return firstLevelDivisionsList;
    }

    private FirstLevelDivisions createFirstLevelDivisionsFromResultSet(ResultSet resultSet) throws SQLException {
        int divisionId = resultSet.getInt("Division_ID");
        String division = resultSet.getString("Division");
        int countryId = resultSet.getInt("Country_ID");

        return new FirstLevelDivisions(divisionId, division, countryId);
    }
}
