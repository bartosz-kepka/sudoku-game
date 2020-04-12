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

    /**
     * Provides an easy way to print out the box.
     *
     * @return formatted String representing sudoku box
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.getFields().size(); i++) {
            sb.append(this.getFields().get(i)).append(" ");
            if ((i + 1) % Math.sqrt(this.getFields().size()) == 0) {
                sb.append('\n');
            }
        }
        return sb.toString();
    }
}
