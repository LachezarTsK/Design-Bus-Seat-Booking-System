
using System;

public class BusBooking
{
    // columns: A, B, C, D
    private static readonly int TOTAL_COLUMNS = 4;
    private static readonly int NOT_BOOKED = 0;
    private readonly int[,] seats;
    private int totalBookedSeats;
    private int totalTimeForBookedSeats;

    public BusBooking(int totalRows)
    {
        seats = new int[totalRows, TOTAL_COLUMNS];
    }

    public void Toggle(string seat)
    {
        int row = GetRowIndex(seat.Substring(1));
        int column = GetColumnIndex(seat[0]);

        if (seats[row, column] != NOT_BOOKED)
        {
            --totalBookedSeats;
            totalTimeForBookedSeats -= seats[row, column];
            seats[row, column] = NOT_BOOKED;
            return;
        }

        if (column == 1 || column == 2)
        {
            ++seats[row, column];
            ++totalBookedSeats;
            ++totalTimeForBookedSeats;
            return;
        }

        if (column == 0)
        {
            seats[row, column] = 1 + 2 * seats[row, column + 1];
            ++totalBookedSeats;
            totalTimeForBookedSeats += seats[row, column];
            return;
        }

        if (column == 3)
        {
            seats[row, column] = 1 + 2 * seats[row, column - 1];
            ++totalBookedSeats;
            totalTimeForBookedSeats += seats[row, column];
        }
    }

    public int GetTotalTime()
    {
        return totalTimeForBookedSeats;
    }

    public int GetMinTime()
    {
        return totalBookedSeats;
    }

    private static int GetColumnIndex(char letter)
    {
        return letter - 'A';
    }

    private static int GetRowIndex(string digits)
    {
        int row = 0;
        for (int i = 0; i < digits.Length; ++i)
        {
            row = row * 10 + (digits[i] - '0');
        }
        return row - 1;
    }
}
