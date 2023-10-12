package appointmentscheduler.model;

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

    /**
     * Gets the appointment id.
     *
     * @return the appointment id
     */
    public int getAppointmentId() {
        return appointmentId;
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
     * Gets the user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Gets the contact id.
     *
     * @return the contact id
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Gets the title of the appointment.
     *
     * @return the title of the appointment
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the appointment.
     *
     * @param title the title of the appointment
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the appointment.
     *
     * @return the description of the appointment
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the location of the appointment.
     *
     * @return the location of the appointment
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the appointment.
     *
     * @param location the location of the appointment
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the type of the appointment.
     *
     * @return the type of the appointment
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the created by user.
     *
     * @return the user who created the appointment
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Gets the last updated by user.
     *
     * @return the user who last updated the appointment
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Gets the start date and time of the appointment.
     *
     * @return the start date and time of the appointment
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Gets the end date and time of the appointment.
     *
     * @return the end date and time of the appointment
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Gets the start time of the appointment.
     *
     * @return the start time of the appointment
     */
    public LocalTime getStartTime() {
        this.startTime = start.toLocalTime();
        return startTime;
    }

    /**
     * Gets the start date of the appointment.
     *
     * @return the start date of the appointment
     */
    public LocalDate getStartDate() {
        this.startDate = start.toLocalDate();
        return startDate;
    }

    /**
     * Gets the end time of the appointment.
     *
     * @return the end time of the appointment
     */
    public LocalTime getEndTime() {
        this.endTime = end.toLocalTime();
        return endTime;
    }

    /**
     * Gets the end date of the appointment.
     *
     * @return the end date of the appointment
     */
    public LocalDate getEndDate() {
        this.endDate = end.toLocalDate();
        return endDate;
    }

}
