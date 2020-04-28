package pl.sudoku.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class MenuController implements Initializable {

    @FXML
    public Button easyButton;

    @FXML
    private void handleEasyButtonAction(ActionEvent event) {
        loadBoardController(GameDifficulty.EASY);
    }

    @FXML
    public Button mediumButton;

    @FXML
    private void handleMediumButtonAction(ActionEvent event) {
        loadBoardController(GameDifficulty.MEDIUM);
    }

    @FXML
    public Button hardButton;

    @FXML
    private void handleHardButtonAction(ActionEvent event) {
        loadBoardController(GameDifficulty.HARD);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        easyButton.setOnAction(this::handleEasyButtonAction);
        mediumButton.setOnAction(this::handleMediumButtonAction);
        hardButton.setOnAction(this::handleHardButtonAction);
    }

    private void loadBoardController(GameDifficulty gameDifficulty) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("game.fxml"));
        GameController gameController = new GameController(gameDifficulty);
        loader.setController(gameController);

        try {
            Parent newRoot = loader.load();
            App.setScene(newRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
