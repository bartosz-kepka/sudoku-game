package pl.sudoku;


import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public final class FileSudokuBoardDao implements Dao<SudokuBoard> {

    /**
     * ObjectOutputStream for writing to file.
     */
    private ObjectOutputStream objectOutputStream;

    /**
     * ObjectInputStream for reading from file.
     */
    private ObjectInputStream objectInputStream;

    /**
     * Constructor for FileSudokuBoardDao class.
     *
     * @param fileName file with extension for IO operations
     */
    public FileSudokuBoardDao(final String fileName) {
        try {
            objectOutputStream =
                    new ObjectOutputStream(new FileOutputStream(fileName));
            objectInputStream =
                    new ObjectInputStream(new FileInputStream(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        try {
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
        try {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Close method for AutoClosable interface.
     *
     * @throws Exception if some resources cannot be closed
     */
    @Override
    public void close() throws Exception {
        objectInputStream.close();
        objectOutputStream.close();
    }

    /**
     * Deprecated in Java 9 finalize method.
     *
     * @throws Throwable if some resources cannot be closed
     */
    @Deprecated(since = "9")
    @Override
    protected void finalize() throws Throwable {
        objectInputStream.close();
        objectOutputStream.close();
        super.finalize();

    }
}
