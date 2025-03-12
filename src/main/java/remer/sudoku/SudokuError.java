package remer.sudoku;

import java.util.Arrays;

public class SudokuError
{
    private final int row;
    private final int column;
    private final int number;

    public SudokuError(int row, int column, int number) {
        this.row = row;
        this.column = column;
        this.number = number;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Error: Number " + number + " in Row " + (row + 1) + ", Column " + (column + 1);
    }

    // add these in to make the list.contains() work
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (!(obj instanceof SudokuError that))
        {
            return false;
        }

        return row == that.row && column == that.column && number == that.number;
    }

    @Override
    public int hashCode()
    {
        return Arrays.hashCode(new int[]{row, column, number});
    }

}

