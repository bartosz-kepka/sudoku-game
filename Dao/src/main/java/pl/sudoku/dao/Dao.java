package pl.sudoku.dao;

public interface Dao<T> extends AutoCloseable {
    /**
     * Read method for Dao interface.
     *
     * @return object read from file
     */
    T read();


    /**
     * Write method for Dao interface.
     *
     * @param obj to be written to file
     */
    void write(T obj);
}

