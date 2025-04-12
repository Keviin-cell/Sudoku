package com.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Represents the main menu window of the Sudoku application.
 * Loads the menu view from FXML, sets up the stage, and displays the interface.
 *
 * This is the entry point for users, allowing navigation to the game, instructions, or credits.
 * It is typically the first window shown when the application starts.
 *
 * @author Kevin Muñoz
 * @author Leon Flor
 */
public class MenuWindow {

    /** JavaFX stage representing the main menu window. */
    private final Stage stage;

    /**
     * Constructs and initializes the menu window.
     *
     * @throws IOException if the FXML layout fails to load
     */
    public MenuWindow() throws IOException {
        this.stage = new Stage();
        initialize();
    }

    /**
     * Loads the FXML layout for the menu, sets the window title and icon, and creates the scene.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    private void initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/sudoku/fxml/menu-view.fxml"
        ));
        Parent root = loader.load();

        stage.setTitle("Sudoku");
        stage.getIcons().add(new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("/com/example/sudoku/images/icon.png")
        )));
        stage.setScene(new Scene(root));
    }

    /**
     * Displays the menu window on screen.
     */
    public void show() {
        stage.show();
    }

    /**
     * Returns the JavaFX stage associated with the menu.
     *
     * @return the main menu stage
     */
    public Stage getStage() {
        return stage;
    }
}
