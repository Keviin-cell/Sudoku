package com.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuStage extends Stage {

    public MenuStage() throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/sudoku/fxml/menu-view.fxml"));
        Parent root = loader.load();
        setTitle("Sudoku");

        getIcons().add(new Image(getClass().getResourceAsStream("/com/example/sudoku/images/ICONO SUDOKU VENTANA.png")));
        Scene scene = new Scene(root);
        setScene(scene);
        show();
    }


}

