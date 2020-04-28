package pl.sudoku.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import pl.sudoku.model.BacktrackingSudokuSolver;
import pl.sudoku.model.SudokuBoard;

public class GameDifficultyTest {

    @Test
    public void clearSudokuFields_Easy_ShouldClearProperNumberOfFields() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        GameDifficulty gameDifficulty = GameDifficulty.EASY;
        gameDifficulty.clearSudokuFields(sudokuBoard);

        int boardSize = sudokuBoard.getBoardSize();
        int expectedClearedFields = boardSize * gameDifficulty.getMultiplier();
        int clearedFields = countClearedFields(sudokuBoard);

        assertEquals(expectedClearedFields, clearedFields);
    }

    @Test
    public void clearSudokuFields_Medium_ShouldClearProperNumberOfFields() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        GameDifficulty gameDifficulty = GameDifficulty.MEDIUM;
        gameDifficulty.clearSudokuFields(sudokuBoard);

        int boardSize = sudokuBoard.getBoardSize();
        int expectedClearedFields = boardSize * gameDifficulty.getMultiplier();
        int clearedFields = countClearedFields(sudokuBoard);

        assertEquals(expectedClearedFields, clearedFields);
    }

    @Test
    public void clearSudokuFields_Hard_ShouldClearProperNumberOfFields() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        GameDifficulty gameDifficulty = GameDifficulty.HARD;
        gameDifficulty.clearSudokuFields(sudokuBoard);

        int boardSize = sudokuBoard.getBoardSize();
        int expectedClearedFields = boardSize * gameDifficulty.getMultiplier();
        int clearedFields = countClearedFields(sudokuBoard);

        assertEquals(expectedClearedFields, clearedFields);
    }

    private int countClearedFields(SudokuBoard sudokuBoard) {
        int boardSize = sudokuBoard.getBoardSize();
        int clearedFields = 0;

        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                if (sudokuBoard.get(row, column) == 0) {
                    clearedFields++;
                }
            }
        }
        return clearedFields;
    }
}