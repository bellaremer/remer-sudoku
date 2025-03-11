package remer.sudoku;

import java.util.ArrayList;
import java.util.List;

public class Sudoku {
    private final int[][] board;

    // Constructor to initialize the Sudoku board
    public Sudoku(int[][] board)
    {
        if (board.length != 9 || board[0].length != 9)
        {
            throw new IllegalArgumentException("The Sudoku board must be 9 rows by 9 columns.");
        }
        this.board = board;
    }

    // Method to get a list of errors in the Sudoku board
    public List<SudokuErrors> getErrors()
    {
        List<SudokuErrors> errors = new ArrayList<>();
        checkRowDuplicates(errors);
        checkColumnDuplicates(errors);
        checkBoxDuplicates(errors);
        return errors;
    }

    // Check for duplicates in each row
    private void checkRowDuplicates(List<SudokuErrors> errors)
    {
        for (int i = 0; i < 9; i++)
        {
            boolean[] seen = new boolean[10]; // To track seen numbers (1-9)
            for (int j = 0; j < 9; j++)
            {
                int num = board[i][j];
                if (num > 0 && num <= 9)
                {
                    if (seen[num])
                    {
                        addUniqueError(errors, i, j, num);
                    } else {
                        seen[num] = true;
                    }
                }
            }
        }
    }

    // Check for duplicates in each column
    private void checkColumnDuplicates(List<SudokuErrors> errors)
    {
        for (int i = 0; i < 9; i++) {
            boolean[] seen = new boolean[10]; // To track seen numbers (1-9)
            for (int j = 0; j < 9; j++)
            {
                int num = board[j][i];
                if (num > 0 && num <= 9)
                {
                    if (seen[num])
                    {
                        addUniqueError(errors, j, i, num);
                    } else {
                        seen[num] = true;
                    }
                }
            }
        }
    }

    // Check for duplicates in each 3x3 box
    private void checkBoxDuplicates(List<SudokuErrors> errors)
    {
        for (int boxRow = 0; boxRow < 3; boxRow++)
        {
            for (int boxCol = 0; boxCol < 3; boxCol++)
            {
                checkSquareDuplicates(boxRow, boxCol, errors);
            }
        }
    }

    // Check for duplicates in a specific 3x3 box
    private void checkSquareDuplicates(int boxRow, int boxCol, List<SudokuErrors> errors)
    {
        boolean[] seen = new boolean[10]; // To track seen numbers (1-9)
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                int row = boxRow * 3 + i;
                int col = boxCol * 3 + j;
                int num = board[row][col];
                if (num > 0 && num <= 9)
                {
                    if (seen[num])
                    {
                        addUniqueError(errors, row, col, num);
                    } else {
                        seen[num] = true;
                    }
                }
            }
        }
    }

    // Add a unique error to the list, ensuring no duplicates
    private void addUniqueError(List<SudokuErrors> errors, int row, int column, int number)
    {
        SudokuErrors newError = new SudokuErrors(row, column, number);

        // Check if the error already exists in the list
        if (!errors.contains(newError))
        {
            errors.add(newError);
        }
    }
}
