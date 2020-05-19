package pl.sudoku.jdbcsudokuboarddao;

import java.util.ResourceBundle;

public class JdbcDaoDeleteException extends Exception {
    ResourceBundle resourceBundle
            = ResourceBundle.getBundle("jdbcDaoExceptions");

    public JdbcDaoDeleteException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getLocalizedMessage() {
        return resourceBundle.getString("JdbcDeleteExceptionMessage");
    }
}
