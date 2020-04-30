package pl.sudoku.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import pl.sudoku.model.BacktrackingSudokuSolver;
import pl.sudoku.model.SudokuBoard;

public class GameController implements Initializable {

    @FXML
    public Button cancelButton;

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("menu.fxml"));
        MenuController primaryController = new MenuController();
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

    GameDifficulty gameDifficulty;
    SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());

    public GameController(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cancelButton.setOnAction(this::handleCancelButtonAction);
        levelLabel.setText("Level: " + gameDifficulty.toString());
        initializeBoard();
    }

    private void initializeBoard() {
        sudokuBoard.solveGame();
        gameDifficulty.clearSudokuFields(sudokuBoard);
        fillSudokuGrid();
    }

    private void fillSudokuGrid() {
        int boardSize = sudokuBoard.getBoardSize();

        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                int fieldValue = sudokuBoard.get(row, column);
                TextField textField = SudokuTextFieldFactory.getSudokuTextField(fieldValue);
                changeFiledValue(textField,row,column);
                sudokuGrid.add(textField, row, column);
            }
        }
    }

    private void changeFiledValue(TextField textField, int row, int column) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                if (!validateValue(newVal)) {
                    Platform.runLater(textField::clear);
                } else {
                    sudokuBoard.set(row, column, Integer.parseInt(newVal));

                }
            }
        });
    }

    private boolean validateValue(String newVal) {
        return newVal.length() == 1 & "123456789".contains(newVal);
    }
}
