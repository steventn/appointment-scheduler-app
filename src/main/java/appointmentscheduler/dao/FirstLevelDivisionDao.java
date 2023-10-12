package appointmentscheduler.dao;

import appointmentscheduler.model.FirstLevelDivisions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static appointmentscheduler.helper.DBConnection.connection;

public class FirstLevelDivisionDao {

    /**
     * Gets all first level divisions.
     *
     * @return a list of first level divisions
     * @throws SQLException if a database access error occurs
     */
    public ObservableList<FirstLevelDivisions> getAllFirstLevelDivisions() throws SQLException {
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

    /**
     * Gets first level divisions by country.
     *
     * @param country the country name
     * @return a list of first level divisions
     * @throws SQLException if a database access error occurs
     */
    public ObservableList<FirstLevelDivisions> getFirstLevelDivisionByCountry(String country) throws SQLException {
        ObservableList<FirstLevelDivisions> firstLevelDivisionsList = FXCollections.observableArrayList();
        String firstLevelDivisionsSQL = "SELECT * FROM FIRST_LEVEL_DIVISIONS " +
                "JOIN COUNTRIES ON FIRST_LEVEL_DIVISIONS.Country_ID = COUNTRIES.Country_ID " +
                "WHERE Country = ?";
        try (PreparedStatement firstLevelDivisionStatement = connection.prepareStatement(firstLevelDivisionsSQL)) {
            firstLevelDivisionStatement.setString(1, country);
            try (ResultSet resultSet = firstLevelDivisionStatement.executeQuery()) {
                while (resultSet.next()) {
                    FirstLevelDivisions firstLevelDivisions = createFirstLevelDivisionsFromResultSet(resultSet);
                    firstLevelDivisionsList.add(firstLevelDivisions);
                }
            }
        }
        return firstLevelDivisionsList;
    }

    /**
     * Creates a FirstLevelDivisions object from a result set.
     *
     * @param resultSet the result set
     * @return a FirstLevelDivisions object
     * @throws SQLException if a database access error occurs
     */
    private FirstLevelDivisions createFirstLevelDivisionsFromResultSet(ResultSet resultSet) throws SQLException {
        int divisionId = resultSet.getInt("Division_ID");
        String division = resultSet.getString("Division");
        int countryId = resultSet.getInt("Country_ID");

        return new FirstLevelDivisions(divisionId, division, countryId);
    }
}