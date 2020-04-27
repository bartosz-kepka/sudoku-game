package pl.sudoku.sudokuboarddao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SudokuBoardDaoFactoryTest {

    @Test
    public void getFileDao_ShouldNotReturnNull() {
        Assertions.assertNotNull(SudokuBoardDaoFactory.getFileDao("testfile.txt"));
    }

    @Test
    public void constructor_ShouldNotThrowAnyException() {
        Assertions.assertDoesNotThrow(SudokuBoardDaoFactory::new);
    }
}