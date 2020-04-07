package pl.sudoku;

public class SudokuBox extends SudokuFieldGroup {
    /**
     * Ctor for sudoku Box.
     *
     * @param fields group to be merged as a Box
     */
    public SudokuBox(final SudokuField[] fields) {
        super(fields);
    }
}
