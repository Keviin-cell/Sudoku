package com.example.sudoku.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Board {

    private  Celda[][] sudoku;
    private int [][] valores;
    private int size = 6;


    private static class Posicion {
        int fila, col;

        public Posicion(int fila, int col) {
            this.fila = fila;
            this.col = col;
        }
    }

    public Board() {
    }

    public boolean generarTablero() {
        sudoku = new Celda[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sudoku[i][j] = new Celda(true, false);
                sudoku[i][j].setValor(0);
            }
        }

        return resolver(0, 0);
    }

    public boolean resolver(int fila, int col) {
        if (fila == size) {
            return true; // Tablero completo
        }

        int siguienteFila = (col == size - 1) ? fila + 1 : fila;
        int siguienteCol = (col + 1) % size;

        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);
        Collections.shuffle(numeros); // Mezcla aleatoria

        for (int num : numeros) {
            if (esValido(fila, col, num)) {
                sudoku[fila][col].setValor(num);

                if (resolver(siguienteFila, siguienteCol)) {
                    return true;
                }
                sudoku[fila][col].setValor(0); // Backtrack
            }
        }

        return false; // No se pudo resolver desde esta posición
    }


    public void limpiarCeldas(int cantidad) {
        List<Posicion> posiciones = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                posiciones.add(new Posicion(i, j));
            }
        }

        Collections.shuffle(posiciones);

        for (int i = 0; i < cantidad && i < posiciones.size(); i++) {
            int f = posiciones.get(i).fila;
            int c = posiciones.get(i).col;

            sudoku[f][c].setValor(0);
            sudoku[f][c].setEditable(true);
        }
    }

  public int[][] getValores() {
    int[][] valores = new int[6][6];
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        valores[i][j] = sudoku[i][j].getValor(); // Asumiendo que tienes Celda.getValor()
      }
    }
    return valores;
  }


    public boolean esValido(int fila, int col, int valor) {
        // Verificar fila
        for (int j = 0; j < size; j++) {
            if (j != col && sudoku[fila][j].getValor() == valor) {
                return false;
            }
        }


        // Verificar columna
        for (int i = 0; i < size; i++) {
            if (i != fila && sudoku[i][col].getValor() == valor) {
                return false;
            }
        }

        // Verificar bloque 2x3
        int inicioFila = (fila / 2) * 2;
        int inicioCol = (col / 3) * 3;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                int f = inicioFila + i;
                int c = inicioCol + j;
                if ((f != fila || c != col) && sudoku[f][c].getValor() == valor) {
                    return false;
                }
            }
        }

        return true;
    }


    public int getValor(int fila, int col) {
        return sudoku[fila][col].getValor();
    }

    public void setValor(int fila, int col, int valor) {
        if (sudoku[fila][col].getEsEditable()) {
            sudoku[fila][col].setValor(valor);
        }
    }


    public Celda[][] getCeldas() {
        return sudoku;
    }

    public boolean estaCompleto() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int valor = sudoku[i][j].getValor();
                if (valor == 0 || !esValido(i, j, valor)) {
                    return false;
                }
            }
        }
        return true;
    }


    public void reiniciar() {
        generarTablero();
        limpiarCeldas(12);
    }

}
