package pl.sudoku.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import pl.sudoku.model.SudokuBoard;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    /**
     * String representing file with extension.
     */
    private String fileName;

    /**
     * Constructor for FileSudokuBoardDao class.
     *
     * @param fileString file for IO operations
     */
    public FileSudokuBoardDao(final String fileString) {
        this.fileName = fileString;
    }

    /**
     * Implementation of Dao interface read method.
     * Uses try-with-resources instruction.
     *
     * @return SudokuBoard obj read from file
     */
    @Override
    public SudokuBoard read() {
        SudokuBoard sudokuBoard;
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream =
                     new ObjectInputStream(fileInputStream)) {
            sudokuBoard = (SudokuBoard) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        return sudokuBoard;
    }

    /**
     * Implementation of Dao interface write method.
     * Uses try-with-resources instruction.
     *
     * @param obj SudokuBoard to be serialized
     */
    @Override
    public void write(final SudokuBoard obj) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Implementation of AutoCloseable.close() method.
     *
     * @throws Exception if error occurred when closing resources
     */
    @Override
    public void close() throws Exception {

    }

    /**
     * Deprecated finalize method for emergency closing streams.
     *
     * @throws Throwable exception if unable to close resources
     */
    @Deprecated(since = "9")
    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }
}
