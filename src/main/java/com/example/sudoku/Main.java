package com.example.sudoku;

import com.example.sudoku.view.MenuStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        new MenuStage();
    }

    public static void main(String[] args) {
        launch();
    }
}