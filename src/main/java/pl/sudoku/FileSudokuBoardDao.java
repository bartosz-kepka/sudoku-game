package pl.sudoku;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    /**
     * String representing file with extension.
     */
    private String fileName;

    /**
     * Constructor for FileSudokuBoardDao class.
     *
     * @param fileString file with extension for IO operations
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
        SudokuBoard sudokuBoard = null;
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
}
