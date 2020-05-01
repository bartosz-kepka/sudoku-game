package pl.sudoku.FXModel;

public class SudokuFieldPlaceholder {
    private int x;
    private int y;
    private int value;

    public SudokuFieldPlaceholder() {
        this.x = 0;
        this.y = 0;
        this.value = 0;
    }

    public SudokuFieldPlaceholder(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
