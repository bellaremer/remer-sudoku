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

        // Check for row and column duplicates
        for (int i = 0; i < 9; i++)
        {
            boolean[] row = new boolean[10];
            boolean[] col = new boolean[10];
            for (int j = 0; j < 9; j++)
            {

                // Check rows
                if (board[i][j] != 0)
                {
                    if (row[board[i][j]])
                    {
                        errors.add("Row " + i + " duplicate " + board[i][j]);
                    } else
                    {
                        row[board[i][j]] = true;
                    }
                }

                // Check columns
                if (board[j][i] != 0)
                {
                    if (col[board[j][i]])
                    {
                        errors.add("Column " + i + " duplicate " + board[j][i]);
                    } else
                    {
                        col[board[j][i]] = true;
                    }
                }

            }
        }

        // Check for 3x3 box duplicates
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
                                errors.add("Box " + (boxRow * 3 + boxCol) + " duplicate " + num);
                            } else
                            {
                                boxSeen[num] = true;
                            }
                        }
                    }
                }
            }
        }

        return errors;
    }
}