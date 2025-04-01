module org.example.ejerciciorepasocalculadora {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejerciciorepasocalculadora to javafx.fxml;
    exports org.example.ejerciciorepasocalculadora;
}