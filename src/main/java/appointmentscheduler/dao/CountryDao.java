package appointmentscheduler.dao;

import appointmentscheduler.model.Countries;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import static appointmentscheduler.helper.DBConnection.connection;

public class CountryDao {

    /**
     * Gets all countries.
     *
     * @return a list of countries
     * @throws SQLException if a database access error occurs
     */
    public ObservableList<Countries> getAllCountries() throws SQLException {
        ObservableList<Countries> countriesList = FXCollections.observableArrayList();
        String countriesSQL = "select * FROM countries";
        try (PreparedStatement countriesStatement = connection.prepareStatement(countriesSQL);
             ResultSet resultSet = countriesStatement.executeQuery()) {
            while (resultSet.next()) {
                Countries countries = createCountriesFromResultSet(resultSet);
                countriesList.add(countries);
            }
        }
        return countriesList;
    }

    /**
     * Creates a Countries object from a result set.
     *
     * @param resultSet the result set
     * @return a Countries object
     * @throws SQLException if a database access error occurs
     */
    private Countries createCountriesFromResultSet(ResultSet resultSet) throws SQLException {
        int countryId = resultSet.getInt("Country_ID");
        String country = resultSet.getString("Country");

        return new Countries(countryId, country);
    }
}