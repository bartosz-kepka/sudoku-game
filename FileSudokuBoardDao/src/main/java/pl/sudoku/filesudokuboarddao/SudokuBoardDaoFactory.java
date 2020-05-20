package pl.sudoku.filesudokuboarddao;

import pl.sudoku.dao.Dao;
import pl.sudoku.jdbcsudokuboarddao.JdbcDaoConnectException;
import pl.sudoku.jdbcsudokuboarddao.JdbcSudokuBoardDao;
import pl.sudoku.model.SudokuBoard;

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

    public static Dao<SudokuBoard> getDatabaseDao(final String filename) throws
            JdbcDaoConnectException {
        return new JdbcSudokuBoardDao(filename);
    }

    private SudokuBoardDaoFactory() {}
}
