package com.example.sudoku.controller;

import com.example.sudoku.model.Board;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private GridPane grid;

    private Board board;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("✅ GameController activado.");

        board = new Board();

        // Usamos Platform.runLater para evitar problemas con JavaFX en M1
        Platform.runLater(() -> {
            board.reiniciar(); // Pregunta por dificultad y genera tablero
            renderizarTablero(board.getValores());
        });
    }

    private void renderizarTablero(int[][] tablero) {
        grid.getChildren().clear();

        for (int fila = 0; fila < 6; fila++) {
            for (int columna = 0; columna < 6; columna++) {
                TextField celda = new TextField();

                celda.setPrefWidth(60);
                celda.setPrefHeight(60);
                celda.setAlignment(Pos.CENTER);
                celda.setFont(Font.font("Arial", 20));

                // Estilo base: sin bordes redondeados, borde negro fino, fondo blanco
                celda.setStyle(
                        "-fx-background-color: white;" +
                                "-fx-border-color: black;" +
                                "-fx-border-width: 1;" +
                                "-fx-text-fill: #222;" +
                                "-fx-alignment: center;" +
                                "-fx-background-radius: 0;" +
                                "-fx-border-radius: 0;"
                );

                if (tablero[fila][columna] != 0) {
                    celda.setText(String.valueOf(tablero[fila][columna]));
                    celda.setEditable(false);
                    celda.setStyle(
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
                    celda.textProperty().addListener((obs, oldText, newText) -> {
                        if (!newText.matches("[1-6]?")) {
                            celda.setText(oldText);
                        }
                    });
                }

                grid.add(celda, columna, fila);
            }
        }
    }
}