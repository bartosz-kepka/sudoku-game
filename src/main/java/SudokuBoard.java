import java.security.SecureRandom;
import java.util.Arrays;

public class SudokuBoard {

    public static final int SIZE = 9;
    public static final int NUMBER_OF_CELLS = SIZE * SIZE;
    private int[][] board = new int[SIZE][SIZE];

    public int[][] getBoard() {
        int[][] copyArray = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            copyArray[i] = Arrays.copyOf(board[i], SIZE);
        }
        return copyArray;
    }

    private boolean isNotInSquare(int row, int col, int number, int index) {
        int r = row / (SIZE / 3);
        int c = col / (SIZE / 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int actualSquareRow = i + r * 3;
                int actualSquareColumn = j + c * 3;
                if (number == board[actualSquareRow][actualSquareColumn]
                        && (actualSquareRow * 9 + actualSquareColumn) < index) {
                    return false;
                }
            }

        }
        return true;
    }

    private boolean isNotInColumn(int row, int col, int number) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == number) {
                return false;
            }
        }
        return true;
    }

    private boolean isNotInRow(int row, int col, int number) {
        for (int i = 0; i < col; i++) {
            if (board[row][i] == number) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method called by the fillBoard method
     *
     * @param index index in array of 81 sudoku values that will be checked if it meets requirements
     * @return boolean value if index fulfills sudoku rules
     */
    private boolean isValid(int index) {
        int indexRow = index / SIZE;
        int indexColumn = index % SIZE;
        int checkedValue = board[indexRow][indexColumn];
        return isNotInRow(indexRow, indexColumn, checkedValue) && isNotInColumn(indexRow, indexColumn, checkedValue) && isNotInSquare(indexRow, indexColumn, checkedValue, index);
    }

    public void fillBoard() {
        SecureRandom random = new SecureRandom();
        int[] initialNumbers = new int[NUMBER_OF_CELLS]; //array of numbers algorithm begins with
        for (int i = 0; i < NUMBER_OF_CELLS; i++) {
            int row = i / SIZE;
            int column = i % SIZE;
            boolean isCorrect = false;
            if (initialNumbers[i] == 0) {
                initialNumbers[i] = random.nextInt(9) + 1;
                board[row][column] = initialNumbers[i];
                //check if written value is correct
                do {
                    if (isValid(i)) {
                        isCorrect = true;
                        break;
                    }
                    board[row][column] = board[row][column] % 9 + 1; //check rest of the values
                } while (board[row][column] != initialNumbers[i]);
            } else { //if backtracked use previous value
                board[row][column] = board[row][column] % 9 + 1;
                while (board[row][column] != initialNumbers[i]) {
                    if (isValid(i)) {
                        isCorrect = true;
                        break;
                    }
                    board[row][column] = board[row][column] % 9 + 1;
                }
            } //backtrack
            if (!isCorrect) {
                initialNumbers[i] = 0; //reset current value
                board[row][column] = 0; // reset value written to board
                i -= 2; // move to previous value
            }
        }
    }
}
