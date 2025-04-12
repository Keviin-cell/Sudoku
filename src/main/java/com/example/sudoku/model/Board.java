package com.example.sudoku.model;

import java.util.*;

/**
 * Represents a 6x6 Sudoku board with logic for generation, validation, and gameplay operations.
 * Handles full solution generation, clearing cells for the puzzle, input validation, and player assistance.
 *
 * @author Kevin Muñoz
 * @author Leon Flor
 */
public class Board {

    /** Size of the Sudoku board (6x6). */
    private final int size = 6;

    /** 2D array of Cell objects representing the board state. */
    private Cell[][] sudoku;

    /** Stores the fully solved board (used for validation or hint assistance). */
    private int[][] solution;

    /**
     * Helper class to represent a row-column position on the board.
     */
    private static class Position {
        int row, col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    /**
     * Default constructor.
     */
    public Board() {
        // Initialized via reset or generateBoard
    }

    /**
     * Initializes the board with a valid complete solution using backtracking.
     */
    public void generateBoard() {
        sudoku = new Cell[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sudoku[i][j] = new Cell(true, false);
                sudoku[i][j].setValue(0);
            }
        }

        solve(0, 0);
    }

    /**
     * Removes a specific number of cells from the board to create the puzzle.
     * The remaining cells retain their correct solution values.
     *
     * @param count Number of cells to clear randomly.
     */
    public void clearCells(int count) {
        List<Position> positions = new ArrayList<>();

        // El ciclo for para obtener las posiciones del sudoku y posteriormente limpiarlas de forma aleatoria
        // no obtendrá las primeras dos filas, es decir que estas quedarán intactas
        for (int i = 2; i < size; i++)
            for (int j = 0; j < size; j++)
                positions.add(new Position(i, j));

        Collections.shuffle(positions);

        for (int i = 0; i < count && i < positions.size(); i++) {
            Position pos = positions.get(i);
            sudoku[pos.row][pos.col].setValue(0);
            sudoku[pos.row][pos.col].setEditable(true);
        }
    }

    /**
     * Retrieves the current values on the board.
     *
     * @return A 2D array of integers representing the values of all cells.
     */
    public int[][] getValues() {
        int[][] values = new int[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                values[i][j] = sudoku[i][j].getValue();

        return values;
    }

    /**
     * Sets a value into the board at the specified position if the cell is editable.
     *
     * @param row    Row index.
     * @param col    Column index.
     * @param value  Value to assign to the cell.
     */
    public void setValue(int row, int col, int value) {
        if (sudoku[row][col].isEditable()) {
            sudoku[row][col].setValue(value);
        }
    }

    /**
     * Saves the current board state as the correct solution (used for hints).
     */
    private int[][] saveSolution() {
        solution = new int[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                solution[i][j] = sudoku[i][j].getValue();
            }
        }
        return solution;
    }

    /**
     * Reveals a single valid value in a random empty cell by attempting valid options.
     * Does not use the original solution, only random valid values based on current state.
     *
     * @return true if a cell was revealed, false if no valid cell could be found.
     */
    public boolean revealOneCell() {
        List<Position> vacias = new ArrayList<>();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (sudoku[row][col].getValue() == 0) {
                    vacias.add(new Position(row, col));
                }
            }
        }

        if (vacias.isEmpty()) return false;

        Collections.shuffle(vacias);

        for (Position p : vacias) {
            List<Integer> options = Arrays.asList(1, 2, 3, 4, 5, 6);
            Collections.shuffle(options);

            for (int value : options) {
                if (isValid(p.row, p.col, value)) {
                    sudoku[p.row][p.col].setValue(value);
                    sudoku[p.row][p.col].setEditable(false);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Verifies if the current board is completely filled and valid.
     *
     * @return true if the board is complete and correct, false otherwise.
     */
    public boolean isComplete() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                int value = sudoku[i][j].getValue();
                if (value == 0 || !isValid(i, j, value)) {
                    return false;
                }
            }

        return true;
    }

    /**
     * Resets the board by generating a new solution and clearing random cells.
     * Default number of cleared cells: 14.
     */
    public void reset() {
        generateBoard();
        clearCells(14);
    }

    /**
     * Solves the board completely using recursive backtracking.
     *
     * @param row Starting row index.
     * @param col Starting column index.
     * @return true if a solution was found, false otherwise.
     */
    public boolean solve(int row, int col) {
        if (row == size) return true;

        int nextRow = (col == size - 1) ? row + 1 : row;
        int nextCol = (col + 1) % size;

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Collections.shuffle(numbers);

        for (int num : numbers) {
            if (isValid(row, col, num)) {
                sudoku[row][col].setValue(num);

                if (solve(nextRow, nextCol)) return true;

                sudoku[row][col].setValue(0); // Backtrack
            }
        }

        return false;
    }

    /**
     * Validates if placing a specific value in a cell is allowed by Sudoku rules:
     * - No duplicates in the row
     * - No duplicates in the column
     * - No duplicates in the 2x3 region
     *
     * @param row   Row index.
     * @param col   Column index.
     * @param value Value to validate.
     * @return true if the value is valid, false otherwise.
     */
    public boolean isValid(int row, int col, int value) {
        // Row check
        for (int j = 0; j < size; j++)
            if (j != col && sudoku[row][j].getValue() == value)
                return false;

        // Column check
        for (int i = 0; i < size; i++)
            if (i != row && sudoku[i][col].getValue() == value)
                return false;

        // 2x3 region check
        int startRow = (row / 2) * 2;
        int startCol = (col / 3) * 3;

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 3; j++) {
                int r = startRow + i;
                int c = startCol + j;

                if ((r != row || c != col) && sudoku[r][c].getValue() == value)
                    return false;
            }

        return true;
    }

    //
    public int remainingNum(int num) {
        int count = 0;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (sudoku[row][col].getValue() == 4) {
                    count++;
                }
            }
        }

        // Suponemos que debe haber 6 cuatros (1 por fila, columna, y región)
        return 6 - count;
    }


}
