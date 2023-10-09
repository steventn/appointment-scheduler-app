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

    public int getCustomerId() {
        return customerId;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public String getDivision() {
        return division;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
}
