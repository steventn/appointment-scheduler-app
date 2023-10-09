package appointmentscheduler.helper;

import javafx.scene.control.Alert;

import java.util.Locale;
import java.util.ResourceBundle;

public class ErrorUtil {
    private final ResourceBundle messages;

    public ErrorUtil() {
        Locale userLocale = Locale.getDefault();
        this.messages = ResourceBundle.getBundle("error_message", userLocale);
    }

    public void displayAlert(String titleKey, String headerTextKey, String contentTextKey) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(this.messages.getString(titleKey));
        alert.setHeaderText(this.messages.getString(headerTextKey));
        alert.setContentText(this.messages.getString(contentTextKey));

        alert.showAndWait();
    }
}
