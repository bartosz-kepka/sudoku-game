package pl.sudoku.jdbcsudokuboarddao;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sudoku.dao.Dao;
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
    private Connection connection;

    private final String fileName;

    private final static Logger LOGGER = LoggerFactory.getLogger(JdbcSudokuBoardDao.class);

    public JdbcSudokuBoardDao(String fileName) {

        this.fileName = fileName;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=Sudoku;","sa","root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public SudokuBoard read() throws DaoReadException {
        return null;
    }

    @Override
    public void write(SudokuBoard obj) throws DaoWriteException {
        try(PreparedStatement statement = connection.prepareStatement("INSERT INTO SudokuBoards(savename, fields) VALUES (?,?)" )) {

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
