module org.example.ejerciciorepasoalarma {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejerciciorepasoalarma to javafx.fxml;
    exports org.example.ejerciciorepasoalarma;
}