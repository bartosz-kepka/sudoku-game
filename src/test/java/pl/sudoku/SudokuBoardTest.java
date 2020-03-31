package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {

    @Test
    public void solveGame_FillOneBoard_ShouldBeFilledCorrectly() {
        SudokuField[][] board;
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        int boardSize = sudokuBoard.getBoardSize();
        int boxSize = sudokuBoard.getBoxSize();

        sudokuBoard.solveGame();
        board = sudokuBoard.getCopyOfBoard();

        // Choose each number in sudoku board one by one
        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                int firstRowInBox = row - (row % boxSize);
                int firstColumnInBox = column - (column % boxSize);
                int value = board[row][column].getFieldValue();

                // Check if chosen value is duplicated in box
                assertFalse(isDuplicatedInBox(board, boxSize, row, column, firstRowInBox, firstColumnInBox, value),
                        "Found duplicate in box starting at row " + firstRowInBox + ", column " + firstColumnInBox);

                // Check if chosen value is duplicated in row
                assertFalse(isDuplicatedInRow(board, boardSize, row, column, value),
                        "Found duplicate in row " + row);

                // Check if chosen value is duplicated in column
                assertFalse(isDuplicatedInColumn(board, boardSize, row, column, value),
                        "Found duplicate in column " + column);

            }
        }
    }

    @Test
    public void solveGame_FillTwoBoards_ShouldBeDifferent() {
        SudokuBoard sudokuBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudokuBoard2 = new SudokuBoard(new BacktrackingSudokuSolver());

        sudokuBoard1.solveGame();
        sudokuBoard2.solveGame();

        assertNotEquals(sudokuBoard1, sudokuBoard2, "Two solved sudoku boards shouldn't be filled the same");
    }

    @Test
    public void toString_EmptyBoard_ShouldReturnFormattedString() {
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

        assertEquals(expected, sudokuBoard.toString(), "String representation of the sudoku board is wrong.");
    }

    @Test
    public void toString_PartlyFilled_ShouldReturnProperString() {
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
        assertEquals(expected, sudokuBoard.toString(), "String representation of the sudoku board is wrong.");
    }

    @Test
    public void equals_CompareToNull_ShouldReturnFalse() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());

        assertNotEquals(null, sudokuBoard);
    }

    @Test
    public void equals_CompareToString_ShouldReturnFalse() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());

        String test = "Test";

        assertNotEquals(sudokuBoard, test);
    }

    @Test
    public void equals_CompareToItself_ShouldReturnTrue() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());

        assertEquals(sudokuBoard, sudokuBoard);
    }

    @Test
    public void HashCode_TwoEmptyBoards_ShouldReturnTheSameHash() {
        SudokuBoard sudokuBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudokuBoard2 = new SudokuBoard(new BacktrackingSudokuSolver());

        assertEquals(sudokuBoard1.hashCode(), sudokuBoard2.hashCode(), "Hash for two empty boards with the same size should be the same.");
    }

    @Test
    public void HashCode_TwoTheSameBoards_ShouldReturnTheSameHash() {
        SudokuBoard sudokuBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudokuBoard2 = new SudokuBoard(new BacktrackingSudokuSolver());

        sudokuBoard1.set(3, 3, 4);
        sudokuBoard2.set(3, 3, 4);

        assertEquals(sudokuBoard1.hashCode(), sudokuBoard2.hashCode(), "Hash for two empty boards with the same size should be the same.");
    }

    @Test
    public void HashCode_TwoDifferentBoards_ShouldReturnTheDifferentHashes() {
        SudokuBoard sudokuBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudokuBoard2 = new SudokuBoard(new BacktrackingSudokuSolver());

        sudokuBoard1.solveGame();
        sudokuBoard2.solveGame();

        assertNotEquals(sudokuBoard1.hashCode(), sudokuBoard2.hashCode(), "Hash for two filled boards should be different.");
    }

    private boolean isDuplicatedInColumn(SudokuField[][] board, int columnSize, int row, int column, int number) {
        for (int r = 0; r < columnSize; r++) {
            if (r != row) {
                if (board[r][column].getFieldValue() == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isDuplicatedInRow(SudokuField[][] board, int rowSize, int row, int column, int number) {
        for (int c = 0; c < rowSize; c++) {
            if (c != column) {
                if (board[row][c].getFieldValue() == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isDuplicatedInBox(SudokuField[][] board, int boxSize, int row, int column, int firstRowInBox, int firstColumnInBox, int number) {
        for (int r = firstRowInBox; r < firstRowInBox + boxSize; r++) {
            for (int c = firstColumnInBox; c < firstColumnInBox + boxSize; c++) {
                if (r != row && c != column) {
                    if (board[r][c].getFieldValue() == number) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}