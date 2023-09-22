module steven.inventoryproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports appointmentscheduler.controller;
    opens appointmentscheduler.controller to javafx.fxml;
    exports appointmentscheduler.main;
    opens appointmentscheduler.main to javafx.fxml;
    opens appointmentscheduler.model to javafx.base;

}