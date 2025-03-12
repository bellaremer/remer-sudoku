package remer.sudoku;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SudokuTest
{
    @Test
    public void getErrors()
    {
        int[][] exampleBoardWithErrors = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2}, // No change
                {6, 7, 2, 1, 9, 5, 3, 4, 8}, // No change
                {1, 9, 8, 3, 4, 2, 5, 6, 7}, // No change
                {8, 5, 9, 7, 6, 1, 4, 2, 3}, // No change
                {4, 2, 6, 8, 5, 3, 7, 9, 1}, // No change
                {7, 1, 3, 9, 2, 4, 8, 5, 6}, // No change
                {9, 6, 1, 5, 3, 7, 2, 8, 4}, // No change
                {2, 8, 2, 4, 1, 9, 6, 3, 5}, // Error: Duplicate 2 in row 8, column 2
                {3, 4, 5, 2, 8, 6, 1, 7, 8}  // Error: Duplicate 8 in row 9, column 9
        };

        Sudoku sudokuWithErrors = new Sudoku(exampleBoardWithErrors);
        List<SudokuError> errors = sudokuWithErrors.getErrors();

        // Assert the number of errors
        assertEquals(2, errors.size(), "Expected 2 errors for the provided Sudoku board.");

        // Assert specific errors
        assertTrue(errors.contains(new SudokuError(7, 2, 2)), "Expected error for duplicate 2 in row 8, column 3.");
        assertTrue(errors.contains(new SudokuError(8, 8, 8)), "Expected error for duplicate 8 in row 9, column 9.");
    }

    @Test
    public void correctBoard()
    {
        int[][] correctBoard = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        Sudoku sudoku = new Sudoku(correctBoard);
        List<SudokuError> errors = sudoku.getErrors();
        assertTrue(errors.isEmpty(), "Expected no errors for the correct Sudoku board.");
    }
}