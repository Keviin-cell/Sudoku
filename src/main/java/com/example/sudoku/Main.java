package com.example.sudoku;

import com.example.sudoku.view.MenuWindow;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Entry point of the Sudoku application.
 * This class launches the JavaFX application and displays the main menu window.
 *
 * It extends {@link javafx.application.Application} and overrides the {@code start} method.
 *
 * @author Kevin Muñoz
 * @author Leon Flor
 */
public class Main extends Application {

    /**
     * Starts the JavaFX application and opens the main menu window.
     *
     * @param primaryStage the primary stage for this application (not used directly)
     * @throws Exception if the menu window fails to load
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        MenuWindow menu = new MenuWindow();
        menu.show();
    }

    /**
     * Main method. Launches the JavaFX application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
