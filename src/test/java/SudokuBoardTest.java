import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    @Test
    void boardIsCorrectlyFilled() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.fillBoard();
        int[][] checkedBoard = sudokuBoard.getBoard();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = j + 1; k < 9; k++) {
                    if (checkedBoard[i][j] == checkedBoard[i][k]) {
                        fail("row " + i + " is not correct");
                    }
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = j + 1; k < 9; k++) {
                    if (checkedBoard[j][i] == checkedBoard[k][i]) {
                        fail("column " + i + " is not correct");
                    }
                }
            }
        }
        //3x3 square
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


    @Test
    void twoBoardsAreDifferent() {
        int[][] first;
        int[][] second;
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.fillBoard();
        first = sudokuBoard.getBoard();
        sudokuBoard.fillBoard();
        second = sudokuBoard.getBoard();
        assertFalse(Arrays.equals(first, second));
    }
}