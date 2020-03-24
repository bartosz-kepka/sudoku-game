package pl.sudoku;

import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a sudoku board.
 *
 * @author Bartosz Kepka 224326
 */
public final class SudokuBoard {

    /**
     * Represents side size of sudoku board.
     * For future development (different board sizes)
     * The value for this field is {@value}.
     */
    private final int boardSize = 9;

    /**
     * Represents side size of box in sudoku board.
     * For future development (different board sizes)
     * The value for this field is {@value}.
     */
    private final int boxSize = (int) Math.sqrt(boardSize);

    /**
     * Board is stored as a square array of ints.
     * Size: {@link #boardSize}x{@link #boardSize}.
     * 0 means that cell in unassigned
     */
    private int[][] board = new int[boardSize][boardSize];

    /**
     * Implementation of sudokuSolver to use for solving sudoku game.
     */
    private SudokuSolver sudokuSolver;

    /**
     * Checks if bow indicated by row and column in it is filled correctly.
     * (has no duplicates)
     *
     * @param row    which box to check contains
     * @param column which box to check contains
     * @return true if is correct, otherwise false
     */
    private boolean verifyBox(final int row,
                              final int column) {
        int firstRowInBox = row - (row % boxSize);
        int firstColumnInBox = column - (column % boxSize);

        // Choose each number other than 0 in the box
        for (int r = 0; r < boxSize; r++) {
            for (int c = 0; c < boxSize; c++) {
                if (board[firstRowInBox + r][firstColumnInBox + c] != 0) {

                    // Compare to other in the box
                    for (int toCompareR = r; toCompareR < boxSize; toCompareR++) {
                        for (int toCompareC = 0; toCompareC < boxSize; toCompareC++) {
                            if (r != toCompareR || c != toCompareC) {
                                if (board[firstRowInBox + r][firstColumnInBox + c] == board[firstRowInBox + toCompareR][firstColumnInBox + toCompareC]) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Checks if row is filled correctly (has no duplicates).
     *
     * @param row number to check
     * @return true if is correct, otherwise false
     */
    private boolean verifyRow(final int row) {
        for (int column = 0; column < boardSize; column++) {
            if (board[row][column] != 0) {
                for (int toCompare = column + 1; toCompare < boardSize; toCompare++) {
                    if (board[row][column] == board[row][toCompare]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Checks if column is filled correctly (has no duplicates).
     *
     * @param column number to check
     * @return true if is correct, otherwise false
     */
    private boolean verifyColumn(final int column) {
        for (int row = 0; row < boardSize; row++) {
            if (board[row][column] != 0) {
                for (int toCompare = row + 1; toCompare < boardSize; toCompare++) {
                    if (board[row][column] == board[toCompare][column]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Checks if sudoku board is filled correctly or solvable.
     * Sudoku is solvable when some cells are unassigned but
     * there is no duplicates neither in any row, column
     * nor box)
     *
     * @return false if found any duplicates in either row, column or box,
     * otherwise true
     */
    public boolean isFilledCorrectlyOrSolvable() {
        boolean isCorrect = true;

        for (int i = 0; i < boardSize && isCorrect; i++) {
            // Verify row and column
            isCorrect = verifyRow(i) && verifyColumn(i);

            // Verify boxes which contain row number i
            if (i % boxSize == 0 && isCorrect) {
                for (int j = 0; j < boardSize / boxSize && isCorrect; j++) {
                    isCorrect = verifyBox(i, j * boxSize);
                }
            }
        }
        return isCorrect;
    }

    /**
     * Fills sudoku board using solver given in constructor.
     */
    public void solveGame() {
        sudokuSolver.solve(this);
    }

    /**
     * Creates an instance of sudoku board with given solver.
     *
     * @param solver to use for solving
     */
    public SudokuBoard(final SudokuSolver solver) {
        this.sudokuSolver = solver;
    }

    /**
     * Accessor for sudoku board.
     *
     * @return copy of board
     */
    public int[][] getCopyOfBoard() {
        return Arrays.copyOf(board, board.length);
    }

    /**
     * Accessor for sudoku board.
     *
     * @return side size of the board
     */
    public int getBoardSize() {
        return boardSize;
    }

    /**
     * Accessor for sudoku board.
     *
     * @return side size of a box
     */
    public int getBoxSize() {
        return boxSize;
    }

    /**
     * Returns value in board cell at certain position.
     * 0 means that this cell is unassigned.
     *
     * @param x number of row starting from 0
     * @param y number of column starting from 0
     * @return value in call at given row and column
     */
    public int get(final int x, final int y) {
        return board[x][y];
    }

    /**
     * Sets value in board cell at certain position.
     * 0 means that this cell is unassigned.
     *
     * @param x     number of row starting from 0
     * @param y     number of column starting from 0
     * @param value value to assign to cell at position [x][y]
     */
    public void set(final int x, final int y, final int value) {
        board[x][y] = value;
    }

    /**
     * Provides an easy way to print out the board.
     *
     * @return formatted String representing sudoku board
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Check if two boards are the same.
     *
     * Checks if values in the board are the same
     * (then returns true) but returns false also when
     * given object is a different class, null or has
     * different board size (for future development).
     * DOESN'T depend on sudoku solver.
     *
     * @param obj object to compare
     * @return true if content of array is the same, otherwise return false
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        SudokuBoard other = (SudokuBoard) obj;

        if (boardSize != other.getBoardSize() || boxSize != other.getBoxSize()) {
            return false;
        } else {
            return ArrayUtils.isEquals(board, other.getCopyOfBoard());
        }
    }

    /**
     * Generate hash code of sudoku board.
     * Depends on board size, box size and content of the board.
     * NOT on the sudoku solver.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(boardSize, boxSize);
        result = 31 * result + ArrayUtils.hashCode(board);
        return result;
    }
}
