package com.lisandrojimenez.webapp.Biblioteca.util;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertUtil {

    private static AlertUtil instance;

    private AlertUtil() {

    }

    public static AlertUtil getInstance() {
        if (instance == null) {
            instance = new AlertUtil();
        }
        return instance;
    }

    public void mostrarAlertasInfo(int code) {
        System.out.println("Mostrando alerta para código: " + code); // Mensaje de depuración
        if (code == 407) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Categoría Duplicada");
            alert.setHeaderText("Categoría Duplicada");
            alert.setContentText("La categoría que intenta registrar ya existe.");
            alert.showAndWait();
        } else if (code == 408) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("DPI Duplicado");
            alert.setHeaderText("DPI Duplicado");
            alert.setContentText("El DPI ingresado ya está registrado.");
            alert.showAndWait();
        } else if (code == 409) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Préstamo Vigente");
            alert.setHeaderText("El Cliente ya tiene un Préstamo Vigente");
            alert.setContentText("El cliente ya tiene un préstamo vigente.");
            alert.showAndWait();
        } else if (code == 410) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Libro No Disponible");
            alert.setHeaderText("Libro No Disponible");
            alert.setContentText("El libro que intenta prestar no está disponible.");
            alert.showAndWait();
        }
    }
}
