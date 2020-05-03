package pl.sudoku.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.sudoku.model.BoardSizeEnum;

public class MenuController implements Initializable {

    @FXML
    public ToggleGroup size;

    @FXML
    public RadioButton smallRadioButton;

    @FXML
    public RadioButton classicRadioButton;

    @FXML
    public RadioButton largeRadioButton;

    @FXML
    public Button easyButton;

    @FXML
    private void handleEasyButtonAction(ActionEvent event) {
        startNewGame(GameDifficultyEnum.EASY);
    }

    @FXML
    public Button mediumButton;

    @FXML
    private void handleMediumButtonAction(ActionEvent event) {
        startNewGame(GameDifficultyEnum.MEDIUM);
    }

    @FXML
    public Button hardButton;

    @FXML
    private void handleHardButtonAction(ActionEvent event) {
        startNewGame(GameDifficultyEnum.HARD);
    }

    @FXML
    public Button loadButton;

    @FXML
    private void handleLoadButtonAction(ActionEvent event) {
        loadGame();
    }

    @FXML
    public Button exitButton;

    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        easyButton.setOnAction(this::handleEasyButtonAction);
        mediumButton.setOnAction(this::handleMediumButtonAction);
        hardButton.setOnAction(this::handleHardButtonAction);
        loadButton.setOnAction(this::handleLoadButtonAction);
        exitButton.setOnAction(this::handleExitButtonAction);
        smallRadioButton.setUserData(BoardSizeEnum.SMALL);
        classicRadioButton.setUserData(BoardSizeEnum.CLASSIC);
        largeRadioButton.setUserData(BoardSizeEnum.LARGE);
    }

    private void startNewGame(GameDifficultyEnum gameDifficulty) {
        BoardSizeEnum boardSizeEnum = (BoardSizeEnum) size.getSelectedToggle().getUserData();
        GameController gameController = new GameController(gameDifficulty, boardSizeEnum);
        openGameView(gameController);
    }

    private void loadGame() {
        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        stage.setAlwaysOnTop(true);
        FileChooser fileChooser = new FileChooser();
        File saveFile = fileChooser.showOpenDialog(stage);

        if (saveFile != null) {
            GameController gameController = new GameController(saveFile);
            openGameView(gameController);
        }
    }

    private void openGameView(GameController gameController) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("game.fxml"));
        loader.setController(gameController);

        try {
            Parent newRoot = loader.load();
            App.setScene(newRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
