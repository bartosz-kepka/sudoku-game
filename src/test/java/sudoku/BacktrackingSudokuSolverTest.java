package sudoku;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BacktrackingSudokuSolverTest {

    private void checkSquare(int[][] checkedBoard) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int i1 = 0; i1 < 9; i1++) {
                    for (int i2 = i1 + 1; i2 < 9; i2++) {
                        if (checkedBoard[i * 3 + (i1 / 3)][j * 3 + (i1 % 3)] ==
                                checkedBoard[i * 3 + (i2 / 3)][j * 3 + (i2 % 3)]) {
                            fail("3x3 square" + i + ", " + j + " is not correct!");
                        }
                    }
                }
            }
        }
    }

    private void checkColumn(int[][] checkedBoard) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = j + 1; k < 9; k++) {
                    if (checkedBoard[j][i] == checkedBoard[k][i]) {
                        fail("column " + i + " is not correct");
                    }
                }
            }
        }
    }

    private void checkRow(int[][] checkedBoard) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = j + 1; k < 9; k++) {
                    if (checkedBoard[i][j] == checkedBoard[i][k]) {
                        fail("row " + i + " is not correct");
                    }
                }
            }
        }
    }


    @Test
    void boardIsCorrectlyFilled() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.solveGame();
        int[][] checkedBoard = sudokuBoard.getCopyOfBoard();

        checkRow(checkedBoard);

        checkColumn(checkedBoard);

        checkSquare(checkedBoard);

    }


    @Test
    void twoBoardsAreDifferent() {
        int[][] first;
        int[][] second;
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.solveGame();
        first = sudokuBoard.getCopyOfBoard();
        sudokuBoard.solveGame();
        second = sudokuBoard.getCopyOfBoard();
        assertFalse(Arrays.equals(first, second));
    }

}