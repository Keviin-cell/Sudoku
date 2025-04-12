package com.example.sudoku.model;

public class Cell implements Validable {

    private boolean editable;
    private int value;

    public Cell(boolean editable, boolean fixed) {
        this.editable = editable;
        this.value = fixed ? 1 : 0;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean isValid() {
        return value >= 1 && value <= 6;
    }
}
