package sudoku;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudokuBoardTest {
    private SudokuBoard sudokuBoard;

    @BeforeEach
     void setUp() {
        sudokuBoard = new SudokuBoard();
    }

    @Test
    void get() {
        assertEquals(sudokuBoard.get(0, 0), 0);

    }

    @Test
    void set() {
        sudokuBoard.set(0, 0, 5);
        assertEquals(sudokuBoard.get(0, 0), 5);
    }

    @Test
    void solveGame() {
    }
}