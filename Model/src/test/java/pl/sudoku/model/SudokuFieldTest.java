package pl.sudoku.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

public class SudokuFieldTest {

    @Test
    public void getFieldValue_DefaultValue_ShouldReturn0() {
        SudokuField sudokuField = new SudokuField();

        assertEquals(sudokuField.getFieldValue(), 0);
    }

    @Test
    public void setFieldValue_SetTo5_GetterShouldReturn5() {
        SudokuField sudokuField = new SudokuField();

        sudokuField.setFieldValue(5);
        assertEquals(sudokuField.getFieldValue(), 5);
    }

    @Test
    public void equals_CompareToItself_ShouldReturnTrue() {
        SudokuField sudokuField = new SudokuField();

        assertEquals(sudokuField, sudokuField);
    }

    @Test
    public void equals_CompareToInstanceOfDifferentClass_ShouldReturnFalse() {
        SudokuField sudokuField = new SudokuField();
        String string = "test";

        assertNotEquals(sudokuField, string);
    }

    @Test
    public void hashCode_TwoTheSameFields_ShouldReturnTheSameHash() {
        SudokuField sudokuField = new SudokuField(4);
        SudokuField sudokuField1 = new SudokuField(4);

        assertEquals(sudokuField.hashCode(), sudokuField1.hashCode(), "Hash for two fields with the same value should be the same.");
    }

    @Test
    public void hashCode_TwoDifferentFields_ShouldReturnTheDifferentHashes() {
        SudokuField sudokuField = new SudokuField(4);
        SudokuField sudokuField1 = new SudokuField(9);

        assertNotEquals(sudokuField.hashCode(), sudokuField1.hashCode(), "Hash for two fields with the different values should not be the same.");
    }

}