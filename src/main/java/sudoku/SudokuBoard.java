package sudoku;

import java.util.Arrays;

public class SudokuBoard {

    private SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();

    public static final int SIZE = 9;
    private int[][] board = new int[SIZE][SIZE];

    public int get(int x, int y) {
        return board[x][y];
    }

    public void set(int x, int y, int value) {
        board[x][y] = value;
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }

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

    public int[][] getCopyOfBoard() {
        int[][] copyArray = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            copyArray[i] = Arrays.copyOf(board[i], SIZE);
        }
        return copyArray;
    }

}
