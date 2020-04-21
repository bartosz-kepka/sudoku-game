package pl.sudoku;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class FileSudokuBoardDaoTest {

    @Test
    void read_write_FromProperFile_ShouldNotThrowException() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudokuBoard2;

        try (Dao<SudokuBoard> fileSudokuBoardDao = factory.getFileDao("properFile")) {
            fileSudokuBoardDao.write(sudokuBoard);
            sudokuBoard2 = fileSudokuBoardDao.read();
            assertEquals(sudokuBoard, sudokuBoard2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void read_FromNonExistentFile_ShouldThrowException() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

        try (Dao<SudokuBoard> fileSudokuBoardDao = factory.getFileDao("nonExistent")) {
            assertThrows(RuntimeException.class, () -> fileSudokuBoardDao.read());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void write_ToImproperFile_ShouldThrowException() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());

        try (Dao<SudokuBoard> fileSudokuBoardDao = factory.getFileDao("/:;:")) {
            assertThrows(RuntimeException.class, () -> fileSudokuBoardDao.write(sudokuBoard));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    @Test
    void finalize_RunFinalizationAndWrite_ShouldThrowRuntimeException() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> fileSudokuBoardDao = factory.getFileDao("properFile");

        Method finalize = fileSudokuBoardDao.getClass().getDeclaredMethod("finalize");
        finalize.setAccessible(true);
        finalize.invoke(fileSudokuBoardDao);

        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());

        assertThrows(RuntimeException.class, () -> fileSudokuBoardDao.write(sudokuBoard));
    }
}