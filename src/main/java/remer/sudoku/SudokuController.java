package remer.sudoku;

import java.awt.*;
import java.util.ArrayList;
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
    public void validateBoard()
    {
        int[][] currentBoard = getCurrentBoard();
        sudoku.updateBoard(currentBoard);
        List<SudokuError> errors = sudoku.getErrors();

        if (errors.isEmpty())
        {
            highlightCorrectBoard();
        } else {
            highlightErrors(errors);
        }
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

    private void highlightCorrectBoard()
    {
        // set the cells to green if the board is correct
        for (int row = 0; row < 9; row++)
        {
            for (int col = 0; col < 9; col++)
            {
                cells[row][col].setBackground(Color.GREEN);
            }
        }
    }

    private void highlightErrors(List<SudokuError> errors)
    {
        resetHighlighting();

        // Highlight the cells with errors
        for (SudokuError error : errors)
        {
            int row = error.row();
            int col = error.col();
            cells[row][col].setBackground(Color.RED);
        }
    }

    void resetHighlighting()
    {
        for (int row = 0; row < 9; row++)
        {
            for (int col = 0; col < 9; col++)
            {
                cells[row][col].setBackground(Color.WHITE);
            }
        }
    }
}