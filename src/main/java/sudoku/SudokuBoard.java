package sudoku;

import java.util.Arrays;

public class SudokuBoard {

    private SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();

    public static final int SIZE = 9;
    private int[][] board = new int[SIZE][SIZE];

    /**
     * Gets value stored in sudoku board.
     * @param x row of tile in board
     * @param y column of tile in board
     * @return value at coordinates
     */
    public int get(int x, int y) {
        return board[x][y];
    }

    /**
     * Sets value of certain sudoku tile.
     * @param x row of tile in board
     * @param y column of tile in board
     * @param value that is written to coordinates
     */
    public void set(int x, int y, int value) {
        board[x][y] = value;
    }

    /**
     * Method calls sudoku solver, that uses certain solving algorithm.
     */
    public void solveGame() {
        sudokuSolver.solve(this);
    }

    /**
     * Checks validity of filled sudoku board against  rules.
     * @param index of 1D board representation that is  evaluated
     * @return boolean value if given sudoku tile fulfills all game requirements
     */
    public boolean checkBoard(int index) {
        int indexRow = index / SIZE;
        int indexColumn = index % SIZE;
        int checkedValue = this.get(indexRow, indexColumn);

        for (int i = 0; i < indexRow; i++) {
            if (this.get(i, indexColumn) == checkedValue) {
                return false;
            }
        }
        for (int i = 0; i < indexColumn; i++) {
            if (this.get(indexRow, i) == checkedValue) {
                return false;
            }
        }
        int r = indexRow / (SIZE / 3);
        int c = indexColumn / (SIZE / 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int actualSquareRow = i + r * 3;
                int actualSquareColumn = j + c * 3;
                if (checkedValue == this.get(actualSquareRow, actualSquareColumn)
                        && (actualSquareRow * 9 + actualSquareColumn) < index) {
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * To ensure encapsulation method returns copy of board.
     * @return copy of sudoku board
     */
    public int[][] getCopyOfBoard() {
        int[][] copyArray = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            copyArray[i] = Arrays.copyOf(board[i], SIZE);
        }
        return copyArray;
    }

}
