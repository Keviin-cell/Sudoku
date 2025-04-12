package com.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GameWindow {

    private final Stage stage;
    private final FXMLLoader loader;

    public GameWindow() throws IOException {
        this.stage = new Stage();
        this.loader = new FXMLLoader(getClass().getResource(
                "/com/example/sudoku/fxml/game-view.fxml"
        ));
        initialize();
    }

    private void initialize() throws IOException {
        Parent root = loader.load();
        stage.setTitle("Sudoku - Game");
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

    public Object getController() {
        return loader.getController();
    }
}