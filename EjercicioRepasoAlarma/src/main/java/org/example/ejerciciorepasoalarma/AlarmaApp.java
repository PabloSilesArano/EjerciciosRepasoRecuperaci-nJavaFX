// Pablo Nicolás Siles Arano
// Desarrolla una aplicación de alarma utilizando JavaFX. La aplicación debe permitir al usuario configurar una hora específica y notificarle cuando se alcance dicha hora.

package org.example.ejerciciorepasoalarma;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class AlarmaApp extends Application {
    private LocalTime alarma;
    private Label estadoAlarma;
    private Timer timer = new Timer(true);

    @Override
    public void start(Stage primaryStage) {
        estadoAlarma = new Label("No hay alarma programada");

        Spinner<Integer> horaSpinner = new Spinner<>(0, 23, LocalTime.now().getHour());
        Spinner<Integer> minutoSpinner = new Spinner<>(0, 59, LocalTime.now().getMinute());

        Button btnActivar = new Button("Activar Alarma");
        btnActivar.setOnAction(e -> {
            alarma = LocalTime.of(horaSpinner.getValue(), minutoSpinner.getValue());
            estadoAlarma.setText("Alarma programada a las " + alarma);
        });

        VBox layout = new VBox(10, horaSpinner, minutoSpinner, btnActivar, estadoAlarma);
        layout.setAlignment(Pos.CENTER);
        primaryStage.setScene(new Scene(layout, 300, 200));
        primaryStage.setTitle("Alarma con JavaFX");
        primaryStage.show();

        iniciarVerificacion();
    }

    private void iniciarVerificacion() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (alarma != null && LocalTime.now().getHour() == alarma.getHour() &&
                        LocalTime.now().getMinute() == alarma.getMinute()) {
                    System.out.println("¡Alarma activada!");
                }
            }
        }, 0, 1000);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
