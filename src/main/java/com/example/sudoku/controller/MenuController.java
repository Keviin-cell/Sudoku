package com.example.sudoku.controller;

import com.example.sudoku.view.GameWindow;
import com.example.sudoku.view.InstructionsWindow;
import com.example.sudoku.view.CreditsWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private Button btnPlay;
    @FXML
    private Button btnInstructions;
    @FXML
    private Button btnCredits;


    private void closeScene(ActionEvent event) {
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onPlayClick(ActionEvent event) {
        try {
            closeScene(event);
            GameWindow gameWindow = new GameWindow();
            gameWindow.show();
        } catch (Exception e) {
            e.printStackTrace(); // Add proper error handling/logging
        }
    }

    @FXML
    void onInstructionsClick(ActionEvent event) {
        try {
            closeScene(event);
            InstructionsWindow instructionsWindow = new InstructionsWindow();
            instructionsWindow.show();
        } catch (Exception e) {
            e.printStackTrace(); // Add proper error handling/logging
        }
    }

    @FXML
    void onCreditsClick(ActionEvent event) {
        try {
            closeScene(event);
            CreditsWindow creditsWindow = new CreditsWindow();
            creditsWindow.show();
        } catch (Exception e) {
            e.printStackTrace(); // Add proper error handling/logging
        }
    }
}