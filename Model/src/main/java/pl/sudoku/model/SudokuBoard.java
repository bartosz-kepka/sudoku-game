package pl.sudoku.model;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Represents a sudoku board.
 */
public final class SudokuBoard implements Serializable {

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
        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                this.board[row][column] = new SudokuField();
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
     * Gets certain sudoku row by it's index.
     *
     * @param rowIndex index of row in sudoku board
     * @return copy of row
     */
    public SudokuRow getRow(final int rowIndex) {
        SudokuField[] fields = new SudokuField[boardSize];
        for (int i = 0; i < boardSize; i++) {
            fields[i] = board[rowIndex][i];
        }
        return new SudokuRow(fields);
    }

    /**
     * Gets certain sudoku column by it's index.
     *
     * @param columnIndex index of column in sudoku board
     * @return copy of column
     */
    public SudokuColumn getColumn(final int columnIndex) {
        SudokuField[] fields = new SudokuField[boardSize];
        for (int i = 0; i < boardSize; i++) {
            fields[i] = board[i][columnIndex];
        }

        return new SudokuColumn(fields);
    }

    /**
     * Gets sudokuBox indicated by coordinates.
     *
     * @param rowIndex    index of row in sudoku board
     * @param columnIndex index of column in sudoku board
     * @return copy of indicated sudokuBox
     */
    public SudokuBox getBox(final int rowIndex, final int columnIndex) {
        int firstRowInBox = rowIndex - (rowIndex % boxSize);
        int firstColumnInBox = columnIndex - (columnIndex % boxSize);

        SudokuField[] fields = new SudokuField[boardSize];
        int index = 0;
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {
                fields[index++] =
                        board[firstRowInBox + i][firstColumnInBox + j];
            }
        }

        return new SudokuBox(fields);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("board", board)
                .toString();
    }

    /**
     * Checks if two boards are the same.
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
                .append(board, that.board)
                .isEquals();
    }

    /**
     * Generates hash code of sudoku board.
     * Depends on content of the board,
     * NOT on the sudoku solver.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {

        return new HashCodeBuilder(17, 37)
                .append(board)
                .toHashCode();
    }
}
