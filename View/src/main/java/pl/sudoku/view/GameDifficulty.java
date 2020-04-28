package pl.sudoku.view;

import java.util.Random;
import pl.sudoku.model.SudokuBoard;

public enum GameDifficulty {
    EASY(3),
    MEDIUM(5),
    HARD(7);

    private final int multiplier;

    GameDifficulty(int multiplier) {
        this.multiplier = multiplier;
    }

    /**
     * Returns multiplier value used for calculating how many fields to clear.
     *
     * @return multiplier value
     */
    public int getMultiplier() {
        return multiplier;
    }

    /**
     * Sets to 0 number of sudoku fields proportionally to chosen difficulty.
     * Higher difficulty -> more cleared sudoku fields.
     * Number of cleared fields = sudoku board side size * multiplier
     *
     * @param sudokuBoard board to clear fields in
     */
    public void clearSudokuFields(SudokuBoard sudokuBoard) {
        int boardSize = sudokuBoard.getBoardSize();
        int fieldsToClear = boardSize * multiplier;

        for (int fieldsCleared = 0; fieldsCleared < fieldsToClear; ) {
            Random random = new Random();
            int row;
            int column;
            row = random.nextInt(boardSize);
            column = random.nextInt(boardSize);

            if (sudokuBoard.get(row, column) != 0) {
                sudokuBoard.set(row, column, 0);
                fieldsCleared++;
            }
        }

    }
}