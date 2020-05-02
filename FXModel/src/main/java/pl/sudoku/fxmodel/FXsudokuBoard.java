package pl.sudoku.fxmodel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.sudoku.model.SudokuBoard;

public class FXsudokuBoard {

    /**
     * PropertyChangeSupport for implementing Observer/Observable pattern.
     */
    private final transient PropertyChangeSupport propertyChangeSupport;

    /**
     * Placeholder containing SudokuBoard instance for integration
     * with javaFX bidirectional binding.
     */
    private SudokuBoard sudokuBoardPlaceholder;

    /**
     * Constructor for FXsudokuBoard.
     * @param sudokuBoardPlaceholder SudokuBoard to integrate
     */
    public FXsudokuBoard(SudokuBoard sudokuBoardPlaceholder) {
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        this.sudokuBoardPlaceholder = sudokuBoardPlaceholder;
    }


    /**
     * Add a PropertyChangeListener for a specific property.
     * @param propertyName property that will fire propertyChange.
     * @param listener listens to value change
     */
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * Remove a PropertyChangeListener for a specific property.
     * @param propertyName -> property that listener will be removed from
     * @param listener -> listener to remove
     */
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }

    /**
     * Returns an array of all the listeners that were added to the
     * PropertyChangeSupport object with addPropertyChangeListener().
     * @return array of all the listeners
     */
    public PropertyChangeListener[] getAllListeners() {
        return propertyChangeSupport.getPropertyChangeListeners();
    }

    /**
     * Returns SudokuBoard stored in placeholder.
     *
     * @return SudokuBoard
     */
    public SudokuBoard getSudokuBoardPlaceholder() {
        return sudokuBoardPlaceholder;
    }

    /**
     * Sets a new SudokuBoard to be stored in placeholder.
     * Swaps all values independently.
     * @param newBoard SudokuBoard to swap
     */
    public void setSudokuBoardPlaceholder(SudokuBoard newBoard) {
        SudokuBoard oldBoard = getSudokuBoardPlaceholder();
        for (int row = 0; row < oldBoard.getBoardSize(); row++) {
            for (int column = 0; column < oldBoard.getBoardSize(); column++) {
                set(row, column, newBoard.get(row, column));
            }
        }
    }

    /**
     * Gets value of SudokuField stored in sudokuBoardPlaceholder.
     * @param x  x coordinate of field
     * @param y  y coordinate of field
     * @return value stored
     */
    public int get(int x, int y) {
        return sudokuBoardPlaceholder.get(x, y);
    }

    /**
     * Gets value of SudokuField stored in sudokuBoardPlaceholder.
     * Fires propertyChange.
     * @param x  x coordinate of field
     * @param y  y coordinate of field
     * @param newVal value to be stored
     */
    public void set(int x, int y, int newVal) {
        if (sudokuBoardPlaceholder.get(x, y) != newVal) {
            SudokuFieldPlaceholder oldField = new SudokuFieldPlaceholder(x, y, sudokuBoardPlaceholder.get(x, y));
            sudokuBoardPlaceholder.set(x, y, newVal);
            SudokuFieldPlaceholder newField = new SudokuFieldPlaceholder(x, y, sudokuBoardPlaceholder.get(x, y));
            propertyChangeSupport.firePropertyChange("value", oldField, newField);
            System.out.println("Model event fired");
        }
    }

    /**
     * Checks if two placeholders(SudokuBoards) are the same.
     *
     * @param o object to compare
     * @return true if content is the same, otherwise return false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof FXsudokuBoard)) {
            return false;
        }

        FXsudokuBoard that = (FXsudokuBoard) o;

        return new EqualsBuilder()
                .append(sudokuBoardPlaceholder, that.sudokuBoardPlaceholder)
                .isEquals();
    }

    /**
     * Generates hash code of sudokuBoardPlaceholder.
     * Depends on content of the placeholder.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(sudokuBoardPlaceholder)
                .toHashCode();
    }

    /**
     * Stringifies placeholder and instance ID.
     * @return String with values
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("sudokuBoardPlaceholder", sudokuBoardPlaceholder)
                .toString();
    }

}
