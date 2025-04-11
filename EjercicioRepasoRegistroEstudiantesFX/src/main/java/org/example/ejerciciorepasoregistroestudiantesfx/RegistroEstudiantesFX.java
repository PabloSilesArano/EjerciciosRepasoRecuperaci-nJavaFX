// Pablo Nicolás Siles Arano
// Crear una aplicación en JavaFX que permita ingresar estudiantes con su nombre, edad y carrera. La información ingresada debe mostrarse en una tabla (TableView). El usuario podrá:
// Insertar nuevos estudiantes.
// Ver la lista de todos los estudiantes registrados.

package org.example.ejerciciorepasoregistroestudiantesfx;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class RegistroEstudiantesFX extends Application {

    // Lista observable para la tabla
    private ObservableList<Estudiante> listaEstudiantes = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        // Campos de entrada
        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nombre");

        TextField txtEdad = new TextField();
        txtEdad.setPromptText("Edad");

        ComboBox<String> cmbCarrera = new ComboBox<>();
        cmbCarrera.getItems().addAll("Ingeniería", "Medicina", "Derecho", "Arquitectura");
        cmbCarrera.setPromptText("Selecciona carrera");

        Button btnRegistrar = new Button("Registrar");

        // Tabla de estudiantes
        TableView<Estudiante> tabla = new TableView<>(listaEstudiantes);
        tabla.setPrefHeight(200);

        TableColumn<Estudiante, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(data -> data.getValue().nombreProperty());

        TableColumn<Estudiante, Integer> colEdad = new TableColumn<>("Edad");
        colEdad.setCellValueFactory(data -> data.getValue().edadProperty().asObject());

        TableColumn<Estudiante, String> colCarrera = new TableColumn<>("Carrera");
        colCarrera.setCellValueFactory(data -> data.getValue().carreraProperty());

        tabla.getColumns().addAll(colNombre, colEdad, colCarrera);

        // Evento del botón
        btnRegistrar.setOnAction(e -> {
            String nombre = txtNombre.getText().trim();
            String edadStr = txtEdad.getText().trim();
            String carrera = cmbCarrera.getValue();

            if (nombre.isEmpty() || edadStr.isEmpty() || carrera == null) {
                mostrarAlerta("Campos incompletos", "Por favor, completa todos los campos.");
                return;
            }

            try {
                int edad = Integer.parseInt(edadStr);
                Estudiante nuevo = new Estudiante(nombre, edad, carrera);
                listaEstudiantes.add(nuevo);
                txtNombre.clear();
                txtEdad.clear();
                cmbCarrera.getSelectionModel().clearSelection();
            } catch (NumberFormatException ex) {
                mostrarAlerta("Edad inválida", "La edad debe ser un número entero.");
            }
        });

        // Layout
        HBox campos = new HBox(10, txtNombre, txtEdad, cmbCarrera, btnRegistrar);
        campos.setAlignment(Pos.CENTER);

        VBox root = new VBox(15, campos, tabla);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        // Escena
        Scene scene = new Scene(root, 600, 350);
        primaryStage.setTitle("Registro de Estudiantes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Clase Estudiante con propiedades
    public static class Estudiante {
        private final SimpleStringProperty nombre;
        private final SimpleIntegerProperty edad;
        private final SimpleStringProperty carrera;

        public Estudiante(String nombre, int edad, String carrera) {
            this.nombre = new SimpleStringProperty(nombre);
            this.edad = new SimpleIntegerProperty(edad);
            this.carrera = new SimpleStringProperty(carrera);
        }

        public SimpleStringProperty nombreProperty() { return nombre; }
        public SimpleIntegerProperty edadProperty() { return edad; }
        public SimpleStringProperty carreraProperty() { return carrera; }
    }

    // Método para mostrar alertas
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
