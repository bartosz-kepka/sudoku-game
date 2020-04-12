package pl.sudoku;

public class SudokuRow extends SudokuFieldGroup {
    /**
     * Ctor for sudoku row.
     *
     * @param fields sudoku fields row consists of
     */
    public SudokuRow(final SudokuField[] fields) {
        super(fields);
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
