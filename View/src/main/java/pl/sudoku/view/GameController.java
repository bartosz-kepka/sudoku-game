package pl.sudoku.view;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import pl.sudoku.model.BacktrackingSudokuSolver;
import pl.sudoku.model.SudokuBoard;

public class BoardController implements Initializable {

    @FXML
    public Button cancelButton;

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("menu.fxml"));
        menuController primaryController = new menuController();
        loader.setController(primaryController);
        try {
            Parent newRoot = loader.load();
            App.setScene(newRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public Label levelLabel;

    @FXML
    public GridPane sudokuGrid;

    Difficulty difficultyLevel;
    SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());

    public BoardController(Difficulty difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cancelButton.setOnAction(this::handleCancelButtonAction);
        levelLabel.setText("Level: " + difficultyLevel.toString());
        initializeBoard();
    }

    private void initializeBoard() {
        sudokuBoard.solveGame();
        clearSudokuFields();
        fillSudokuGrid();
    }

    private void fillSudokuGrid() {
        int boardSize = sudokuBoard.getBoardSize();

        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                int fieldValue = sudokuBoard.get(row, column);
                sudokuGrid.add(createSudokuTextField(fieldValue), row, column);
            }
        }
    }

    private void clearSudokuFields() {
        int boardSize = sudokuBoard.getBoardSize();
        int fieldsToClear = boardSize * difficultyLevel.multiplier;

        for (int fieldsCleared = 0; fieldsCleared < fieldsToClear; ) {
            Random random = new Random();
            int row;
            int column;
            row = random.nextInt(boardSize);
            column = random.nextInt(boardSize);

            if (sudokuBoard.get(row, column) != 0) {
                sudokuBoard.set(row, column, 0);
                fieldsCleared++;
            }
        }
    }

    private TextField createSudokuTextField(int fieldValue) {
        TextField sudokuTextField = new TextField();

        if (fieldValue != 0) {
            sudokuTextField.setText(Integer.toString(fieldValue));
        }

        sudokuTextField.setAlignment(Pos.CENTER);
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
