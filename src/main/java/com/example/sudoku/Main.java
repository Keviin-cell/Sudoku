package com.example.sudoku;

import com.example.sudoku.view.MenuWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        MenuWindow menu = new MenuWindow();
        menu.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}