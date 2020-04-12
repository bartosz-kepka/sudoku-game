package pl.sudoku;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Arrays;
import java.util.List;

public abstract class SudokuFieldGroup {


    /**
     * Group is stored as fixed-size list of sudoku fields.
     */
    private List<SudokuField> fields;


    /**
     * Getter for fields returning copy of List that stores them.
     *
     * @return copy of fields list
     */
    protected List<SudokuField> getFields() {
        return List.copyOf(fields);
    }

    /**
     * Accessor for sudoku field group.
     *
     * @return number of elements in group
     */
    public int getSize() {
        return fields.size();
    }

    /**
     * Ctor for SudokuFieldGroup.
     *
     * @param sudokuFields array of separate fields to be merged into group
     */
    public SudokuFieldGroup(final SudokuField[] sudokuFields) {
        this.fields = Arrays.asList(sudokuFields);
    }

    /**
     * Checks correctness if given field group.
     *
     * @return boolean value if given field group is correct
     */
    public boolean verify() {
        for (int i = 0; i < getSize(); i++) {
            if (fields.get(i).getFieldValue() == 0) {
                continue;
            }
            for (int j = i + 1; j < getSize(); j++) {
                if (fields.get(i).getFieldValue()
                        == fields.get(j).getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if two field groups are the same.
     * <p>
     * Checks if values in the groups are the same
     * (then returns true) but returns false also when
     * given object is a different class, null or has
     * different group size (for future development).
     *
     *
     * @param o object to compare
     * @return true if content of list is the same, otherwise return false
     */
    @Override
    public boolean equals(final Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof SudokuFieldGroup)) {
            return false;
        }

        SudokuFieldGroup that = (SudokuFieldGroup) o;

        return new EqualsBuilder()
                .append(fields, that.fields)
                .isEquals();
    }


    /**
     * Generates hash code of SudokuFieldGroup object.
     * Depends only on content of the group.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(fields)
                .toHashCode();
    }
}
