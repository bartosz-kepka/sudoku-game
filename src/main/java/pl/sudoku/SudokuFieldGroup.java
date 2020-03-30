package pl.sudoku;

public abstract class SudokuFieldGroup {
    /**
     * Represents Current size of fields merged into group.
     */
    public static final int SIZE = 9;
    /**
     * Group is stored as array of separate fields.
     */
    private SudokuField[] fields;

    /**
     * Ctor for SudokuFieldGroup.
     *
     * @param sudokuFields array of separate fields to be merged into group
     */
    public SudokuFieldGroup(final SudokuField[] sudokuFields) {
        this.fields = sudokuFields;
    }

    /**
     * Checks correctness if given field group.
     *
     *
     * @return boolean value if given field group is correct
     */
    public boolean verify() {
        for (int i = 0; i < SIZE; i++) {
            if (fields[i].getFieldValue() == 0) {
                continue;
            }
            for (int j = i + 1; j < SIZE; j++) {
                if (fields[i].getFieldValue() == fields[j].getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }
}
