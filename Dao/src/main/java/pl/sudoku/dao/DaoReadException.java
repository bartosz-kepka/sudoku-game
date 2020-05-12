package pl.sudoku.dao;

import java.io.IOException;

public class DaoReadException extends IOException {
    public DaoReadException(Throwable cause) {
        super(cause);
    }
}
