package com.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class InstructionsWindow {

    private final Stage stage;

    public InstructionsWindow() throws IOException {
        this.stage = new Stage();
        initialize();
    }

    private void initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/sudoku/fxml/instructions-view.fxml"
        ));
        Parent root = loader.load();
        stage.setTitle("Instrucciones");
        stage.getIcons().add(new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("/com/example/sudoku/images/icon.png")
        )));
        stage.setScene(new Scene(root));
    }

    public void show() {
        stage.show();
    }

    public Stage getStage() {
        return stage;
    }
}