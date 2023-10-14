package appointmentscheduler.helper;

import javafx.scene.control.Alert;

import java.util.Locale;
import java.util.ResourceBundle;

public class AlertUtil {
    private ResourceBundle messages;
    private Locale userLocale = Locale.getDefault();

    public AlertUtil() {
    }

    public void displayErrorAlert(String titleKey, String headerTextKey, String contentTextKey) {
        this.messages = ResourceBundle.getBundle("error_message", userLocale);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(this.messages.getString(titleKey));
        alert.setHeaderText(this.messages.getString(headerTextKey));
        alert.setContentText(this.messages.getString(contentTextKey));

        alert.showAndWait();
    }

    public void displaySuccessAlert(String titleKey, String headerTextKey, String contentTextKey) {
        this.messages = ResourceBundle.getBundle("messages", userLocale);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(this.messages.getString(titleKey));
        alert.setHeaderText(this.messages.getString(headerTextKey));
        alert.setContentText(this.messages.getString(contentTextKey));

        alert.showAndWait();
    }

    public void displaySuccessAlert(String titleKey, String headerTextKey, String contentTextKey, String extraInformation) {
        this.messages = ResourceBundle.getBundle("messages", userLocale);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(this.messages.getString(titleKey));
        alert.setHeaderText(this.messages.getString(headerTextKey));
        alert.setContentText(this.messages.getString(contentTextKey) + extraInformation);

        alert.showAndWait();
    }
}
