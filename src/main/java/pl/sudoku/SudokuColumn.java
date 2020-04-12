package pl.sudoku;

public class SudokuColumn extends SudokuFieldGroup {
    /**
     * Ctor for sudoku column.
     *
     * @param fields group to be merged into column
     */
    public SudokuColumn(final SudokuField[] fields) {
        super(fields);
    }

    /**
     * Provides an easy way to print out the box.
     *
     * @return formatted String representing sudoku box
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var i : this.getFields()) {
            sb.append(i.toString()).append('\n');
        }
        return sb.toString();
    }
}
