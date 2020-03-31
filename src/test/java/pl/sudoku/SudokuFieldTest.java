package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

}