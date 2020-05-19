module pl.sudoku.daofactory {
    requires pl.sudoku.dao;
    requires pl.sudoku.model;
    requires pl.sudoku.jdbcsudokuboarddao;
    requires pl.sudoku.filesudokuboarddao;

    exports pl.sudoku.daofactory;
}