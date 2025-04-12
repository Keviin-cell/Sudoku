package com.example.sudoku.controller;

import com.example.sudoku.view.GameWindow;
import com.example.sudoku.view.InstructionsWindow;
import com.example.sudoku.view.CreditsWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller for the main menu of the Sudoku application.
 * Handles navigation between the main menu and other windows (Game, Instructions, Credits).
 *
 * Provides methods to handle button clicks and open corresponding windows.
 *
 * @author Kevin Muñoz
 * @author Leon Flor
 */
public class MenuController {

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnInstructions;

    @FXML
    private Button btnCredits;

    /**
     * Closes the current JavaFX stage (window) from which the event was triggered.
     *
     * @param event the ActionEvent triggered by a button click
     */
    private void closeScene(ActionEvent event) {
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace(); // Consider using a logging framework instead
        }
    }

    /**
     * Handles the "Play" button click event.
     * Closes the current menu window and opens the game window.
     *
     * @param event the ActionEvent triggered by clicking the Play button
     */
    @FXML
    void onPlayClick(ActionEvent event) {
        try {
            closeScene(event);
            GameWindow gameWindow = new GameWindow();
            gameWindow.show();
        } catch (Exception e) {
            e.printStackTrace(); // Proper error handling recommended
        }
    }

    /**
     * Handles the "Instructions" button click event.
     * Closes the current menu window and opens the instructions window.
     *
     * @param event the ActionEvent triggered by clicking the Instructions button
     */
    @FXML
    void onInstructionsClick(ActionEvent event) {
        try {
            closeScene(event);
            InstructionsWindow instructionsWindow = new InstructionsWindow();
            instructionsWindow.show();
        } catch (Exception e) {
            e.printStackTrace(); // Proper error handling recommended
        }
    }

    /**
     * Handles the "Credits" button click event.
     * Closes the current menu window and opens the credits window.
     *
     * @param event the ActionEvent triggered by clicking the Credits button
     */
    @FXML
    void onCreditsClick(ActionEvent event) {
        try {
            closeScene(event);
            CreditsWindow creditsWindow = new CreditsWindow();
            creditsWindow.show();
        } catch (Exception e) {
            e.printStackTrace(); // Proper error handling recommended
        }
    }
}
