package com.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Represents the Credits window of the Sudoku application.
 * This class is responsible for loading and displaying the corresponding FXML view.
 * It sets the window title, icon, and initializes the JavaFX scene.
 *
 * Typically called from {@link com.example.sudoku.controller.MenuController} or {@link com.example.sudoku.controller.CreditsController}.
 *
 * @author Kevin Muñoz
 * @author Leon Flor
 */
public class CreditsWindow {

    /** The JavaFX stage representing this window. */
    private final Stage stage;

    /**
     * Constructs and initializes the credits window.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    public CreditsWindow() throws IOException {
        this.stage = new Stage();
        initialize();
    }

    /**
     * Loads the FXML layout, sets the window title, icon, and scene.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
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

    /**
     * Displays the window on the screen.
     */
    public void show() {
        stage.show();
    }

    /**
     * Returns the internal stage object.
     *
     * @return the Stage used by this window
     */
    public Stage getStage() {
        return stage;
    }
}
