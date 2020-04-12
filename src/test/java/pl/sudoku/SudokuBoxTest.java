package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoxTest {

    @Test
    void toString_Box_ShouldReturnProperString() {
        SudokuBox sudokuBox = new SudokuBox(new SudokuField[]{
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)});
        String expected = "1 2 3 \n4 5 6 \n7 8 9 \n";
        assertEquals(expected,sudokuBox.toString());
    }
}