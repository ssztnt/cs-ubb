module org.example.lab3_java1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.lab3_java1 to javafx.fxml;
    exports org.example.lab3_java1;
}