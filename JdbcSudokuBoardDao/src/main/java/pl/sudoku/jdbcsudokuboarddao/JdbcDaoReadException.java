package pl.sudoku.jdbcsudokuboarddao;

import pl.sudoku.dao.DaoReadException;

import java.util.ResourceBundle;

public class JdbcDaoReadException extends DaoReadException {

    ResourceBundle resourceBundle
            = ResourceBundle.getBundle("jdbcDaoExceptions");


    public JdbcDaoReadException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getLocalizedMessage() {
        return resourceBundle.getString("JdbcReadExceptionMessage");
    }
}
