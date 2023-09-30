package appointmentscheduler.main;

import appointmentscheduler.dao.UserDao;
import appointmentscheduler.helper.DBConnection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane myPane = FXMLLoader.load(getClass().getResource
                ("/LoginScreen.fxml"));
        Scene scene = new Scene(myPane);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}