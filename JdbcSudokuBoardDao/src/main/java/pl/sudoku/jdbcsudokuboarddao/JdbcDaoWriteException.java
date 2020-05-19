package pl.sudoku.jdbcsudokuboarddao;

import java.util.ResourceBundle;
import pl.sudoku.dao.DaoWriteException;

public class JdbcDaoWriteException extends DaoWriteException {

    ResourceBundle resourceBundle
            = ResourceBundle.getBundle("pl.sudoku.jdbcsudokuboarddao.jdbcDaoExceptions");


    public JdbcDaoWriteException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getLocalizedMessage() {
        return resourceBundle.getString("JdbcWriteExceptionMessage");
    }
}
