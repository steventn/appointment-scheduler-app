package appointmentscheduler.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AppointmentController {
    @FXML
    public void exitToMainViewAction(ActionEvent event) throws IOException {
        Pane customer = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        Scene scene = new Scene(customer);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
