package pl.sudoku.jdbcsudokuboarddao;

import pl.sudoku.dao.Dao;
import pl.sudoku.dao.DaoReadException;
import pl.sudoku.dao.DaoWriteException;
import pl.sudoku.model.SudokuBoard;

import java.io.*;
import java.sql.*;


public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {

    private final Connection connection;

    private String saveName;

    public JdbcSudokuBoardDao(String saveName) throws JdbcDaoConnectException {
        this.saveName = saveName;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
                    + "databaseName=Sudoku;", "SA", "Password.2020");
        } catch (ClassNotFoundException | SQLException e) {
            throw new JdbcDaoConnectException(e);
        }
    }

    @Override
    public SudokuBoard read() throws DaoReadException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT board FROM "
                + "SudokuBoards WHERE savename = ?")) {
            preparedStatement.setString(1, saveName);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            byte[] input = resultSet.getBytes(1);

            try (ByteArrayInputStream bais = new ByteArrayInputStream(input);
                 ObjectInputStream objectIn = new ObjectInputStream(bais)) {

                return (SudokuBoard) objectIn.readObject();
            }

        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new JdbcDaoReadException(e);
        }
    }

    @Override
    public void write(SudokuBoard obj) throws DaoWriteException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO "
                + "SudokuBoards VALUES (?,?)")) {
            preparedStatement.setString(1, saveName);

            try (ByteArrayOutputStream boos = new ByteArrayOutputStream();
                 ObjectOutputStream oos = new ObjectOutputStream(boos)) {

                oos.writeObject(obj);
                preparedStatement.setBytes(2, boos.toByteArray());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException | IOException e) {
            throw new JdbcDaoWriteException(e);
        }
    }

    public void delete() throws JdbcDaoDeleteException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM "
                + "SudokuBoards WHERE savename = ?")) {
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
