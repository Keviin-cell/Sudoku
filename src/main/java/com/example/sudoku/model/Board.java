package com.example.sudoku.model;

import java.util.*;

/**
 * Represents a 6x6 Sudoku board with logic for generation, validation and manipulation.
 */
public class Board {

    // --- Constants and Fields ---

    private final int size = 6;
    private Cell[][] sudoku;

    // --- Inner Classes ---

    /**
     * Helper class for cell positions.
     */
    private static class Position {
        int row, col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    // --- Constructor ---

    public Board() {
        // Intentionally left blank
    }

    // --- Public API ---

    /**
     * Initializes the board with a valid complete solution.
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
     * Removes values from the board to create a playable puzzle.
     *
     * @param count Number of cells to clear.
     */
    public void clearCells(int count) {
        List<Position> positions = new ArrayList<>();

        for (int i = 0; i < size; i++)
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
     * Retrieves the board's current values.
     *
     * @return 2D array of integers representing cell values.
     */
    public int[][] getValues() {
        int[][] values = new int[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                values[i][j] = sudoku[i][j].getValue();

        return values;
    }


    /**
     * Sets a value in a cell if it's editable.
     */
    public void setValue(int row, int col, int value) {
        if (sudoku[row][col].isEditable()) {
            sudoku[row][col].setValue(value);
        }
    }

    public boolean revealOneCell() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {

                if (sudoku[row][col].getValue() == 0) {

                    List<Integer> options = Arrays.asList(1, 2, 3, 4, 5, 6);
                    Collections.shuffle(options);

                    for (int value : options) {
                        if (isValid(row, col, value)) {

                            sudoku[row][col].setValue(value);
                            sudoku[row][col].setEditable(false);

                            return true;
                        }
                    }

                }
            }
        }

        return false;
    }



    /**
     * Checks whether the entire board is filled in and valid.
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
     * Resets the board to a new puzzle state.
     */
    public void reset() {
        generateBoard();
        clearCells(14); // Default number of cells to clear
    }

    // --- Core Logic ---

    /**
     * Recursively solves the Sudoku using backtracking.
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
     * Validates a number in a specific cell based on Sudoku rules.
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

        // Region check (2x3 block)
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
}