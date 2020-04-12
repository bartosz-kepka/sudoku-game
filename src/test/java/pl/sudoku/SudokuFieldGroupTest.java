package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuFieldGroupTest {

    @Test
    public void verify_ValidGroup_ShouldReturnTrue() {
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
    public void verify_InvalidGroup_ShouldReturnFalse() {
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

    @Test
    public void equals_CompareToNull_ShouldReturnFalse() {
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
        assertNotEquals(null, sudokuRow);
    }

    @Test
    public void equals_CompareToInstanceOfDifferentClass_ShouldReturnFalse() {
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
        String s = "Test";
        assertNotEquals(sudokuRow, s);
    }

    @Test
    public void equals_CompareToItselfDifferentSize_ShouldReturnFalse() {
        SudokuRow sudokuRow = new SudokuRow(new SudokuField[]{
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8)});
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
    @Test
    public void equals_CompareToItself_ShouldReturnTrue() {
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
        assertEquals(sudokuRow, sudokuRow);
    }

    @Test
    void hashCode_TwoTheSameGroups_ShouldReturnTheSameHash() {
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
        assertEquals(sudokuRow.hashCode(), sudokuRow1.hashCode(), "Hash for two identical groups should be the same.");
    }

    @Test
    void hashCode_TwoDifferentGroups_ShouldReturnDifferentHash() {
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


        SudokuRow sudokuRow1 = new SudokuRow(new SudokuField[]{
                new SudokuField(2),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)});
        assertNotEquals(sudokuRow.hashCode(), sudokuRow1.hashCode(), "Hash for two different groups should be different.");
    }
}