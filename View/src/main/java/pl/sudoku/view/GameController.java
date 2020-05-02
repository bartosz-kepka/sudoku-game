package pl.sudoku.view;

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
import pl.sudoku.fxmodel.SudokuFieldPlaceholder;
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
    FXsudokuBoard sudokuBoard = new FXsudokuBoard(new SudokuBoard(new BacktrackingSudokuSolver()));



    public GameController(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cancelButton.setOnAction(this::handleCancelButtonAction);
        levelLabel.setText("Level: " + gameDifficulty.toString());
        sudokuBoard = new FXsudokuBoard(new SudokuBoard(new BacktrackingSudokuSolver()));
        sudokuBoard.addPropertyChangeListener("value", new ValueListener());
        initializeBoard();
    }

    class ValueListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            String propertyName = evt.getPropertyName();
            if ("value".equals(propertyName)) {
                SudokuFieldPlaceholder evtNewValue = (SudokuFieldPlaceholder) evt.getNewValue();
                int row = evtNewValue.getRow();
                int column = evtNewValue.getColumn();
                int newFieldValue = evtNewValue.getValue();
                TextField textField = getNodeByRowColumnIndex(row, column, sudokuGrid);
                textField.setText(Integer.toString(newFieldValue));

                System.out.println("EventFired");
            }
        }
    }

    private void initializeBoard() {
        sudokuBoard.getSudokuBoardPlaceholder().solveGame();
        gameDifficulty.clearSudokuFields(sudokuBoard.getSudokuBoardPlaceholder());
        fillSudokuGrid();
    }

    private void fillSudokuGrid() {
        int boardSize = sudokuBoard.getSudokuBoardPlaceholder().getBoardSize();

        for (int i = 0; i < 9; i++) {
            RowConstraints row = new RowConstraints(40.0, 40.0, 40.0, Priority.SOMETIMES,
                    VPos.CENTER, true);
            ColumnConstraints column = new ColumnConstraints(40.0, 40.0, 40.0, Priority.SOMETIMES
                    , HPos.CENTER, true);
            sudokuGrid.getRowConstraints().add(row);
            sudokuGrid.getColumnConstraints().add(column);
        }


        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                int fieldValue = sudokuBoard.get(row, column);
                TextField textField = SudokuTextFieldFactory.getSudokuTextField(fieldValue);
                addFieldValueListener(textField, row, column);
                sudokuGrid.add(textField, row, column);
            }
        }
    }

    public TextField getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null) {
                System.out.println(GridPane.getRowIndex(node) + " " + GridPane.getColumnIndex(node));
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
