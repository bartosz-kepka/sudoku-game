package pl.sudoku.fxmodel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuFieldPlaceholder {
    /**
     * Integer value representing row of SudokuField in SudokuBoard.
     */
    private int row;

    /**
     * Integer value representing column of SudokuField in SudokuBoard.
     */
    private int column;

    /**
     * Integer value representing value of SudokuField in SudokuBoard.
     */
    private int value;

    /**
     * Constructor for SudokuFiledPlaceholder.
     * @param row of filed in SudokuBoard
     * @param column of filed in SudokuBoard
     * @param value stored in filed
     */
    public SudokuFieldPlaceholder(int row, int column, int value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }

    /**
     * Returns row value stored in placeholder.
     * @return row value
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets new row value.
     * @param row new value
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Returns column value stored in placeholder.
     * @return column value
     */
    public int getColumn() {
        return column;
    }

    /**
     * Sets new column value.
     * @param column new value
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Returns field value stored in placeholder.
     * @return field value
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets new filed value.
     * @param value new value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Checks if two placeholders are the same.
     * @param o object to compare
     * @return true if content is the same, otherwise return false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof SudokuFieldPlaceholder)) {
            return false;
        }

        SudokuFieldPlaceholder that = (SudokuFieldPlaceholder) o;

        return new EqualsBuilder()
                .append(row, that.row)
                .append(column, that.column)
                .append(value, that.value)
                .isEquals();
    }

    /**
     * Generates hash code of sudokuFieldPlaceholder.
     * Depends on row, column and value.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(row)
                .append(column)
                .append(value)
                .toHashCode();
    }

    /**
     * Stringifies all values and instance ID.
     * @return String value with ID of class instance
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("x", row)
                .append("y", column)
                .append("value", value)
                .toString();
    }
}
