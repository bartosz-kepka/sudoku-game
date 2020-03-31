package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuFieldGroupTest {

    @Test
    public void verifyValidTest() {
        SudokuRow sudokuRow = new SudokuRow(new SudokuField[]{
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)});
        assertTrue(sudokuRow.verify());
    }

    @Test
    public void verifyInvalidTest() {
        SudokuRow sudokuRow = new SudokuRow(new SudokuField[]{
                new SudokuField(2),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)});
        assertFalse(sudokuRow.verify());
    }
}