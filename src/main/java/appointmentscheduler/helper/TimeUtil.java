package appointmentscheduler.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalTime;
import java.time.Month;

public class TimeUtil {
    public static ObservableList<String> generateBusinessHours() {
        ObservableList<String> businessHours = FXCollections.observableArrayList();
        LocalTime firstTime = LocalTime.MIN.plusHours(8);
        LocalTime lastTime = LocalTime.MAX.minusHours(1).minusMinutes(45);

        while (!firstTime.isAfter(lastTime)) {
            businessHours.add(String.valueOf(firstTime));
            firstTime = firstTime.plusMinutes(15);
        }

        return businessHours;
    }

    public static String getMonthName(int monthNumber) {
        if (monthNumber < 1 || monthNumber > 12) {
            throw new IllegalArgumentException("Invalid month number: " + monthNumber);
        }
        Month month = Month.of(monthNumber);
        String monthName = month.name();
        return monthName.substring(0, 1) + monthName.substring(1).toLowerCase();
    }
}
