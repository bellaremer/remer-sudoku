package remer.sudoku;

import java.awt.*;
import java.util.List;
import javax.swing.JTextField;

public class SudokuController
{
    private final JTextField[][] cells;     // storing the JTextFields
    private Sudoku sudoku;

    public SudokuController(JTextField[][] cells, int[][] initialBoard)
    {
        this.cells = cells;     // initialize with the JTextFields
        this.sudoku = new Sudoku(initialBoard);
    }

    // Method to validate the current board and update the GUI
    public List<SudokuError> validateBoard()
    {
        int[][] currentBoard = getCurrentBoard();
        sudoku.updateBoard(currentBoard);
        sudoku.getErrors();
        return sudoku.getErrors();  // return list of errors
    }

    private int[][] getCurrentBoard()
    {
        int[][] board = new int[9][9];
        for (int row = 0; row < 9; row++)
        {
            for (int col = 0; col < 9; col++)
            {
                String text = cells[row][col].getText();
                if (text.isEmpty())
                {
                    board[row][col] = 0;
                } else {
                    try
                    {
                        board[row][col] = Integer.parseInt(text);
                    } catch (NumberFormatException e)
                    {
                        board[row][col] = 0;
                    }
                }
            }
        }
        return board;
    }
}