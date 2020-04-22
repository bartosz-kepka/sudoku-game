package pl.sudoku.model;

public class SudokuBoardDaoFactory {

    /**
     * Factory method to create Dao class for SudokuBoard.
     *
     * @param filename serialization file
     * @return instance of Dao class
     */
    public static Dao<SudokuBoard> getFileDao(final String filename) {
        return new FileSudokuBoardDao(filename);
    }
}
