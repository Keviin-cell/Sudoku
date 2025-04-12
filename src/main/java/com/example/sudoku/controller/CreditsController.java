package com.example.sudoku.controller;

import com.example.sudoku.view.MenuWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;

/**
 * Controller for the Credits window of the Sudoku game.
 * Allows navigation back to the main menu from the credits screen.
 *
 * Linked to the FXML layout that displays project authorship and acknowledgments.
 *
 * @author Kevin Muñoz
 * @author Leon Flor
 */
public class CreditsController {

    @FXML
    private Button menuButton;

    /**
     * Handles the event triggered by clicking the "Menu" button.
     * Closes the current credits window and opens the main menu window.
     *
     * @param event the ActionEvent triggered by the button
     */
    @FXML
    private void changeScene(ActionEvent event) {
        try {
            // Close the current stage
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            // Open the main menu window
            MenuWindow menuWindow = new MenuWindow();
            menuWindow.show();
        } catch (Exception e) {
            e.printStackTrace(); // Consider logging the error instead
        }
    }
}
