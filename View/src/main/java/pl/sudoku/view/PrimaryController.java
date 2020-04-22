package pl.sudoku.view;

import java.io.IOException;
import javafx.fxml.FXML;
import pl.sudoku.model.*;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
