package remer.sudoku;

public class SudokuErrors
{
    private final int row;
    private final int column;
    private final int number;

    public SudokuErrors(int row, int column, int number) {
        this.row = row;
        this.column = column;
        this.number = number;
    }

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }

    public int getNumber()
    {
        return number;
    }

    @Override
    public String toString()
    {
        return "Error: Number " + number + " in Row " + (row + 1) + ", Column " + (column + 1);
    }
}