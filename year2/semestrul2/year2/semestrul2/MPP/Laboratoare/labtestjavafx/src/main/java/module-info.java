module org.example.labtestjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.labtestjavafx to javafx.fxml;
    exports org.example.labtestjavafx;
}