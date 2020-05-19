package pl.sudoku.jdbcsudokuboarddao;

import java.util.ResourceBundle;

public class JdcbDaoConnectException extends Exception {
    ResourceBundle resourceBundle
            = ResourceBundle.getBundle("pl.sudoku.jdbcsudokuboarddao.jdbcDaoExceptions");


    public JdcbDaoConnectException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getLocalizedMessage() {
        return resourceBundle.getString("JdbcConnectExceptionMessage");
    }
}
