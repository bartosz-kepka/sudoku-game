package pl.sudoku.view;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
class SudokuTextFieldFactoryTest {

    private TextField sudokuTextField;
    private TextField sudokuTextField1;


    // TestFX
    @Start
    private void start(Stage stage) {
        sudokuTextField = SudokuTextFieldFactory.getSudokuTextField(1,
                SudokuTextFieldFactory.MaxDigitsEnum.ONE);
        sudokuTextField1 = SudokuTextFieldFactory.getSudokuTextField(0,
                SudokuTextFieldFactory.MaxDigitsEnum.ONE);
        stage.setScene(new Scene(new VBox(sudokuTextField, sudokuTextField1), 200, 100));
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    @Test
    void getSudokuTextField_ProperValue_ShouldReturnProperTextField() {
        assertEquals("1", sudokuTextField.getText());
    }

    @Test
    void getSudokuTextField_ZeroValue_ShouldReturnTextFieldWithNoText() {
        SudokuTextFieldFactory factory = new SudokuTextFieldFactory();
        int fieldValue = 0;
        TextField sudokuTextFieldTest = factory.getSudokuTextField(fieldValue,
                SudokuTextFieldFactory.MaxDigitsEnum.ONE);

        assertEquals("", sudokuTextFieldTest.getText());
    }

    // TestFX
    @Test
    void textFormatter_ChangeToImproperText_ShouldNotAllowChange(FxRobot robot) {
        robot.clickOn(sudokuTextField);
        robot.write("2");

        Assertions.assertThat(sudokuTextField).hasText("1");
    }

    // TestFX
    @Test
    void textFormatter_ChangeToProperText_ShouldAllowChange(FxRobot robot) {
        robot.clickOn(sudokuTextField1);
        robot.write("2");

        Assertions.assertThat(sudokuTextField1).hasText("2");
    }
}