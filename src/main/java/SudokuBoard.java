import java.util.Arrays;
import java.util.Random;

/**
 * Represents a sudoku board.
 *
 * @author Bartosz Kepka 224326
 */
public final class SudokuBoard {

    /**
     * Represents side size of 9x9 sudoku board.
     * The value for this field is {@value}.
     */
    private static final int BOARD_SIZE = 9;

    /**
     * Represents side size of 3x3 box.
     * The value for this field is {@value}.
     */
    private static final int BOX_SIZE = 3;

    /**
     * Numbers that can be inserted into sudoku board (ints from 1 to 9).
     * Shuffled by {@link #shuffleCandidates()}
     * every time before trying to fill each cell in board.
     * Filled by {@link #SudokuBoard() constructor}.
     */
    private static int[] candidates = new int[BOARD_SIZE];

    /**
     * Static instance of class Random generating random numbers.
     * For shuffling candidates.
     */
    private static Random random = new Random();

    /**
     * Board is stored as a square array of ints.
     * Size: {@link #BOARD_SIZE}x{@link #BOARD_SIZE}.
     * 0 means that cell in unassigned
     */
    private int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

    /**
     * Checks if number is already used in the box.
     *
     * @param row    row in which we want to insert the number
     * @param column column in which we want to insert the number
     * @param number number to check
     * @return true if is used, otherwise false
     */
    private boolean isUsedInBox(final int row,
                                final int column,
                                final int number) {
        int firstRowInBox = row - (row % BOX_SIZE);
        int firstColumnInBox = column - (column % BOX_SIZE);

        for (int i = 0; i < BOX_SIZE; i++) {
            for (int j = 0; j < BOX_SIZE; j++) {
                if (board[firstRowInBox + i][firstColumnInBox + j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if number is already used in the row.
     *
     * @param row    row in which we want to insert the number
     * @param number number to check
     * @return true if is used, otherwise false
     */
    private boolean isUsedInRow(final int row, final int number) {
        for (int i = 0; i < BOARD_SIZE; i++) {

            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if number is already used in the row.
     *
     * @param column column in which we want to insert the number
     * @param number number to check
     * @return true if is used, otherwise false
     */
    private boolean isUsedInColumn(final int column, final int number) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if candidate can be inserted into board cell at given position.
     *
     * @param row       row in which we want to insert the candidate
     * @param column    column in which we want to insert the candidate
     * @param candidate number to check
     * @return true if used neither in given row, column nor box, else false
     */
    private boolean isCandidateRight(final int row,
                                     final int column,
                                     final int candidate) {
        return !(isUsedInBox(row, column, candidate)
                || isUsedInColumn(column, candidate)
                || isUsedInRow(row, candidate));
    }

    /**
     * Shuffles {@link #candidates} to get greater randomness.
     */
    private void shuffleCandidates() {
        for (int i = BOARD_SIZE - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = candidates[index];
            candidates[index] = candidates[i];
            candidates[i] = temp;
        }
    }

    /**
     * Constructor.
     * Fills array of {@link #candidates} to insert.
     */
    public SudokuBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            candidates[i] = i + 1;
        }
    }

    /**
     * Fills board following sudoku rules.
     * Called recursively, implements backtracking algorithm.
     * Steps:
     * 1. Checks for first unassigned cell.
     * Going from left to right, top to bottom.
     * 2. If found, tries to insert each integer from 1 to 9 in random order.
     * Otherwise sudoku is solved and returns true.
     * 3. If number {@link #isCandidateRight(int, int, int) is a right candidate}
     * inserts it and calls itself recursively. Otherwise tries next number.
     * If none of them can be inserted, returns false and goes back to previous call.
     *
     * @return true if succeeded, otherwise false
     */
    public boolean fillBoard() {
        int row = -1;
        int column = -1;

        // Check for unassigned cells on sudoku board
        boolean isSolved = true;
        for (int i = 0; i < BOARD_SIZE && isSolved; i++) {
            for (int j = 0; j < BOARD_SIZE && isSolved; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    column = j;
                    isSolved = false;
                }
            }
        }

        // If all of the cells are assigned then sudoku is solved
        if (isSolved) {
            return true;
        }

        // Try to find a correct number for unassigned cell
        // If successful then assign and call solveSudoku() recursively
        // If not then go back to previous call on the stack
        shuffleCandidates();
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (isCandidateRight(row, column, candidates[i])) {
                board[row][column] = candidates[i];
                if (fillBoard()) {
                    return true;
                } else {
                    board[row][column] = 0;
                }
            }
        }
        return false;
    }

    /**
     * Accessor for sudoku board.
     *
     * @return copy of board
     */
    public int[][] getCopyOfBoard() {
        return Arrays.copyOf(board, BOARD_SIZE * BOARD_SIZE);
    }

    /**
     * Provides an easy way to print out the board.
     *
     * @return formatted String representing sudoku board
     */
    public String getBoardAsString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
