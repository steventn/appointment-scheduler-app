package appointmentscheduler.model;

public class Reports {
    private String month;
    private String type;
    private int totalAppointments;

    public Reports(String month, String type, int totalAppointments) {
        this.month = month;
        this.type = type;
        this.totalAppointments = totalAppointments;
    }

    public String getMonth() {
        return month;
    }

    public String getType() {
        return type;
    }

    public int getTotalAppointments() {
        return totalAppointments;
    }
}
