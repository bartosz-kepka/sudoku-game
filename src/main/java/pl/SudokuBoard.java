package pl;

import java.util.*;

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
    private final int BOARD_SIZE = 9;

    /**
     * Represents side size of 3x3 box.
     * The value for this field is {@value}.
     */
    private final int BOX_SIZE = 3;

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
     * Generates shuffled list of integers that could be inserted into board cell depends on its size
     * eg. for 9x9 board returns shuffled list of {1, 2, ..., 8, 9}
     *
     * @return list of candidates
     */
    private List<Integer> generateShuffledCandidates() {
        List<Integer> candidates = new ArrayList<>();
        for (int i = 1; i <= BOARD_SIZE; i++) {
            candidates.add(i);
        }
        Collections.shuffle(candidates);
        return candidates;
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
        List<Integer> candidates = generateShuffledCandidates();

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
        //shuffleCandidates();
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (isCandidateRight(row, column, candidates.get(i))) {
                board[row][column] = candidates.get(i);
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
     * Accessor for sudoku board.
     *
     * @return size of the board
     */
    public int getBOARD_SIZE() {
        return BOARD_SIZE;
    }

    /**
     * Returns value in board cell at certain position.
     * 0 means that this cell is unassigned.
     *
     * @param row    number of row starting from 0
     * @param column number of column starting from 0
     * @return value in call at given row and column
     */
    public int getValueAt(int row, int column) {
        return board[row][column];
    }

    /**
     * Provides an easy way to print out the board.
     *
     * @return formatted String representing sudoku board
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Checks if values in the board are the same (then returns true) but returns false also when
     * given object is a different class, null or has different board size (for future development)
     *
     * @param obj object to compare
     * @return true if content of array is the same, otherwise return false
     */
    @Override
    public boolean equals(Object obj) {
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

        if (BOARD_SIZE != other.getBOARD_SIZE()) {
            return false;
        } else {
            for (int row = 0; row < BOARD_SIZE; row++) {
                for (int column = 0; column < BOARD_SIZE; column++) {
                    if (board[row][column] != other.getValueAt(row, column)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
