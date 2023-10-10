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

    public String getCustomerId() {
        return customerId;
    }

    public String getMonth() {
        return month;
    }

    public int getAverageDuration() {
        return averageDuration;
    }
}
