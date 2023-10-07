package appointmentscheduler.dao;

import appointmentscheduler.model.Countries;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static appointmentscheduler.helper.DBConnection.connection;

public class CountryDao {
    public ObservableList<Countries> getAllCountries() throws SQLException, Exception {
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

    private Countries createCountriesFromResultSet(ResultSet resultSet) throws SQLException {
        int countryId = resultSet.getInt("Country_ID");
        String country = resultSet.getString("Country");

        return new Countries(countryId, country);
    }
}
