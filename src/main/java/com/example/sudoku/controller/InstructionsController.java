package com.example.sudoku.controller;

import com.example.sudoku.view.MenuWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller for the Instructions window of the Sudoku game.
 * Handles the action to return to the main menu from the instructions screen.
 *
 * This class interacts with JavaFX components defined in the instructions FXML layout.
 *
 * @author Kevin Muñoz
 * @author Leon Flor
 */
public class InstructionsController {

    @FXML
    private Button menuButton;

    /**
     * Handles the event of clicking the "Menu" button.
     * Closes the current Instructions window and opens the main menu window.
     *
     * @param event the action event triggered by clicking the menu button
     */
    @FXML
    private void changeScene(ActionEvent event) {
        try {
            // Close the current window
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            // Open the menu window
            MenuWindow menuWindow = new MenuWindow();
            menuWindow.show();
        } catch (Exception e) {
            e.printStackTrace(); // Consider replacing with logging in production
        }
    }
}
