package pl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

                // Check if chosen number is duplicated in box
                Assertions.assertFalse(isDuplicatedInRow(board, row, column, firstRowInBox, firstColumnInBox, number),
                        "Found duplicate in box starting at row " + firstRowInBox + ", column " + firstColumnInBox);

                // Check if chosen number is duplicated in row
                Assertions.assertFalse(isDuplicatedInRow(board, row, column, number),
                        "Found duplicate in row " + row);

                // Check if chosen number is duplicated in column
                Assertions.assertFalse(isDuplicatedInColumn(board, row, column, number),
                        "Found duplicate in column " + column);

            }
        }
    }

    @Test
    void fillBoard_FillTwoBoards_ShouldBeDifferent() {
        SudokuBoard sudokuBoard1 = new SudokuBoard();
        SudokuBoard sudokuBoard2 = new SudokuBoard();

        sudokuBoard1.fillBoard();
        sudokuBoard2.fillBoard();

        Assertions.assertNotEquals(sudokuBoard1, sudokuBoard2, "Two next sudoku boards are the same");
    }

    private boolean isDuplicatedInColumn(int[][] board, int row, int column, int number) {
        for (int r = 0; r < 9; r++) {
            if (r != row) {
                if (board[r][column] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isDuplicatedInRow(int[][] board, int row, int column, int number) {
        for (int c = 0; c < 9; c++) {
            if (c != column) {
                if (board[row][c] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isDuplicatedInRow(int[][] board, int row, int column, int firstRowInBox, int firstColumnInBox, int number) {
        for (int r = firstRowInBox; r < firstRowInBox + 3; r++) {
            for (int c = firstColumnInBox; c < firstColumnInBox + 3; c++) {
                if (r != row && c != column) {
                    if (board[r][c] == number) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}