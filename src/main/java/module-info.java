/**
 * Main module definition for the Sudoku application.
 * <p>
 * This module declares dependencies on JavaFX libraries
 * and opens the controller package for FXML reflection.
 * </p>
 */
module com.example.sudoku {
    // JavaFX core modules required for UI and FXML integration
    requires javafx.controls;
    requires javafx.fxml;

    // Opens controller package for JavaFX to inject FXML elements
    opens com.example.sudoku.controller to javafx.fxml;

    // Exports the main application entry point package
    exports com.example.sudoku;
}