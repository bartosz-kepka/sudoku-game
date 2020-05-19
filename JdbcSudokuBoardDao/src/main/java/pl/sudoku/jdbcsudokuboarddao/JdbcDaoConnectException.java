package pl.sudoku.jdbcsudokuboarddao;

import java.util.ResourceBundle;

public class JdbcDaoConnectException extends Exception {
    ResourceBundle resourceBundle
            = ResourceBundle.getBundle("pl.sudoku.jdbcsudokuboarddao.jdbcDaoExceptions");


    public JdbcDaoConnectException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getLocalizedMessage() {
        return resourceBundle.getString("JdbcConnectExceptionMessage");
    }
}
