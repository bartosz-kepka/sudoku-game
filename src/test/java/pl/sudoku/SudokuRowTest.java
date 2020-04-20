package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuRowTest {

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

        SudokuRow sudokuRow = new SudokuRow(content);
        SudokuColumn sudokuColumn = new SudokuColumn(content);

        assertNotEquals(sudokuRow, sudokuColumn);
    }

    @Test
    public void equals_CompareToRowWithTheSameContent_ShouldReturnTrue() {
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

        SudokuRow sudokuRow = new SudokuRow(content);
        SudokuRow sudokuRow1 = new SudokuRow(content);

        assertEquals(sudokuRow, sudokuRow1);
    }

    @Test
    public void equals_CompareToRowWithDifferentContent_ShouldReturnFalse() {
        SudokuRow sudokuRow = new SudokuRow(new SudokuField[]{
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(8),
                new SudokuField(9)});

        SudokuRow sudokuRow1 = new SudokuRow(new SudokuField[]{
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)});
        assertNotEquals(sudokuRow, sudokuRow1);
    }

}