module org.example.demotest {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.demotest to javafx.fxml;
    exports org.example.demotest;
}