open module pl.sudoku.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires transitive pl.sudoku.sudokuboarddao;
    requires transitive pl.sudoku.dao;
    requires transitive pl.sudoku.fxmodel;
    requires java.desktop;

    exports pl.sudoku.view;
    exports pl.sudoku.view.bundles;
}