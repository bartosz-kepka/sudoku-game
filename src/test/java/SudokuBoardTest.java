import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SudokuBoardTest {

    @Test
    void fillBoard_FillOneBoard_ShouldBeFilledCorrectly() {
        int[][] board;
        SudokuBoard sudokuBoard = new SudokuBoard();

        sudokuBoard.fillBoard();
        board = sudokuBoard.getCopyOfBoard();

        // Choose each number in sudoku board one by one
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                int firstRowInBox = row - (row % 3);
                int firstColumnInBox = column - (column % 3);
                int number = board[row][column];

                // Check if chosen number is unique in box
                for (int r = firstRowInBox; r < firstRowInBox + 3; r++) {
                    for (int c = firstColumnInBox; c < firstColumnInBox + 3; c++) {
                        if (r != row && c != column) {
                            Assertions.assertNotEquals(number, board[r][c], "Found duplicate in box starting at row " + firstRowInBox + ", column " + firstColumnInBox);
                        }
                    }
                }

                // Check if chosen number is unique in row
                for (int c = 0; c < 9; c++) {
                    if (c != column) {
                        Assertions.assertNotEquals(number, board[row][c], "Found duplicate in row " + row);
                    }
                }

                // Check if chosen number is unique in column
                for (int r = 0; r < 9; r++) {
                    if (r != row) {
                        Assertions.assertNotEquals(number, board[r][column], "Found duplicate in column " + column);
                    }
                }
            }
        }
    }

    @Test
    void fillBoard_FillTwoBoards_ShouldBeDifferent() {
        int[][] board1, board2;
        SudokuBoard sudokuBoard1 = new SudokuBoard();
        SudokuBoard sudokuBoard2 = new SudokuBoard();

        sudokuBoard1.fillBoard();
        sudokuBoard2.fillBoard();
        board1 = sudokuBoard1.getCopyOfBoard();
        board2 = sudokuBoard2.getCopyOfBoard();

        Assertions.assertFalse(Arrays.equals(board1, board2), "Two next sudoku boards are the same");
    }

}