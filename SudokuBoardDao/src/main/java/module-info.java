module pl.sudoku.sudokuboarddao {
    requires pl.sudoku.model;
    requires pl.sudoku.dao;

    opens pl.sudoku.sudokuboarddao;
    exports pl.sudoku.sudokuboarddao;
}