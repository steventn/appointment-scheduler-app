package appointmentscheduler.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalTime;

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
}
