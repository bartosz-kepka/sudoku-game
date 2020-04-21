package pl.sudoku;

public interface Dao<T> extends AutoCloseable {
   /**
    * Read method for Dao interface.
    * @return object read from file
    */
    T read();


   /**
    * Write method for Dao interface.
    * @param obj to be written to file
    */
    void write(T obj);

    /**
     * Close method for AutoClosable interface.
     * @throws Exception if some resources cannot be closed
     */
    @Override
    void close() throws Exception;
}

