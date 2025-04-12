package com.example.sudoku.controller;

import com.example.sudoku.view.MenuWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;

public class CreditsController {

    @FXML
    private Button menuButton;

    @FXML
    private void changeScene(ActionEvent event) {
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            MenuWindow menuWindow = new MenuWindow();
            menuWindow.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}