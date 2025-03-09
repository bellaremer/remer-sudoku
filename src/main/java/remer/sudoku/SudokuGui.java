package remer.sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SudokuGui
{
    private JFrame frame;
    private JTextField[][] cells;
    private JButton checkButton;

    public SudokuGui(int[][] board)
    {
        // Create the main frame
        frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(600, 600);

        // Initialize the cells array
        cells = new JTextField[9][9];
        JPanel gridPanel = new JPanel(new GridLayout(9, 9));

        // Create and add JTextFields to the grid panel
        for (int row = 0; row < 9; row++)
        {
            for (int col = 0; col < 9; col++)
            {
                cells[row][col] = new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                cells[row][col].setFont(new Font("Arial", Font.PLAIN, 24));
                cells[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                // Set the value if it's not zero
                if (board[row][col] != 0)
                {
                    cells[row][col].setText(String.valueOf(board[row][col]));
                    cells[row][col].setEditable(false); // Make it non-editable for pre-filled cells
                } else
                {
                    cells[row][col].setText(""); // Empty cell
                }

                gridPanel.add(cells[row][col]);
            }
        }

        // Create a button to check for errors
        checkButton = new JButton("Check for Errors");
        checkButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkForErrors();
            }
        });

        // Add components to the frame
        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(checkButton, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(true);
    }

    private void checkForErrors()
    {
        // Check if all cells are filled
        boolean allFilled = true;
        int[][] board = new int[9][9];

        for (int row = 0; row < 9; row++)
        {
            for (int col = 0; col < 9; col++)
            {
                String text = cells[row][col].getText();
                if (text.isEmpty())
                {
                    allFilled = false;
                } else
                {
                    try
                    {
                        int value = Integer.parseInt(text);
                        if (value < 1 || value > 9)
                        {
                            allFilled = false; // Invalid value
                        }
                        board[row][col] = value;
                    } catch (NumberFormatException ex)
                    {
                        allFilled = false; // Not a valid number
                    }
                }
            }
        }

        if (allFilled)
        {
            // Check for errors and highlight them
            highlightErrors(new Sudoku(board).getErrors());
        } else
        {
            JOptionPane.showMessageDialog(frame, "Please fill in all cells with numbers 1-9.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            resetHighlighting();
        }
    }

    private void highlightErrors(List<SudokuErrors> errors) {
        // Reset all cell backgrounds to white
        resetHighlighting();

        // Highlight cells with errors
        for (SudokuErrors error : errors) {
            int row = error.getRow();
            int col = error.getColumn();
            cells[row][col].setBackground(Color.RED); // Highlight the cell in red
        }
    }

    private void resetHighlighting() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col].setBackground(Color.WHITE); // Reset background color
            }
        }
    }


    public static void main(String[] args)
    {
        // Example partially completed Sudoku board
        int[][] board = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        // Create the Sudoku GUI
        SwingUtilities.invokeLater(() -> new SudokuGui(board));
    }
}