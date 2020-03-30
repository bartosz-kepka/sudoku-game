package pl.sudoku;

public class SudokuBox extends SudokuFieldGroup {
    /**
     * Represents Max SudokuBox height/width.
     */
    public static final int BOX_SIZE = 3;

    /**
     * Ctor for sudoku Box.
     * @param fields group to be merged as a Box
     */
    public SudokuBox(final SudokuField[] fields) {
        super(fields);
    }
}
