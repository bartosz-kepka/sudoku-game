package pl.sudoku;

public class SudokuField {
    /**
     * Represents value stored inside sudoku field.
     */
    private int value;

    /**
     * Ctor for sudoku field.
     */
    public SudokuField() {
    }

    /**
     * Ctor for sudoku field with stating value.
     *
     * @param i to store inside field
     */
    public SudokuField(final int i) {
        this.value = i;
    }

    /**
     * Gets value stored in sudoku field.
     *
     * @return integer value
     */
    public int getFieldValue() {
        return value;
    }

    /**
     * Sets value of sudoku field.
     *
     * @param newValue to be stored
     */
    public void setFieldValue(final int newValue) {
        //TODO add BadValueException
        this.value = newValue;
    }
}

