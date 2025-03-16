package remer.sudoku;

import java.util.List;

public class SudokuController
{
    private final SudokuGui gui;
    private Sudoku sudoku;

    public SudokuController(SudokuGui gui, int[][] initialBoard)
    {
        this.gui = gui;
        this.sudoku = new Sudoku(initialBoard);
    }

    // Method to validate the current board and update the GUI
    public void validateBoard()
    {
        int[][] board = gui.getCurrentBoard();
        sudoku = new Sudoku(board); // Update the Sudoku instance with the current board
        List<SudokuError> errors = sudoku.getErrors();

        if (errors.isEmpty())
        {
            gui.highlightCorrectBoard();
        } else {
            gui.highlightErrors(errors);
        }
    }
}