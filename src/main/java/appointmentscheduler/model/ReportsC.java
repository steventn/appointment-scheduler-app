package appointmentscheduler.model;

public class ReportsC {
    private String customerId;
    private String month;
    private int averageDuration;

    public ReportsC(String customerId, String month, int averageDuration) {
        this.customerId = customerId;
        this.month = month;
        this.averageDuration = averageDuration;
    }

    /**
     * Gets the customer id.
     *
     * @return the customer id
     */
    public String getCustomerId() {
        return customerId;
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
     * Gets the average appointment duration.
     *
     * @return the average appointment duration
     */
    public int getAverageDuration() {
        return averageDuration;
    }
}
