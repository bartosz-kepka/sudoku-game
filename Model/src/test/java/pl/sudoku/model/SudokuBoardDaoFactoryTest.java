package pl.sudoku.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardDaoFactoryTest {

    @Test
    public void getFileDao_ShouldNotReturnNull() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        assertNotNull(factory.getFileDao("testfile.txt"));
    }
}