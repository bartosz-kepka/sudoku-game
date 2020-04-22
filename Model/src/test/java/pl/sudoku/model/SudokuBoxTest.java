package pl.sudoku.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoxTest {

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

        SudokuBox sudokuBox = new SudokuBox(content);
        SudokuRow sudokuRow = new SudokuRow(content);

        assertNotEquals(sudokuBox, sudokuRow);
    }

    @Test
    public void equals_CompareToBoxWithTheSameContent_ShouldReturnTrue() {
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

        SudokuBox sudokuBox = new SudokuBox(content);
        SudokuBox sudokuBox1 = new SudokuBox(content);

        assertEquals(sudokuBox, sudokuBox1);
    }

    @Test
    public void equals_CompareToBoxWithDifferentContent_ShouldReturnFalse() {
        SudokuBox sudokuBox = new SudokuBox(new SudokuField[]{
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(8),
                new SudokuField(9)});

        SudokuBox sudokuBox1 = new SudokuBox(new SudokuField[]{
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)});
        assertNotEquals(sudokuBox, sudokuBox1);
    }
}