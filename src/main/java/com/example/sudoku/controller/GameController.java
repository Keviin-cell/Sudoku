package com.example.sudoku.controller;

import com.example.sudoku.model.Board;
import com.example.sudoku.view.MenuWindow;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller for the main game window of the Sudoku application.
 * Handles rendering the board, validating input, managing hint usage, and resetting the game.
 * This class is linked to the FXML layout for the game interface.
 *
 * @author Kevin Muñoz
 * @author Leon Flor
 */
public class GameController implements Initializable {

    @FXML
    private Button newGameButton, menuButton, helpButton;

    @FXML
    private GridPane grid;

    @FXML
    private Label remain;

    /** The game board instance used for logic and state management. */
    private Board board;

    /** Number of remaining hints available to the user (default is 3). */
    private int remainingHints = 3;

    /**
     * Initializes the game controller.
     * Called automatically when the FXML is loaded.
     *
     * @param location  the location used to resolve relative paths
     * @param resources the resources used to localize the root object
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("✅ GameController activated.");

        board = new Board();

        Platform.runLater(() -> {
            board.reset();
            renderBoard(board.getValues());
        });
    }

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

    /**
     * Renders the current board state into the GridPane.
     * Sets styles, listeners, and handles input validation and game completion.
     *
     * @param boardValues a 2D array representing the current values of the board
     */
    private void renderBoard(int[][] boardValues) {
        grid.getChildren().clear();

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                TextField cell = new TextField();
                cell.setPrefWidth(60);
                cell.setPrefHeight(60);
                cell.setAlignment(Pos.CENTER);
                cell.setFont(Font.font("Arial", 20));

                // Base style
                StringBuilder style = new StringBuilder("-fx-background-color: white; -fx-text-fill: #222; -fx-alignment: center;");

                // Bordered blocks (2x3) with thicker lines
                style.append("-fx-border-color: black;");
                style.append("-fx-border-width: ");
                style.append((row % 2 == 0 ? "2" : "0.5")).append(" "); // top
                style.append((col == 5 ? "2" : "0.5")).append(" ");      // right
                style.append((row == 5 ? "2" : "0.5")).append(" ");      // bottom
                style.append((col % 3 == 0 ? "2" : "0.5")).append(";");  // left

                style.append("-fx-background-radius: 0; -fx-border-radius: 0;");
                cell.setStyle(style.toString());


                if (boardValues[row][col] != 0) {
                    cell.setText(String.valueOf(boardValues[row][col]));
                    cell.setEditable(false);
                    cell.setStyle(style + "-fx-background-color: #e6e6e6; -fx-font-weight: bold; -fx-text-fill: #000;");
                } else {
                    final int currentRow = row;
                    final int currentCol = col;



                    cell.setOnMouseClicked(event -> cell.setStyle(style + "-fx-background-color: #f0f8ff;"));
                    remain.setText(""+ board.remainingNum(boardValues));

                    cell.textProperty().addListener((obs, oldText, newText) -> {
                        if (!newText.matches("[1-6]?") || newText.length() > 1) {
                            cell.setText(oldText);
                            return;
                        }

                        int value = newText.isEmpty() ? 0 : Integer.parseInt(newText);


                        if (board.isComplete()) {
                            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                            alerta.setTitle("¡Juego Terminado!");
                            alerta.setHeaderText("El juego ha acabado");
                            alerta.setContentText("Felicidades. Has ganado");
                            alerta.showAndWait();
                            Platform.exit();
                        }

                        if (value != 0 && !board.isValid(currentRow, currentCol, value)) {
                            cell.setStyle(style + "-fx-background-color: #ffcccc; -fx-border-color: #ff0000; -fx-text-fill: #ff0000; -fx-border-width: 2;");
                        } else {
                            cell.setStyle(style.toString());
                        }
                        remain.setText(""+ board.remainingNum(boardValues));
                    });
                }

                grid.add(cell, col, row);
            }
        }
        remain.setText(""+ board.remainingNum(boardValues));
    }

    /**
     * Handles the "New Game" button click.
     * Prompts the user for confirmation and resets the board if confirmed.
     */
    @FXML
    private void OnNewGameClick() {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación nuevo juego");
        alerta.setHeaderText("¿Estás seguro de empezar un nuevo juego?");
        alerta.setContentText("Tu progreso no será guardado.");

        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            board = new Board();
            remainingHints = 3;

            Platform.runLater(() -> {
                board.reset();
                renderBoard(board.getValues());
            });

            System.out.println("Confirmado por el usuario");
        } else {
            System.out.println("Acción cancelada");
        }
    }

    /**
     * Handles the "Help" button click.
     * Reveals a single valid value if available and updates the board.
     * Limits hint usage to a maximum of 3.
     */
    @FXML
    private void OnHelpButtonClick() {
        if (remainingHints == 0) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Ayuda agotada");
            alerta.setHeaderText("Ya no puedes pedir más ayuda");
            alerta.setContentText("Has usado las 3 ayudas disponibles.");
            alerta.showAndWait();
            return;
        }

        boolean revelada = board.revealOneCell();
        if (revelada) {
            renderBoard(board.getValues());
            remainingHints--;
            System.out.println("Quedan " + remainingHints + " ayudas.");
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Tablero completo");
            alerta.setHeaderText("No se pudo revelar ninguna celda");
            alerta.setContentText("Ya no hay más celdas vacías.");
            alerta.showAndWait();
        }
    }
}
