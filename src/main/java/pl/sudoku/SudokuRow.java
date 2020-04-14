package pl.sudoku;

public final class SudokuRow extends SudokuFieldGroup {
    /**
     * Ctor for sudoku row.
     *
     * @param fields sudoku fields row consists of
     */
    public SudokuRow(final SudokuField[] fields) {
        super(fields);
    }

    /**
     * Checks if two rows are the same.
     * <p>
     * Checks if values in the rows are the same
     * (then returns true) but returns false also when
     * given object is a different class, null or has
     * different size (for future development).
     *
     *
     * @param o object to compare
     * @return true if content of rows is the same, otherwise return false
     */
    @Override
    public boolean equals(final Object o) {

        if (!super.equals(o)) {
            return false;
        }

        return o.getClass() == getClass();
    }

    /**
     * Generates hash code of SudokuRow object.
     * Depends only on content of the group.
     * Hash of SudokuBox or SudokuColumn with the same content
     * is the same.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Provides an easy way to print out the row.
     *
     * @return formatted String representing sudoku row
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var i : this.getFields()) {
            sb.append(i.toString()).append(" ");
        }
        sb.append('\n');
        return sb.toString();
    }
}
