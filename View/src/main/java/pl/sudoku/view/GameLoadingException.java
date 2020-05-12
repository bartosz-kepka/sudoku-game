package pl.sudoku.view;

import java.util.ResourceBundle;

public class GameLoadingException extends Exception {

    ResourceBundle resourceBundle
            = ResourceBundle.getBundle("pl.sudoku.view.bundles.controllerExceptions");

    public GameLoadingException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getLocalizedMessage() {
        return resourceBundle.getString("GameLoadingExceptionMessage");
    }
}
