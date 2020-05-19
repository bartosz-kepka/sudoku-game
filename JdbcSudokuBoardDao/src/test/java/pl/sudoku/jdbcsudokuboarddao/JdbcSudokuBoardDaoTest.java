package pl.sudoku.jdbcsudokuboarddao;

import org.junit.Test;

 public class JdbcSudokuBoardDaoTest {

    @Test
    public void createDB_test(){
        JdbcSudokuBoardDao jdbcSudokuBoardDao = new JdbcSudokuBoardDao("ASD");
    }
}