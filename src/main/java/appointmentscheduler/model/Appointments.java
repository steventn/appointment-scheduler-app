package appointmentscheduler.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Appointments {
    private int appointmentId;
    private int customerId;
    private int userId;
    private int contactId;
    private String title;
    private String description;
    private String location;
    private String type;
    private String createdBy;
    private String lastUpdatedBy;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalTime startTime;
    private LocalDate startDate;
    private LocalTime endTime;
    private LocalDate endDate;



    public Appointments(int appointmentId, int customerId, int userId, int contactId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end) {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
    }

    public Appointments(int appointmentId, int customerId, int userId, int contactId, String title, String description, String location, String type, String createdBy, String lastUpdatedBy, LocalDateTime start, LocalDateTime end) {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.createdBy = createdBy;
        this.lastUpdatedBy = lastUpdatedBy;
        this.start = start;
        this.end = end;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public LocalTime getStartTime() {
        this.startTime = start.toLocalTime();
        return startTime;
    }

    public LocalDate getStartDate() {
        this.startDate = start.toLocalDate();
        return startDate;
    }

    public LocalTime getEndTime() {
        this.endTime = end.toLocalTime();
        return endTime;
    }

    public LocalDate getEndDate() {
        this.endDate = end.toLocalDate();
        return endDate;
    }
}
