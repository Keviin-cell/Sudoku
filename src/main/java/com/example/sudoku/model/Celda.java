package com.example.sudoku.model;

public class Celda {

    private Boolean esEditable;
    private Boolean esFija;
    private int valor;

    public Celda(boolean esEditable, boolean esFija){
        this.esEditable = esEditable;
        this.esFija = esFija;
    }

    public Boolean getEsEditable() {
        return esEditable;
    }

    public void setEditable(boolean esEditable) {
        this.esEditable = esEditable;
    }

    public Boolean getEsFija() {
        return esFija;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
