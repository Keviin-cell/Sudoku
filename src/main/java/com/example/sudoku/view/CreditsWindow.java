package com.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CreditsWindow {

    private final Stage stage;

    public CreditsWindow() throws IOException {
        this.stage = new Stage();
        initialize();
    }

    private void initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/sudoku/fxml/credits-view.fxml"
        ));
        Parent root = loader.load();
        stage.setTitle("Créditos");
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