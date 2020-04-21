package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileSudokuBoardDaoTest {

    @Test
    void read_write_fromProperFile_ShouldNotThrow_Exception() {
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
    void read_from_nonExistentFile_ShouldThrowException() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> fileSudokuBoardDao;
        fileSudokuBoardDao = factory.getFileDao("nonExistent");
        assertThrows(RuntimeException.class,()-> fileSudokuBoardDao.read());
    }
    @Test
    void write_to_ImproperFile_ShouldThrowException() {
        Dao<SudokuBoard> fileSudokuBoardDao;
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        fileSudokuBoardDao = factory.getFileDao("/:;:");
        assertThrows(RuntimeException.class,()-> fileSudokuBoardDao.write(sudokuBoard));
    }
}