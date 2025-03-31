// Pablo Nicolás Siles Arano

package org.example.ejerciciorepasoexamenjavafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListaUsuariosApp extends Application {
    // Tabla donde se mostrarán los usuarios ingresados
    private TableView<Usuario> tablaUsuarios;

    // Lista observable para almacenar los usuarios
    private ObservableList<Usuario> listaUsuarios;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Introducir datos en ObservableList");

        // Crear la tabla de usuarios
        tablaUsuarios = new TableView<>();
        listaUsuarios = FXCollections.observableArrayList();
        tablaUsuarios.setItems(listaUsuarios);

        // Definir las columnas de la tabla
        TableColumn<Usuario, String> columnaNombre = new TableColumn<>("Nombre");
        columnaNombre.setCellValueFactory(c -> c.getValue().nombreProperty());

        TableColumn<Usuario, String> columnaEdad = new TableColumn<>("Edad");
        columnaEdad.setCellValueFactory(c -> c.getValue().edadProperty());

        // Agregar columnas a la tabla
        tablaUsuarios.getColumns().addAll(columnaNombre, columnaEdad);

        // Botón para agregar un nuevo usuario
        Button btnAgregar = new Button("Ingresar información");
        btnAgregar.setOnAction(e -> mostrarVentanaAgregar());

        // Botón para eliminar el usuario seleccionado de la tabla
        Button btnEliminar = new Button("Eliminar información");
        btnEliminar.setOnAction(e -> {
            Usuario seleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                listaUsuarios.remove(seleccionado);
            }
        });

        // Contenedor para los botones con espaciado de 10 píxeles
        HBox botones = new HBox(10, btnAgregar, btnEliminar);
        botones.setPadding(new Insets(10));

        // Diseño principal de la ventana con la tabla y los botones
        VBox layout = new VBox(10, tablaUsuarios, botones);
        layout.setPadding(new Insets(10));

        // Crear la escena y mostrar la ventana principal
        primaryStage.setScene(new Scene(layout, 400, 300));
        primaryStage.show();
    }

    private void mostrarVentanaAgregar() {
        // Crear una nueva ventana para la entrada de datos
        Stage ventana = new Stage();
        ventana.setTitle("Información del usuario");
        ventana.initModality(Modality.APPLICATION_MODAL);

        // Campos de entrada para el usuario
        TextField campoNombre = new TextField();
        campoNombre.setPromptText("Introduce un nombre");

        TextField campoApellido1 = new TextField();
        campoApellido1.setPromptText("Introduce el primer apellido");

        TextField campoApellido2 = new TextField();
        campoApellido2.setPromptText("Introduce el segundo apellido");

        TextField campoEdad = new TextField();
        campoEdad.setPromptText("Introduce la edad");

        // Botón para insertar un nuevo usuario en la tabla
        Button btnInsertar = new Button("Insertar");
        btnInsertar.setOnAction(e -> {
            // Verifica que el nombre y la edad no estén vacíos antes de agregar el usuario
            if (!campoNombre.getText().isEmpty() && !campoEdad.getText().isEmpty()) {
                Usuario usuario = new Usuario(
                        campoNombre.getText(),
                        campoApellido1.getText(),
                        campoApellido2.getText(),
                        campoEdad.getText()
                );
                listaUsuarios.add(usuario);
                ventana.close();
            }
        });

        // Botón para cancelar la operación y cerrar la ventana emergente
        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setOnAction(e -> ventana.close());

        // Diseño de la ventana emergente con etiquetas y campos de entrada
        VBox layout = new VBox(10,
                new Label("Por favor, inserta un nombre y una edad"),
                new Label("Nombre:"), campoNombre,
                new Label("Apellido1:"), campoApellido1,
                new Label("Apellido2:"), campoApellido2,
                new Label("Edad:"), campoEdad,
                new HBox(10, btnInsertar, btnCancelar)
        );
        layout.setPadding(new Insets(10));

        // Crear la escena y mostrar la ventana emergente
        ventana.setScene(new Scene(layout, 300, 250));
        ventana.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
