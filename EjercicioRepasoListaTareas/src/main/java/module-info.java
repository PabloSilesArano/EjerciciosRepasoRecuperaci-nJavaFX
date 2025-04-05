module org.example.ejerciciorepasolistatareas {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejerciciorepasolistatareas to javafx.fxml;
    exports org.example.ejerciciorepasolistatareas;
}