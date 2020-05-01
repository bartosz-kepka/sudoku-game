module pl.sudoku.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive pl.sudoku.model;
    requires transitive pl.sudoku.sudokuboarddao;
    requires transitive pl.sudoku.dao;
    requires transitive pl.sudoku.FXModel;

    opens pl.sudoku.view to javafx.fxml;
    exports pl.sudoku.view;
}