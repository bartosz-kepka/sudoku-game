package pl.sudoku.dao;

import java.io.IOException;

public class DaoWriteException extends IOException {
    public DaoWriteException(Throwable cause) {
        super(cause);
    }
}
