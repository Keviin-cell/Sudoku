package com.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Represents the Instructions window of the Sudoku application.
 * Loads the corresponding FXML layout and displays game instructions to the user.
 *
 * This class is typically invoked from the {@link com.example.sudoku.controller.MenuController}
 * or returned to from {@link com.example.sudoku.controller.InstructionsController}.
 *
 * It initializes its own {@link Stage}, sets an icon and title, and displays the view.
 *
 * @author Kevin Muñoz
 * @author Leon Flor
 */
public class InstructionsWindow {

    /** JavaFX Stage representing the instructions window. */
    private final Stage stage;

    /**
     * Constructs and initializes the instructions window.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    public InstructionsWindow() throws IOException {
        this.stage = new Stage();
        initialize();
    }

    /**
     * Loads the FXML layout, sets the stage title and icon, and creates the scene.
     *
     * @throws IOException if the FXML layout fails to load
     */
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

    /**
     * Displays the instructions window.
     */
    public void show() {
        stage.show();
    }

    /**
     * Returns the JavaFX stage associated with this window.
     *
     * @return the stage of the instructions window
     */
    public Stage getStage() {
        return stage;
    }
}
