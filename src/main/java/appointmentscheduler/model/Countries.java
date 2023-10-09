package appointmentscheduler.model;

public class Countries {
    private int countryId;
    private String country;

    public Countries(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getCountry() {
        return country;
    }

}
