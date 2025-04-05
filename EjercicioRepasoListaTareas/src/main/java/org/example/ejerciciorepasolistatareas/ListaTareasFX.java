// Pablo Nicolás Siles Arano
// Crear una aplicación de JavaFX donde el usuario pueda:
// Añadir tareas a una lista.
// Marcar tareas como completadas.
// Eliminar tareas seleccionadas.

package org.example.ejerciciorepasolistatareas;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ListaTareasFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Lista de tareas
        ObservableList<String> tareas = FXCollections.observableArrayList();

        // Componentes
        TextField campoTarea = new TextField();
        campoTarea.setPromptText("Escribe una tarea");

        Button btnAgregar = new Button("Agregar");
        Button btnEliminar = new Button("Eliminar seleccionada");

        ListView<String> listaTareas = new ListView<>(tareas);
        listaTareas.setPrefHeight(200);

        // Eventos
        btnAgregar.setOnAction(e -> {
            String nuevaTarea = campoTarea.getText().trim();
            if (!nuevaTarea.isEmpty()) {
                tareas.add(nuevaTarea);
                campoTarea.clear();
            }
        });

        btnEliminar.setOnAction(e -> {
            String tareaSeleccionada = listaTareas.getSelectionModel().getSelectedItem();
            if (tareaSeleccionada != null) {
                tareas.remove(tareaSeleccionada);
            }
        });

        // Layout
        HBox entrada = new HBox(10, campoTarea, btnAgregar);
        entrada.setAlignment(Pos.CENTER);

        VBox root = new VBox(10, entrada, listaTareas, btnEliminar);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 20px;");

        // Escena
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Lista de Tareas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}