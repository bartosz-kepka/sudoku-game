package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuRowTest {

    @Test
    void toString_Row_ShouldReturnProperString() {
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
        String expected = "1 2 3 4 5 6 7 8 9 \n";
        assertEquals(expected,sudokuRow.toString());
    }
}