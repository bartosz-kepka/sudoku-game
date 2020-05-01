package pl.sudoku.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import pl.sudoku.FXModel.FXSudokuBoard;
import pl.sudoku.FXModel.SudokuFieldPlaceholder;
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
    FXSudokuBoard sudokuBoard = new FXSudokuBoard(new SudokuBoard(new BacktrackingSudokuSolver()));


    public GameController(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cancelButton.setOnAction(this::handleCancelButtonAction);
        levelLabel.setText("Level: " + gameDifficulty.toString());
        sudokuBoard.addPropertyChangeListener("value", new ValueListener());
        initializeBoard();
    }

    class ValueListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            String propertyName = evt.getPropertyName();
            if ("value".equals(propertyName)) {
//               String value = Integer.toString(((SudokuFieldPlaceholder) evt.getNewValue()).getValue());
//                (getNodeByRowColumnIndex(((SudokuFieldPlaceholder) evt.getNewValue()).getX(),((SudokuFieldPlaceholder) evt.getNewValue()).getY(),sudokuGrid)).setText(value);
                sudokuGrid.add(SudokuTextFieldFactory.getSudokuTextField(((SudokuFieldPlaceholder) evt.getNewValue()).getValue()),
                        ((SudokuFieldPlaceholder) evt.getNewValue()).getX(), ((SudokuFieldPlaceholder) evt.getNewValue()).getY());
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

        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                int fieldValue = sudokuBoard.get(row, column);
                TextField textField = SudokuTextFieldFactory.getSudokuTextField(fieldValue);
                addFieldValueListener(textField, row, column);
                sudokuGrid.add(textField, row, column);
            }
        }
    }

    public TextField getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            System.out.println(GridPane.getRowIndex(node)+ " " +GridPane.getRowIndex(node));
            if(GridPane.getRowIndex(node) == row & GridPane.getRowIndex(node) == column) {
                result = node;
                break;
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
