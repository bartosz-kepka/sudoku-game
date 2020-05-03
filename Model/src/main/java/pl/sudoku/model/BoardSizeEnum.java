package pl.sudoku.model;

public enum BoardSizeEnum {
    SMALL(4),
    CLASSIC(9),
    LARGE(16);

    private final int size;

    BoardSizeEnum(int size) {
        this.size = size;
    }

    /**
     * Returns size of sudoku board.
     *
     * @return size of board
     */
    public int getSize() {
        return size;
    }
}
