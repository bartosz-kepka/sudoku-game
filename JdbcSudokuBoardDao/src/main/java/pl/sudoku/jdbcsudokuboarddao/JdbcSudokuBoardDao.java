package pl.sudoku.jdbcsudokuboarddao;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sudoku.dao.Dao;
import pl.sudoku.dao.DaoCreateException;
import pl.sudoku.dao.DaoReadException;
import pl.sudoku.dao.DaoWriteException;
import pl.sudoku.model.BacktrackingSudokuSolver;
import pl.sudoku.model.BoardSizeEnum;
import pl.sudoku.model.SudokuBoard;

import java.sql.*;


public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {

    /**
     * String representing file with extension.
     */
    private final String fileName;

    private final static Logger LOGGER = LoggerFactory.getLogger(JdbcSudokuBoardDao.class);

    public JdbcSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    private Connection getDatabaseConnection(String URL) {
        Connection connection = null;
        try {
             connection = DriverManager.getConnection(URL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    @Override
    public SudokuBoard read() throws DaoReadException {
        SudokuBoard sudokuBoard;
        String DbUrl = "jdbc:derby:"+fileName;

        return null;
    }

    @Override
    public void write(SudokuBoard obj) throws DaoWriteException {

    }

    @Override
    public void close() throws Exception {

    }

    /**
     * Getter for fileName field.
     *
     * @return fileName string -> file with extension
     */
    public String getFileName() {
        return fileName;
    }
}
