package pl.sudoku.FXModel;

import pl.sudoku.model.SudokuBoard;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FXSudokuBoard {

    private transient final PropertyChangeSupport propertyChangeSupport;

    private SudokuBoard sudokuBoardPlaceholder;

    public FXSudokuBoard(SudokuBoard sudokuBoardPlaceholder) {
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        this.sudokuBoardPlaceholder = sudokuBoardPlaceholder;
    }


    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }

    public SudokuBoard getSudokuBoardPlaceholder() {
        return sudokuBoardPlaceholder;
    }

    public void setSudokuBoardPlaceholder(SudokuBoard newBoard) {
        SudokuBoard oldBoard = getSudokuBoardPlaceholder();
        for (int row = 0; row < oldBoard.getBoardSize(); row++) {
            for (int column = 0; column < oldBoard.getBoardSize(); column++) {
                set(row, column, newBoard.get(row, column));
            }
        }
    }

    public int get(int x, int y) {
        return sudokuBoardPlaceholder.get(x, y);
    }

    public void set(int x, int y, int newVal) {
        SudokuFieldPlaceholder oldField = new SudokuFieldPlaceholder(x, y, sudokuBoardPlaceholder.get(x, y));
        sudokuBoardPlaceholder.set(x, y, newVal);
        SudokuFieldPlaceholder newField = new SudokuFieldPlaceholder(x, y, newVal);
        propertyChangeSupport.firePropertyChange("value", oldField, newField);
    }




}
