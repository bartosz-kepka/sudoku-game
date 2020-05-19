open module JdbcSudokuBoardDaoProject {
    requires pl.sudoku.model;
    requires pl.sudoku.dao;
    requires java.sql;
    requires slf4j.api;
    requires org.apache.commons.lang3;
    requires com.microsoft.sqlserver.jdbc;

    exports pl.sudoku.jdbcsudokuboarddao;
}