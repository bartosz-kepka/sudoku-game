module pl.sudoku.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires model;

    opens pl.sudoku.view to javafx.fxml;
    exports pl.sudoku.view;
}