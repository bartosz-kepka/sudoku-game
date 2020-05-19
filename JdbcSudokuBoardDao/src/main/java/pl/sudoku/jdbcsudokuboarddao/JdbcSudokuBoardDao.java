package pl.sudoku.jdbcsudokuboarddao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pl.sudoku.dao.Dao;
import pl.sudoku.dao.DaoReadException;
import pl.sudoku.dao.DaoWriteException;
import pl.sudoku.model.SudokuBoard;


public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {

    private final Connection connection;

    private String saveName;

    /**
     * Method connecting Dao to database.
     * @param saveName string representing SudokuBaord Save
     * @throws JdbcDaoConnectException if unable to connect to database
     */
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

    /**
     * Method deleting SudokuBoard save from database.
     * @throws JdbcDaoDeleteException if database is unavailable or rows affected by delete == 0
     *
     */
    public void delete() throws JdbcDaoDeleteException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM "
                + "SudokuBoards WHERE savename = ?")) {
            preparedStatement.setString(1, saveName);
            if (preparedStatement.executeUpdate() != 1) {
                throw new JdbcDaoDeleteException(new IOException());
            }
        } catch (SQLException e) {
            throw new JdbcDaoDeleteException(e);
        }
    }

    public void update(SudokuBoard obj) throws DaoWriteException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE "
                + "SudokuBoards SET board = ? WHERE savename = ?")) {
            preparedStatement.setString(2, saveName);

            try (ByteArrayOutputStream boos = new ByteArrayOutputStream();
                 ObjectOutputStream oos = new ObjectOutputStream(boos)) {

                oos.writeObject(obj);
                preparedStatement.setBytes(1, boos.toByteArray());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException | IOException e) {
            throw new JdbcDaoWriteException(e);
        }
    }

    /**
     * Method returning ArrayList of saves.
     * @return ArrayList consisting of saveNames
     * @throws JdbcDaoReadException if unable to access data form database
     */
    public ArrayList<String> readAvailable() throws JdbcDaoReadException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT savename FROM SudokuBoards")) {
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<String> available = new ArrayList<String>();

            while (resultSet.next()) {
                available.add(resultSet.getString(1));
            }

            return available;
        } catch (SQLException e) {
            throw new JdbcDaoReadException(e);
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

    /**
     * Setter for fileName field.
     * @param saveName new fileName
     */
    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }
}
