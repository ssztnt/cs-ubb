module com.example.project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.project to javafx.fxml;
    exports com.example.project;
    opens com.example.project.controller to javafx.fxml;
    exports com.example.project.controller;
}
