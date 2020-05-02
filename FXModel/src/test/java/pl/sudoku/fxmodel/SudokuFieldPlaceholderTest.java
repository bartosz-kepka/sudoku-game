package pl.sudoku.fxmodel;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldPlaceholderTest {

    @Test
    void getX_ShouldReturnProper_Value() {
        SudokuFieldPlaceholder fieldPlaceholder = new SudokuFieldPlaceholder(1, 2, 3);
        assertEquals(1, fieldPlaceholder.getRow());
    }

    @Test
    void getY_ShouldReturnProper_Value() {
        SudokuFieldPlaceholder fieldPlaceholder = new SudokuFieldPlaceholder(1, 2, 3);
        assertEquals(2, fieldPlaceholder.getColumn());
    }

    @Test
    void getValue_ShouldReturnProper_Value() {
        SudokuFieldPlaceholder fieldPlaceholder = new SudokuFieldPlaceholder(1, 2, 3);
        assertEquals(3, fieldPlaceholder.getValue());
    }

    @Test
    void setX_ShouldSetProper_Value() {
        SudokuFieldPlaceholder fieldPlaceholder = new SudokuFieldPlaceholder(1, 2, 3);
        fieldPlaceholder.setRow(9);
        assertEquals(9, fieldPlaceholder.getRow());
    }

    @Test
    void setY_ShouldSetProper_Value() {
        SudokuFieldPlaceholder fieldPlaceholder = new SudokuFieldPlaceholder(1, 2, 3);
        fieldPlaceholder.setColumn(9);
        assertEquals(9, fieldPlaceholder.getColumn());
    }

    @Test
    void setValue_ShouldSetProper_Value() {
        SudokuFieldPlaceholder fieldPlaceholder = new SudokuFieldPlaceholder(1, 2, 3);
        fieldPlaceholder.setValue(9);
        assertEquals(9, fieldPlaceholder.getValue());
    }

    @Test
    public void equals_CompareToNull_ShouldReturnFalse() {
        SudokuFieldPlaceholder fieldPlaceholder = new SudokuFieldPlaceholder(1, 2, 3);

        assertNotEquals(null, fieldPlaceholder);
    }

    @Test
    public void equals_CompareToString_ShouldReturnFalse() {
        SudokuFieldPlaceholder fieldPlaceholder = new SudokuFieldPlaceholder(1, 2, 3);

        String test = "Test";

        assertNotEquals(fieldPlaceholder, test);
    }

    @Test
    public void equals_CompareToItself_ShouldReturnTrue() {
        SudokuFieldPlaceholder fieldPlaceholder = new SudokuFieldPlaceholder(1, 2, 3);

        assertEquals(fieldPlaceholder, fieldPlaceholder);
    }



    @Test
    public void hashCode_TwoTheSameFields_ShouldReturnTheSameHash() {
        SudokuFieldPlaceholder fieldPlaceholder1 = new SudokuFieldPlaceholder(1, 2, 3);
        SudokuFieldPlaceholder fieldPlaceholder2 = new SudokuFieldPlaceholder(1, 2, 3);

        assertEquals(fieldPlaceholder1.hashCode(), fieldPlaceholder2.hashCode(), "Hash for two empty boards with the same size should be the same.");
    }

    @Test
    public void hashCode_TwoDifferentBoards_ShouldReturnTheDifferentHashes() {
        SudokuFieldPlaceholder fieldPlaceholder1 = new SudokuFieldPlaceholder(1, 2, 3);
        SudokuFieldPlaceholder fieldPlaceholder2 = new SudokuFieldPlaceholder(4, 5, 6);



        assertNotEquals(fieldPlaceholder1.hashCode(), fieldPlaceholder2.hashCode(), "Hash for two filled boards should be different.");
    }

    @Test
    public void toString_EmptyBoard_ReturnsProperValues() {
        SudokuFieldPlaceholder fieldPlaceholder1 = new SudokuFieldPlaceholder(1, 2, 3);


        assertThat(fieldPlaceholder1.toString(), containsString("value=3"));
    }

}