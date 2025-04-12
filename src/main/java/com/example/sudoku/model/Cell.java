package com.example.sudoku.model;

/**
 * Represents a single cell in the Sudoku board.
 * A cell can be editable or fixed, and stores an integer value from 1 to 6.
 * Implements the {@link Validable} interface to provide value validation.
 *
 * @author Kevin Muñoz
 * @author Leon Flor
 */
public class Cell implements Validable {

    /** Indicates whether the cell is editable by the user. */
    private boolean editable;

    /** Value stored in the cell (0 if empty). */
    private int value;

    /**
     * Constructs a new Cell.
     *
     * @param editable true if the cell can be edited by the player
     * @param fixed true if the cell is initially fixed with a default value
     */
    public Cell(boolean editable, boolean fixed) {
        this.editable = editable;
        this.value = fixed ? 1 : 0;
    }

    /**
     * Checks whether the cell is editable.
     *
     * @return true if the cell is editable, false otherwise
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * Sets whether the cell is editable.
     *
     * @param editable true to make the cell editable, false to make it fixed
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    /**
     * Gets the value of the cell.
     *
     * @return the integer value of the cell (0 if empty)
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the cell.
     *
     * @param value the integer value to assign to the cell
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Checks whether the current value of the cell is valid (between 1 and 6).
     *
     * @return true if the value is valid, false otherwise
     */
    @Override
    public boolean isValid() {
        return value >= 1 && value <= 6;
    }
}
