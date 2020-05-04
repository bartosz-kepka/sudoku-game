package pl.sudoku.view;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.sudoku.dao.Dao;
import pl.sudoku.fxmodel.FXsudokuBoard;
import pl.sudoku.model.BacktrackingSudokuSolver;
import pl.sudoku.model.BoardSizeEnum;
import pl.sudoku.model.SudokuBoard;
import pl.sudoku.sudokuboarddao.SudokuBoardDaoFactory;

public class GameController implements Initializable {

    @FXML
    public Button cancelButton;

    /**
     * Closes current game and returns to main menu when "Cancel" button clicked.
     *
     * @param event onClick event
     */
    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        ResourceBundle bundle = ResourceBundle.getBundle("pl.sudoku.view/bundles.menu", locale);
        FXMLLoader loader = new FXMLLoader(App.class.getResource("menu.fxml"),bundle);
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
    public Button saveButton;

    /**
     * Saves game when "Save game" button clicked.
     *
     * @param event onClick Event
     */
    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        FileChooser fileChooser = new FileChooser();
        File saveFile = fileChooser.showSaveDialog(stage);

        if (saveFile != null) {
            try (Dao<SudokuBoard> fileSudokuBoardDao =
                         SudokuBoardDaoFactory.getFileDao(saveFile.getAbsolutePath())) {
                fileSudokuBoardDao.write(sudokuBoard.getSudokuBoardPlaceholder());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public GridPane sudokuGrid;

    FXsudokuBoard sudokuBoard;

    int boardSize;

    String[] possibleValues;

    private Locale locale;

    /**
     * Game controller constructor used for creating new game.
     *
     * @param gameDifficultyEnum chosen difficulty
     * @param boardSizeEnum      chosen board size
     */
    public GameController(GameDifficultyEnum gameDifficultyEnum, BoardSizeEnum boardSizeEnum) {
        sudokuBoard = new FXsudokuBoard(new SudokuBoard(new BacktrackingSudokuSolver(),
                boardSizeEnum));
        sudokuBoard.getSudokuBoardPlaceholder().solveGame();
        gameDifficultyEnum.clearSudokuFields(sudokuBoard.getSudokuBoardPlaceholder());
    }

    /**
     * Game controller constructor used for resuming saved game.
     *
     * @param saveFile save file to load game from
     */
    public GameController(File saveFile) {
        try (Dao<SudokuBoard> fileSudokuBoardDao =
                     SudokuBoardDaoFactory.getFileDao(saveFile.getAbsolutePath())) {
            sudokuBoard = new FXsudokuBoard(fileSudokuBoardDao.read());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        locale = resources.getLocale();
        cancelButton.setOnAction(this::handleCancelButtonAction);
        saveButton.setOnAction(this::handleSaveButtonAction);
        boardSize = sudokuBoard.getSudokuBoardPlaceholder().getBoardSize();
        generatePossibleValues();
        sudokuBoard.addPropertyChangeListener(
                FXsudokuBoard.FIELD_VALUE_PROPERTY, new ValueListener());
        fillSudokuGrid();
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

    /**
     * Creates proper number of rows and columns in sudokuGrid then fills grid with TextFields.
     */
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

        int maxDigitsInField = boardSize / 10 + 1;
        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                int fieldValue = sudokuBoard.get(row, column);
                TextField textField = SudokuTextFieldFactory.getSudokuTextField(fieldValue,
                        maxDigitsInField);
                addFieldValueListener(textField, row, column);
                sudokuGrid.add(textField, column, row);
            }
        }
    }

    /**
     * Searches for TextField in GridPane with given coordinates.
     *
     * @param row      number of row in which TextField is
     * @param column   number of column in which TextField is
     * @param gridPane GridPane in which TextField is
     * @return TextField at given position in GridPane
     */
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

    /**
     * Adds TextProperty ValueListener to given TextField.
     *
     * @param textField TextField to add ValueListener to
     * @param row number of row in which corresponding SudokuField from SudokuBoard is
     * @param column number of column in which corresponding SudokuField from SudokuBoard is
     */
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

    /**
     * Checks whether given value can be inserted into used SudokuBoard.
     *
     * @param newVal value to check
     * @return true is can be inserted, otherwise false
     */
    private boolean validateValue(String newVal) {
        for (String value : possibleValues) {
            if (value.equals(newVal)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generates string representations of values than can inserted into used SudokuBoard
     */
    private void generatePossibleValues() {
        possibleValues = new String[boardSize];
        for (int i = 0; i < possibleValues.length; i++) {
            possibleValues[i] = Integer.toString(i + 1);
        }
    }

}
