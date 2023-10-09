package appointmentscheduler.model;

import java.sql.Timestamp;

public class Users {
    private int userId;
    private String username;
    private String password;
    private String createdBy;
    private String lastUpdatedBy;
    private Timestamp createDate;
    private Timestamp lastUpdate;

    public Users(int userId, String username, String password, String createdBy, String lastUpdatedBy, Timestamp createDate, Timestamp lastUpdate) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.createdBy = createdBy;
        this.lastUpdatedBy = lastUpdatedBy;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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
