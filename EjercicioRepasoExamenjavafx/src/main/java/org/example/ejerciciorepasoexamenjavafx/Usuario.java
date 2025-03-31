// Pablo Nicolás Siles Arano

package org.example.ejerciciorepasoexamenjavafx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {

    // Propiedades observables para facilitar la actualización en la TableView
    private final StringProperty nombre;
    private final StringProperty apellido1;
    private final StringProperty apellido2;
    private final StringProperty edad;

    public Usuario(String nombre, String apellido1, String apellido2, String edad) {
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido1 = new SimpleStringProperty(apellido1);
        this.apellido2 = new SimpleStringProperty(apellido2);
        this.edad = new SimpleStringProperty(edad);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public StringProperty edadProperty() {
        return edad;
    }

    // Métodos opcionales si necesitas acceder directamente a los valores
    public String getNombre() {
        return nombre.get();
    }

    public String getEdad() {
        return edad.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public void setEdad(String edad) {
        this.edad.set(edad);
    }
}
