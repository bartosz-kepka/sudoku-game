package pl.sudoku.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuColumnTest {


    @Test
    public void equals_CompareToDifferentClassWithTheSameContent_ShouldReturnFalse() {
        SudokuField[] content = new SudokuField[]{
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)};

        SudokuColumn sudokuColumn = new SudokuColumn(content);
        SudokuRow sudokuRow = new SudokuRow(content);

        assertNotEquals(sudokuColumn, sudokuRow);
    }

    @Test
    public void equals_CompareToColumnWithTheSameContent_ShouldReturnTrue() {
        SudokuField[] content = new SudokuField[]{
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)};

        SudokuColumn sudokuColumn = new SudokuColumn(content);
        SudokuColumn sudokuColumn1 = new SudokuColumn(content);

        assertEquals(sudokuColumn, sudokuColumn1);
    }

    @Test
    public void equals_CompareToColumnWithDifferentContent_ShouldReturnFalse() {
        SudokuColumn sudokuColumn = new SudokuColumn(new SudokuField[]{
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(8),
                new SudokuField(9)});

        SudokuColumn sudokuColumn1 = new SudokuColumn(new SudokuField[]{
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)});

        assertNotEquals(sudokuColumn, sudokuColumn1);
    }
}