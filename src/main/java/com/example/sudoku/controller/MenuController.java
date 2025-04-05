package com.example.sudoku.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    /**
     * Cambia la escena actual a la vista correspondiente según el botón presionado.
     *
     * <p>El método obtiene el botón que activó el evento, extrae su ID y lo utiliza para
     * determinar qué archivo FXML debe cargarse.</p>
     *
     * @param event Evento de acción generado al hacer clic en un botón del menú.
     */
    @FXML
    public void changeScene(ActionEvent event) {
        try {
            // Obtener ID del botón presionado
            String buttonId = ((Button) event.getSource()).getId();

            // Generar el nombre del archivo FXML
            String fxmlFile = buttonId.replace("Button", "-view.fxml");

            // Cargar el archivo FXML desde el paquete correcto
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sudoku/fxml/" + fxmlFile));
            Parent newScene = loader.load();

            // Cambiar la escena en la misma ventana
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(newScene));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
