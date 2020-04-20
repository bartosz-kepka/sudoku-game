package pl.sudoku;

public class SudokuBoardDaoFactory {

    /**
     * Factory method to create Dao class for SudokuBoard.
     *
     * @param filename serialization file
     * @return instance of Dao class
     */
    public Dao<SudokuBoard> getFileDao(final String filename) {
        return new FileSudokuBoardDao(filename);
    }
}
