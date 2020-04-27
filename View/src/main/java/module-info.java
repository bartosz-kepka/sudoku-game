module pl.sudoku.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive pl.sudoku.model;
    requires transitive pl.sudoku.sudokuboarddao;
    requires transitive pl.sudoku.dao;

    opens pl.sudoku.view;
    exports pl.sudoku.view;
}