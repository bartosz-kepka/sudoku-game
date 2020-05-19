package pl.sudoku.jdbcsudokuboarddao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pl.sudoku.dao.Dao;
import pl.sudoku.dao.DaoReadException;
import pl.sudoku.dao.DaoWriteException;
import pl.sudoku.model.SudokuBoard;


public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {

    private final Connection connection;

    private String saveName;

    public JdbcSudokuBoardDao(String saveName) throws JdcbDaoConnectException {
        this.saveName = saveName;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;" +
                    "databaseName=Sudoku;", "SA", "Password.2020");
        } catch (ClassNotFoundException | SQLException e) {
            throw new JdcbDaoConnectException(e);
        }
    }


    @Override
    public SudokuBoard read() throws DaoReadException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT board FROM " +
                "SudokuBoards WHERE savename = ?")) {
            preparedStatement.setString(1, saveName);
            ResultSet resultSet = preparedStatement.executeQuery();
            return (SudokuBoard) resultSet.getObject(1);
        } catch (SQLException e) {
            throw new JdbcDaoReadException(e);
        }
    }


    @Override
    public void write(SudokuBoard obj) throws DaoWriteException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " +
                "SudokuBoards VALUES (?,?)")) {
            preparedStatement.setString(1, saveName);
            preparedStatement.setObject(2, (Object) obj);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new JdbcDaoWriteException(e);
        }
    }

    public void delete() throws JdbcDaoDeleteException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " +
                "SudokuBoards WHERE savename = ?")) {
            preparedStatement.setString(1, saveName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new JdbcDaoDeleteException(e);
        }
    }


    @Override
    public void close() throws Exception {
        connection.close();
    }

    /**
     * Getter for fileName field.
     *
     * @return fileName string -> file with extension
     */
    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }
}
