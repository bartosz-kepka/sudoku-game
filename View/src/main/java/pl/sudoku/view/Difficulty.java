package pl.sudoku.view;

public enum Difficulty {
    EASY(3),
    MEDIUM(5),
    HARD(7);

    public final int multiplier;

    Difficulty(int multiplier) {
        this.multiplier = multiplier;
    }
}