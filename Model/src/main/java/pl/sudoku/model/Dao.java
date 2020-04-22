package pl.sudoku.model;

public interface Dao<T> {
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
}

