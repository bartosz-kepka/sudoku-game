package pl.sudoku;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Arrays;

/**
 * Represents a sudoku board.
 *
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
    private SudokuField[][] board = new SudokuField[boardSize][boardSize];

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

        return this.getBox(firstRowInBox, firstColumnInBox).verify();
    }

    /**
     * Checks if row is filled correctly (has no duplicates).
     *
     * @param row number to check
     * @return true if is correct, otherwise false
     */
    private boolean verifyRow(final int row) {
        return this.getRow(row).verify();

    }

    /**
     * Checks if column is filled correctly (has no duplicates).
     *
     * @param column number to check
     * @return true if is correct, otherwise false
     */
    private boolean verifyColumn(final int column) {
        return this.getColumn(column).verify();
    }

    /**
     * Checks if sudoku board is filled correctly or solvable.
     * Sudoku is solvable when some cells are unassigned but
     * there is no duplicates neither in any row, column
     * nor box)
     *
     * @return false if found any duplicates in
     * either row, column or box,
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
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                this.board[i][j] = new SudokuField();
            }
        }
        this.sudokuSolver = solver;
    }

    /**
     * Accessor for sudoku board.
     *
     * @return copy of board
     */
    public SudokuField[][] getCopyOfBoard() {
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
        return board[x][y].getFieldValue();
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
        this.board[x][y].setFieldValue(value);
    }

    /**
     * Get certain sudoku row by it's index.
     *
     * @param row to be recovered form board
     * @return copy of row
     */
    public SudokuRow getRow(final int row) {
        SudokuField[] fields = new SudokuField[boardSize];
        for (int i = 0; i < boardSize; i++) {
            fields[i] = board[row][i];
        }
        return new SudokuRow(fields);
    }

    /**
     * Get certain sudoku column by it's index.
     *
     * @param column to be recovered form board
     * @return copy of column
     */
    public SudokuColumn getColumn(final int column) {
        SudokuField[] fields = new SudokuField[boardSize];
        for (int i = 0; i < boardSize; i++) {
            fields[i] = board[i][column];
        }

        return new SudokuColumn(fields);
    }

    /**
     * Gets sudouBox indicated by coordinates.
     *
     * @param rowIndex    index of row in sudoku board
     * @param columnIndex index of column in sudoku board
     * @return copy of indicated sudokuBox
     */
    public SudokuBox getBox(final int rowIndex, final int columnIndex) {
        SudokuField[] fields = new SudokuField[boardSize];
        int index = 0;
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {
                fields[index++] = board[rowIndex + i][columnIndex + j];
            }
        }

        return new SudokuBox(fields);
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
                sb.append(board[i][j].getFieldValue()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Check if two boards are the same.
     * <p>
     * Checks if values in the board are the same
     * (then returns true) but returns false also when
     * given object is a different class, null or has
     * different board size (for future development).
     * DOESN'T depend on sudoku solver.
     *
     * @param o object to compare
     * @return true if content of array is the same, otherwise return false
     */
    @Override
    public boolean equals(final Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof SudokuBoard)) {
            return false;
        }

        SudokuBoard that = (SudokuBoard) o;

        return new EqualsBuilder()
                .append(boardSize, that.boardSize)
                .append(boxSize, that.boxSize)
                .append(board, that.board)
                .isEquals();
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

        return new HashCodeBuilder(17, 37)
                .append(boardSize)
                .append(boxSize)
                .append(board)
                .toHashCode();
    }
}
