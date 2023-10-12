package appointmentscheduler.model;

import java.sql.Timestamp;

public class FirstLevelDivisions {
    private int divisionId;
    private String division;
    private int countryId;

    public FirstLevelDivisions(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    /**
     * Gets the division id.
     *
     * @return the division id
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Gets the division name.
     *
     * @return the division name
     */
    public String getDivision() {
        return division;
    }

    /**
     * Gets the country id.
     *
     * @return the country id
     */
    public int getCountryId() {
        return countryId;
    }
}
