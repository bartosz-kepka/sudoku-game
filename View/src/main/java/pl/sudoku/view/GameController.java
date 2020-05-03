package pl.sudoku.view;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import pl.sudoku.fxmodel.FXsudokuBoard;
import pl.sudoku.model.BacktrackingSudokuSolver;
import pl.sudoku.model.BoardSizeEnum;
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

    GameDifficultyEnum gameDifficulty;
    FXsudokuBoard sudokuBoard;
    int boardSize;
    String[] possibleValues;

    public GameController(GameDifficultyEnum gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cancelButton.setOnAction(this::handleCancelButtonAction);
        levelLabel.setText("Level: " + gameDifficulty.toString());
        sudokuBoard = new FXsudokuBoard(new SudokuBoard(new BacktrackingSudokuSolver(),
                BoardSizeEnum.CLASSIC));
        boardSize = sudokuBoard.getSudokuBoardPlaceholder().getBoardSize();

        possibleValues = new String[boardSize];
        for (int i = 0; i < possibleValues.length; i++) {
            possibleValues[i] = Integer.toString(i + 1);
        }

        sudokuBoard.addPropertyChangeListener(
                FXsudokuBoard.FIELD_VALUE_PROPERTY, new ValueListener());
        initializeBoard();
    }

    class ValueListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            String propertyName = evt.getPropertyName();
            if (FXsudokuBoard.FIELD_VALUE_PROPERTY.equals(propertyName)) {
                IndexedPropertyChangeEvent event = (IndexedPropertyChangeEvent) evt;
                int row = event.getIndex() / boardSize;
                int column = event.getIndex() % boardSize;
                int newFieldValue = (int) event.getNewValue();
                TextField textField = getNodeByRowColumnIndex(row, column, sudokuGrid);
                textField.setText(Integer.toString(newFieldValue));
            }
        }
    }

    private void initializeBoard() {
        sudokuBoard.getSudokuBoardPlaceholder().solveGame();
        gameDifficulty.clearSudokuFields(sudokuBoard.getSudokuBoardPlaceholder());
        fillSudokuGrid();
    }

    private void fillSudokuGrid() {
        double textFieldPixelSize = 40.0 + boardSize / 10 * 10.0;

        for (int i = 0; i < boardSize; i++) {
            RowConstraints row = new RowConstraints(textFieldPixelSize, textFieldPixelSize,
                    textFieldPixelSize, Priority.ALWAYS, VPos.CENTER, true);
            ColumnConstraints column = new ColumnConstraints(textFieldPixelSize, textFieldPixelSize,
                    textFieldPixelSize, Priority.ALWAYS, HPos.CENTER, true);
            sudokuGrid.getRowConstraints().add(row);
            sudokuGrid.getColumnConstraints().add(column);
        }

        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                int fieldValue = sudokuBoard.get(row, column);
                TextField textField = SudokuTextFieldFactory.getSudokuTextField(fieldValue);
                addFieldValueListener(textField, row, column);
                sudokuGrid.add(textField, column, row);
            }
        }
    }

    private TextField getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null) {
                if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                    result = node;
                    break;
                }
            }
        }
        return (TextField) result;
    }

    private void addFieldValueListener(TextField textField, int row, int column) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue,
                                String oldVal, String newVal) {
                if (!validateValue(newVal)) {
                    Platform.runLater(textField::clear);
                } else {
                    sudokuBoard.set(row, column, Integer.parseInt(newVal));
                }
            }
        });
    }

    private boolean validateValue(String newVal) {
        for (String value : possibleValues) {
            if (value.equals(newVal)) {
                return true;
            }
        }
        return false;
    }
}
