package pl.sudoku.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sudoku.model.BoardSizeEnum;

public class MenuController implements Initializable {

    private static Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

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

    @FXML
    public Button langEN;

    @FXML
    private void handleLangEnButtonAction(ActionEvent event) {
        locale = new Locale("en");
        changeUiLanguage(locale);
    }

    @FXML
    public Button langPL;

    @FXML
    private void handleLangPlButtonAction(ActionEvent event) {
        locale = new Locale("pl");
        changeUiLanguage(locale);
    }

    @FXML
    public Label authorOne;

    @FXML
    public Label authorTwo;

    private Locale locale;

    private void changeUiLanguage(Locale locale) {
        Locale.setDefault(locale);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("pl.sudoku.view.bundles.menu",
                locale);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"), resourceBundle);

        try {
            loader.setController(this);
            Parent root = loader.load();
            App.setScene(root);
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getStackTrace(e));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        locale = resources.getLocale();
        langEN.setOnAction(this::handleLangEnButtonAction);
        langPL.setOnAction(this::handleLangPlButtonAction);
        easyButton.setOnAction(this::handleEasyButtonAction);
        mediumButton.setOnAction(this::handleMediumButtonAction);
        hardButton.setOnAction(this::handleHardButtonAction);
        loadButton.setOnAction(this::handleLoadButtonAction);
        exitButton.setOnAction(this::handleExitButtonAction);
        smallRadioButton.setUserData(BoardSizeEnum.SMALL);
        classicRadioButton.setUserData(BoardSizeEnum.CLASSIC);
        largeRadioButton.setUserData(BoardSizeEnum.LARGE);
        setAuthors(locale);
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
            try {
                GameController gameController = new GameController(saveFile);
                openGameView(gameController);
            } catch (GameLoadingException e) {
                LOGGER.error(ExceptionUtils.getStackTrace(e));
            }
        }
    }

    private void openGameView(GameController gameController) {
        ResourceBundle bundle = ResourceBundle.getBundle("pl.sudoku.view.bundles.game", locale);
        FXMLLoader loader = new FXMLLoader(App.class.getResource("game.fxml"), bundle);
        loader.setController(gameController);

        try {
            Parent newRoot = loader.load();
            App.setScene(newRoot);
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getStackTrace(e));
        }
    }

    private void setAuthors(Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(
                "pl.sudoku.view.bundles.Authors", locale);

        authorOne.setText(resourceBundle.getString("224326"));
        authorTwo.setText(resourceBundle.getString("224248"));
    }

}
