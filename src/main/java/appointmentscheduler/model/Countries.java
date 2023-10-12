package appointmentscheduler.model;

public class Countries {
    private int countryId;
    private String country;

    public Countries(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    /**
     * Gets the country id.
     *
     * @return the country id
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Gets the name of the country.
     *
     * @return the name of the country
     */
    public String getCountry() {
        return country;
    }
}
