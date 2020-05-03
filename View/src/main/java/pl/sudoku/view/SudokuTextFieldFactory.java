package pl.sudoku.view;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.text.Font;

public class SudokuTextFieldFactory {

    /**
     * Creates JavaFX TextField corresponding to single field in sudoku board.
     *
     * @param fieldValue value to display in TextField
     * @return Formatted TextField with input length restriction
     */
    public static TextField getSudokuTextField(int fieldValue) {
        TextField sudokuTextField = new TextField();

        if (fieldValue != 0) {
            sudokuTextField.setText(Integer.toString(fieldValue));
        }

        sudokuTextField.setAlignment(Pos.CENTER);
        sudokuTextField.setPrefSize(50.0, 50.0);
        sudokuTextField.setFont(Font.font(20.0));
        sudokuTextField.setTextFormatter(
                new TextFormatter<String>((TextFormatter.Change change) -> {
                    String newText = change.getControlNewText();
                     if (newText.length() > 1) {
                        return null;
                    } else {
                        return change;
                    }
                }));

        return sudokuTextField;
    }
}
