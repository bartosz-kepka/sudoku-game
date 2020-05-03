package pl.sudoku.view;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
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

    // TestFX
    @Start
    private void start(Stage stage) {
        int fieldValue = 1;
        sudokuTextField = SudokuTextFieldFactory.getSudokuTextField(fieldValue, 1);
        stage.setScene(new Scene(new StackPane(sudokuTextField), 100, 100));
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
        TextField sudokuTextFieldTest = factory.getSudokuTextField(fieldValue, 1 );

        assertEquals("", sudokuTextFieldTest.getText());
    }

    // TestFX
    @Test
    void textFormatter_ChangeToImproperText_ShouldNotAllowChange(FxRobot robot) {
        robot.clickOn(sudokuTextField);
        robot.write("2");

        Assertions.assertThat(sudokuTextField).hasText("1");
    }
}