package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BacktrackingSudokuSolverTest {

    @Test
    public void solve_GivenUnsolvableBoard_ShouldReturnFalse() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());

        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();

        // Wrongly filled board
        sudokuBoard.set(0, 0, 1);
        sudokuBoard.set(0, 1, 1);

        assertFalse(solver.solve(sudokuBoard));
    }
}