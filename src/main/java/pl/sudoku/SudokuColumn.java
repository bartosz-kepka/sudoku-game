package pl.sudoku;

public class SudokuColumn extends SudokuFieldGroup {
    /**
     * Ctor for sudoku column.
     * @param fields group to be merged into column
     */
    public SudokuColumn(final SudokuField[] fields) {
        super(fields);
    }
}
