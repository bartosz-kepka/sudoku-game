package sudoku;

import static sudoku.SudokuBoard.SIZE;

import java.security.SecureRandom;


public class BacktrackingSudokuSolver implements SudokuSolver {


    public static final int NUMBER_OF_CELLS = SIZE * SIZE;

    @Override
    public void solve(SudokuBoard boardToSolve) {
        SecureRandom random = new SecureRandom();
        int[] initialNumbers = new int[NUMBER_OF_CELLS]; //array of numbers algorithm begins with
        for (int i = 0; i < NUMBER_OF_CELLS; i++) {
            int row = i / SIZE;
            int column = i % SIZE;
            boolean isCorrect = false;
            if (initialNumbers[i] == 0) {
                initialNumbers[i] = random.nextInt(9) + 1;
                boardToSolve.set(row, column, initialNumbers[i]);
                //check if written value is correct
                do {
                    if (boardToSolve.checkBoard(i)) {
                        isCorrect = true;
                        break;
                    }
                    boardToSolve.set(row, column, boardToSolve.get(row, column) % 9 + 1);
                } while (boardToSolve.get(row, column) != initialNumbers[i]);
            } else { //if backtracked use previous value
                boardToSolve.set(row, column, boardToSolve.get(row, column) % 9 + 1);
                while (boardToSolve.get(row, column) != initialNumbers[i]) {
                    if (boardToSolve.checkBoard(i)) {
                        isCorrect = true;
                        break;
                    }
                    boardToSolve.set(row, column, boardToSolve.get(row, column) % 9 + 1);
                }
            } //backtrack
            if (!isCorrect) {
                initialNumbers[i] = 0; //reset current value
                boardToSolve.set(row, column, 0); // reset value written to board
                i -= 2; // move to previous value
            }
        }
    }
}
