package pl.sudoku;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class FileSudokuBoardDaoTest {

    @Test
    public void read_write_FromProperFile_ShouldNotThrowException() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudokuBoard2;
        Dao<SudokuBoard> fileSudokuBoardDao;

        fileSudokuBoardDao = factory.getFileDao("properFile");
        fileSudokuBoardDao.write(sudokuBoard);
        sudokuBoard2 = fileSudokuBoardDao.read();
        assertEquals(sudokuBoard,sudokuBoard2);
    }

    @Test
    public void read_FromNonExistentFile_ShouldThrowException() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> fileSudokuBoardDao;
        fileSudokuBoardDao = factory.getFileDao("nonExistent");
        assertThrows(RuntimeException.class,()-> fileSudokuBoardDao.read());
    }

    @Test
    public void write_ToImproperFile_ShouldThrowException() {
        Dao<SudokuBoard> fileSudokuBoardDao;
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        fileSudokuBoardDao = factory.getFileDao("/:;:");
        assertThrows(RuntimeException.class,()-> fileSudokuBoardDao.write(sudokuBoard));
    }

    @Test
    public void finalize_ShouldNotThrowAnyException() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> fileSudokuBoardDao = factory.getFileDao("Test");

        Method finalize = fileSudokuBoardDao.getClass().getDeclaredMethod("finalize");
        finalize.setAccessible(true);

        assertDoesNotThrow(() -> finalize.invoke(fileSudokuBoardDao));
    }
}