module org.example.ejerciciorepasoregistroestudiantesfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejerciciorepasoregistroestudiantesfx to javafx.fxml;
    exports org.example.ejerciciorepasoregistroestudiantesfx;
}