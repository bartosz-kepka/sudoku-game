package pl.sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SudokuBoardTest {

    @Test
    void solveGame_FillOneBoard_ShouldBeFilledCorrectly() {
        int[][] board;
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());

        sudokuBoard.solveGame();
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
    void solveGame_FillTwoBoards_ShouldBeDifferent() {
        SudokuBoard sudokuBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudokuBoard2 = new SudokuBoard(new BacktrackingSudokuSolver());

        sudokuBoard1.solveGame();
        sudokuBoard2.solveGame();

        Assertions.assertNotEquals(sudokuBoard1, sudokuBoard2, "Two solved sudoku boards shouldn't be filled the same");
    }

    @Test
    void toString_EmptyBoard_ShouldReturnFormattedString() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());

        String expected = "\n0 0 0 0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 0 0 0 \n";

        Assertions.assertEquals(expected, sudokuBoard.toString(), "String representation of the sudoku board is wrong.");
    }

    @Test
    void toString_PartlyFilled_ShouldReturnProperString() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());

        sudokuBoard.set(0, 0, 1);
        sudokuBoard.set(8, 8, 2);
        sudokuBoard.set(3, 0, 5);
        sudokuBoard.set(0, 2, 6);

        String expected = "\n1 0 6 0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 0 0 0 \n" +
                "5 0 0 0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 0 0 2 \n";
        Assertions.assertEquals(expected, sudokuBoard.toString(), "String representation of the sudoku board is wrong.");
    }

    @Test
    void getBoxSize_DefaultSize_ShouldReturn3() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());

        Assertions.assertEquals(3, sudokuBoard.getBoxSize(), "Returned side of the box is wrong.");
    }

    @Test
    void getBoardSize_DefaultSize_ShouldReturn9() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());

        Assertions.assertEquals(9, sudokuBoard.getBoardSize(), "Returned side of the box is wrong.");
    }

    @Test
    void equals_CompareToNull_ShouldReturnFalse() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());

        Assertions.assertFalse(sudokuBoard.equals(null));
    }

    @Test
    void equals_CompareToString_ShouldReturnFalse() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());

        String test = "Test";

        Assertions.assertFalse(sudokuBoard.equals(test));
    }

    @Test
    void equals_CompareToItself_ShouldReturnTrue() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());

        Assertions.assertTrue(sudokuBoard.equals(sudokuBoard));
    }

    @Test
    void getHashCode_TwoEmptyBoards_ShouldReturnTheSameHash() {
        SudokuBoard sudokuBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudokuBoard2 = new SudokuBoard(new BacktrackingSudokuSolver());

        Assertions.assertEquals(sudokuBoard1.hashCode(), sudokuBoard2.hashCode(), "Hash for two empty boards with the same size should be the same.");
    }

    @Test
    void getHashCode_TwoDifferentBoards_ShouldReturnTheDifferentHash() {
        SudokuBoard sudokuBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudokuBoard2 = new SudokuBoard(new BacktrackingSudokuSolver());

        sudokuBoard1.solveGame();
        sudokuBoard2.solveGame();

        Assertions.assertNotEquals(sudokuBoard1.hashCode(), sudokuBoard2.hashCode(), "Hash for two filled boards should be different.");
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