package com.example.sudoku.controller;

import com.example.sudoku.model.Board;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private Button newGameButton;

    @FXML
    private Button helpButton;

    @FXML
    private GridPane grid;

    private Board board;

    private int remainingHints = 3;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("✅ GameController activated.");

        board = new Board();

        Platform.runLater(() -> {
            board.reset();
            renderBoard(board.getValues());
        });
    }

    private void renderBoard(int[][] boardValues) {
        grid.getChildren().clear();

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                TextField cell = new TextField();

                cell.setPrefWidth(60);
                cell.setPrefHeight(60);
                cell.setAlignment(Pos.CENTER);
                cell.setFont(Font.font("Arial", 20));

                // Default editable cell style
                String defaultStyle = "-fx-background-color: white;" +
                        "-fx-border-color: black;" +
                        "-fx-border-width: 1;" +
                        "-fx-text-fill: #222;" +
                        "-fx-alignment: center;" +
                        "-fx-background-radius: 0;" +
                        "-fx-border-radius: 0;";

                cell.setStyle(defaultStyle);

                if (boardValues[row][col] != 0) {
                    cell.setText(String.valueOf(boardValues[row][col]));
                    cell.setEditable(false);
                    cell.setStyle(
                            "-fx-background-color: #e6e6e6;" +
                                    "-fx-border-color: black;" +
                                    "-fx-border-width: 1;" +
                                    "-fx-font-weight: bold;" +
                                    "-fx-text-fill: #000;" +
                                    "-fx-alignment: center;" +
                                    "-fx-background-radius: 0;" +
                                    "-fx-border-radius: 0;"
                    );
                } else {
                    final int currentRow = row;
                    final int currentCol = col;
                    cell.setOnMouseClicked(event -> {
                        System.out.println("🖱️ Clicked cell at [" + currentRow + "," + currentCol + "]");
                        cell.setStyle(cell.getStyle() + "-fx-background-color: #f0f8ff;"); // light blue focus
                    });

                    cell.textProperty().addListener((obs, oldText, newText) -> {
                        if (!newText.matches("[1-6]?") || newText.length() > 1) {
                            cell.setText(oldText);
                            return;
                        }

                        int value = newText.isEmpty() ? 0 : Integer.parseInt(newText);
                        board.setValue(currentRow, currentCol, value);

                        if (value != 0 && !board.isValid(currentRow, currentCol, value)) {
                            // Invalid input: highlight red
                            cell.setStyle(
                                    "-fx-background-color: #ffcccc;" +
                                            "-fx-border-color: #ff0000;" +
                                            "-fx-border-width: 2;" +
                                            "-fx-text-fill: #ff0000;" +
                                            "-fx-alignment: center;" +
                                            "-fx-background-radius: 0;" +
                                            "-fx-border-radius: 0;"
                            );
                            System.out.println("❌ Invalid value at [" + currentRow + "," + currentCol + "]");
                        } else {
                            // Valid input: reset to default style
                            cell.setStyle(defaultStyle);
                        }
                    });
                }

                grid.add(cell, col, row);
                if (board.isComplete()){
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("¡Juego Terminado!");
                    alerta.setHeaderText("El juego ha acabado");
                    alerta.setContentText("Felicidades. Has ganado");
                }
            }
        }
    }

    @FXML
    private void OnNewGameClick(){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación nuevo juego");
        alerta.setHeaderText("¿Estás seguro de empezar un nuevo juego?");
        alerta.setContentText("Tú progreso no será guardado.");

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

    @FXML
    private void OnHelpButtonClick(){
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
            renderBoard(board.getValues()); // Actualiza la vista
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