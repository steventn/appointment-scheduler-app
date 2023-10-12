package appointmentscheduler.model;

import java.sql.Timestamp;

public class Customers {
    private int customerId;
    private int divisionId;
    private String division;
    private String country;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private String createdBy;
    private String lastUpdatedBy;
    private Timestamp createDate;
    private Timestamp lastUpdate;

    public Customers(int customerId, int divisionId, String division, String country, String name, String address, String postalCode, String phone, String createdBy, String lastUpdatedBy, Timestamp createDate, Timestamp lastUpdate) {
        this.customerId = customerId;
        this.divisionId = divisionId;
        this.division = division;
        this.country = country;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createdBy = createdBy;
        this.lastUpdatedBy = lastUpdatedBy;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }

    public Customers(int customerId, int divisionId, String country, String name, String address, String postalCode, String phone, String createdBy, String lastUpdatedBy, Timestamp createDate, Timestamp lastUpdate) {
        this.customerId = customerId;
        this.divisionId = divisionId;
        this.country = country;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createdBy = createdBy;
        this.lastUpdatedBy = lastUpdatedBy;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }

    public Customers(int customerId, int divisionId, String country, String name, String address, String postalCode, String phone) {
        this.customerId = customerId;
        this.divisionId = divisionId;
        this.country = country;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
    }

    /**
     * Gets the customer id.
     *
     * @return the customer id
     */
    public int getCustomerId() {
        return customerId;
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
     * Gets the country name.
     *
     * @return the country name
     */
    public String getCountry() {
        return country;
    }

    /**
     * Gets the customer name.
     *
     * @return the customer name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the customer address.
     *
     * @return the customer address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the customer postal code.
     *
     * @return the customer postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Gets the customer phone number.
     *
     * @return the customer phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets the name of the user who created the customer record.
     *
     * @return the name of the user who created the customer record
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Gets the name of the last user who updated the customer record.
     *
     * @return the name of the last user who updated the customer record
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Gets the date and time the customer record was created.
     *
     * @return the date and time the customer record was created
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * Gets the date and time the customer record was last updated.
     *
     * @return the date and time the customer record was last updated
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
}
