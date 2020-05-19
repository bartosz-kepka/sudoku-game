package pl.sudoku.jdbcsudokuboarddao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import pl.sudoku.model.BacktrackingSudokuSolver;
import pl.sudoku.model.BoardSizeEnum;
import pl.sudoku.model.SudokuBoard;

public class JdbcSudokuBoardDaoTest {

    @Test
    public void JdbcSudokuBoardDao_CorrectConnection_ShouldNowThrowAnyException() {
        assertDoesNotThrow(() -> new JdbcSudokuBoardDao("Test"));
    }

    @Test
     public void read_SaveBoard_ShouldLoadCorrectly() {
        try (JdbcSudokuBoardDao jdbcSudokuBoardDao = new JdbcSudokuBoardDao("Test")) {
            SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver(), BoardSizeEnum.CLASSIC);
            sudokuBoard.set(0,0, 1);
            jdbcSudokuBoardDao.write(sudokuBoard);

            SudokuBoard read = jdbcSudokuBoardDao.read();
            jdbcSudokuBoardDao.delete();
            assertEquals(sudokuBoard, read);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}