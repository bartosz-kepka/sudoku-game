package pl.sudoku;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class SudokuField {
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
     * @param newValue to store inside field
     */
    public SudokuField(final int newValue) {
        this.value = newValue;
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

    @Override
    public boolean equals(final Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof SudokuField)) {
            return false;
        }

        SudokuField that = (SudokuField) o;

        return new EqualsBuilder()
                .append(value, that.value)
                .isEquals();
    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(17, 37)
                .append(value)
                .toHashCode();
    }
}

