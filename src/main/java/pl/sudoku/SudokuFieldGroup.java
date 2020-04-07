package pl.sudoku;

import java.util.Arrays;
import java.util.List;

public abstract class SudokuFieldGroup {
    /**
     * Group is stored as fixed-size list of sudoku fields
     */
    private List<SudokuField> fields;

    /**
     * Accessor for sudoku field group.
     *
     * @return number of elements in group
     */
    public int getSize() {
        return  fields.size();
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
                if (fields.get(i).getFieldValue() == fields.get(j).getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }
}
