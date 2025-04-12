package com.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Represents the main game window of the Sudoku application.
 * Loads the corresponding FXML file and sets the scene, icon, and window title.
 * Provides access to the JavaFX controller and internal stage.
 *
 * This class is typically called from the {@link com.example.sudoku.controller.MenuController}.
 *
 * @author Kevin Muñoz
 * @author Leon Flor
 */
public class GameWindow {

    /** JavaFX stage representing the game window. */
    private final Stage stage;

    /** FXMLLoader to load and access the game FXML and controller. */
    private final FXMLLoader loader;

    /**
     * Constructs and initializes the game window.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    public GameWindow() throws IOException {
        this.stage = new Stage();
        this.loader = new FXMLLoader(getClass().getResource(
                "/com/example/sudoku/fxml/game-view.fxml"
        ));
        initialize();
    }

    /**
     * Loads the FXML layout, sets the title, icon, and attaches the scene to the stage.
     *
     * @throws IOException if the FXML layout fails to load
     */
    private void initialize() throws IOException {
        Parent root = loader.load();

        stage.setTitle("Sudoku - Game");
        stage.getIcons().add(new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("/com/example/sudoku/images/icon.png")
        )));
        stage.setScene(new Scene(root));
    }

    /**
     * Displays the game window.
     */
    public void show() {
        stage.show();
    }

    /**
     * Returns the internal JavaFX stage.
     *
     * @return the game window stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Returns the controller associated with the loaded FXML.
     * Can be cast to {@code GameController} for interaction with the logic.
     *
     * @return the controller instance linked to the FXML
     */
    public Object getController() {
        return loader.getController();
    }
}
