package appointmentscheduler.model;

public class ReportsB {
    private String month;
    private String type;
    private int totalAppointments;

    public ReportsB(String month, String type, int totalAppointments) {
        this.month = month;
        this.type = type;
        this.totalAppointments = totalAppointments;
    }

    /**
     * Gets the month name.
     *
     * @return the month name
     */
    public String getMonth() {
        return month;
    }

    /**
     * Gets the appointment type.
     *
     * @return the appointment type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the total number of appointments for Report B.
     *
     * @return the total number of appointments for Report B
     */
    public int getTotalAppointments() {
        return totalAppointments;
    }
}
