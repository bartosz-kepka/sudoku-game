package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuColumnTest {

    @Test
    void toString_Column_ShouldReturnProperString() {
        SudokuColumn sudokuColumn = new SudokuColumn(new SudokuField[]{
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)});
        String expected = "1\n2\n3\n4\n5\n6\n7\n8\n9\n";
        assertEquals(expected, sudokuColumn.toString());
    }
}