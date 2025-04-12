package com.example.sudoku.model;

import java.util.*;

public class Board {

    private Celda[][] sudoku;
    private final int size = 6;

    private static class Position {
        int row, col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public Board() {
    }

    public void generateBoard() {
        sudoku = new Celda[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sudoku[i][j] = new Celda(true, false);
                sudoku[i][j].setValor(0);
            }
        }

        solve(0, 0);
    }

    public boolean solve(int row, int col) {
        if (row == size) {
            return true;
        }

        int nextRow = (col == size - 1) ? row + 1 : row;
        int nextCol = (col + 1) % size;

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Collections.shuffle(numbers);

        for (int num : numbers) {
            if (isValid(row, col, num)) {
                sudoku[row][col].setValor(num);

                if (solve(nextRow, nextCol)) {
                    return true;
                }
                sudoku[row][col].setValor(0);
            }
        }

        return false;
    }

    public void clearCells(int count) {
        List<Position> positions = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                positions.add(new Position(i, j));
            }
        }

        Collections.shuffle(positions);

        for (int i = 0; i < count && i < positions.size(); i++) {
            int r = positions.get(i).row;
            int c = positions.get(i).col;

            sudoku[r][c].setValor(0);
            sudoku[r][c].setEditable(true);
        }
    }

    public int[][] getValues() {
        int[][] values = new int[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                values[i][j] = sudoku[i][j].getValor();
            }
        }
        return values;
    }

    public boolean isValid(int row, int col, int value) {
        for (int j = 0; j < size; j++) {
            if (j != col && sudoku[row][j].getValor() == value) {
                return false;
            }
        }

        for (int i = 0; i < size; i++) {
            if (i != row && sudoku[i][col].getValor() == value) {
                return false;
            }
        }

        int startRow = (row / 2) * 2;
        int startCol = (col / 3) * 3;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                int r = startRow + i;
                int c = startCol + j;
                if ((r != row || c != col) && sudoku[r][c].getValor() == value) {
                    return false;
                }
            }
        }

        return true;
    }

    public int getValue(int row, int col) {
        return sudoku[row][col].getValor();
    }

    public void setValue(int row, int col, int value) {
        if (sudoku[row][col].getEsEditable()) {
            sudoku[row][col].setValor(value);
        }
    }

    public Celda[][] getCells() {
        return sudoku;
    }

    public boolean isComplete() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int value = sudoku[i][j].getValor();
                if (value == 0 || !isValid(i, j, value)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void reset() {
        generateBoard();
        clearCells(12);
    }
}
