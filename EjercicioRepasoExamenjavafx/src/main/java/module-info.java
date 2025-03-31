module org.example.ejerciciorepasoexamenjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejerciciorepasoexamenjavafx to javafx.fxml;
    exports org.example.ejerciciorepasoexamenjavafx;
}