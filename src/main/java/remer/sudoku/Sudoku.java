package remer.sudoku;

import java.util.ArrayList;
import java.util.List;

public class Sudoku
{
    private final int[][] board;

    // Constructor to initialize the Sudoku board
    public Sudoku(int[][] board)
    {
        this.board = board;
    }

    public List<String> getErrors()
    {
        List<String> errors = new ArrayList<>();
        checkRowDuplicates(errors);
        checkColumnDuplicates(errors);
        checkBoxDuplicates(errors);
        return errors;
    }

    private void checkRowDuplicates(List<String> errors)
    {
        for (int i = 0; i < 9; i++)
        {
            boolean[] seen = new boolean[10];
            for (int j = 0; j < 9; j++)
            {
                int num = board[i][j];
                if (num != 0)
                {
                    if (seen[num])
                    {
                        addUniqueError(errors, "Row " + (i + 1) + " has duplicate value " + num);
                    } else
                    {
                        seen[num] = true;
                    }
                }
            }
        }
    }

    private void checkColumnDuplicates(List<String> errors)
    {
        for (int i = 0; i < 9; i++)
        {
            boolean[] seen = new boolean[10];
            for (int j = 0; j < 9; j++)
            {
                int num = board[j][i];
                if (num != 0)
                {
                    if (seen[num])
                    {
                        addUniqueError(errors, "Column " + (i + 1) + " has duplicate value " + num);
                    } else
                    {
                        seen[num] = true;
                    }
                }
            }
        }
    }

    private void checkBoxDuplicates(List<String> errors)
    {
        for (int boxRow = 0; boxRow < 3; boxRow++)
        {
            for (int boxCol = 0; boxCol < 3; boxCol++)
            {
                boolean[] boxSeen = new boolean[10];
                for (int i = 0; i < 3; i++)
                {
                    for (int j = 0; j < 3; j++)
                    {
                        int num = board[boxRow * 3 + i][boxCol * 3 + j];
                        if (num != 0)
                        {
                            if (boxSeen[num])
                            {
                                addUniqueError(errors, "Box " + (boxRow * 3 + boxCol + 1) + " has duplicate value " + num);
                            } else
                            {
                                boxSeen[num] = true;
                            }
                        }
                    }
                }
            }
        }
    }

    private void addUniqueError(List<String> errors, String error)
    {
        // Check if the error is already in the list before adding
        if (!errors.contains(error))
        {
            errors.add(error);
        }
    }
}