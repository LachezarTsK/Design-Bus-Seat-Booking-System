
public class BusBooking {

    // columns: A, B, C, D
    private static final int TOTAL_COLUMNS = 4;
    private static final int NOT_BOOKED = 0;

    private final int[][] seats;
    private int totalBookedSeats;
    private int totalTimeForBookedSeats;

    public BusBooking(int totalRows) {
        seats = new int[totalRows][TOTAL_COLUMNS];
    }

    public void toggle(String seat) {
        int row = getRowIndex(seat.substring(1));
        int column = getColumnIndex(seat.charAt(0));

        if (seats[row][column] != NOT_BOOKED) {
            --totalBookedSeats;
            totalTimeForBookedSeats -= seats[row][column];
            seats[row][column] = NOT_BOOKED;
            return;
        }

        if (column == 1 || column == 2) {
            ++seats[row][column];
            ++totalBookedSeats;
            ++totalTimeForBookedSeats;
            return;
        }

        if (column == 0) {
            seats[row][column] = 1 + 2 * seats[row][column + 1];
            ++totalBookedSeats;
            totalTimeForBookedSeats += seats[row][column];
            return;
        }

        if (column == 3) {
            seats[row][column] = 1 + 2 * seats[row][column - 1];
            ++totalBookedSeats;
            totalTimeForBookedSeats += seats[row][column];
        }
    }

    public int getTotalTime() {
        return totalTimeForBookedSeats;
    }

    public int getMinTime() {
        return totalBookedSeats;
    }

    private static int getColumnIndex(char letter) {
        return letter - 'A';
    }

    private static int getRowIndex(String digits) {
        int row = 0;
        for (int i = 0; i < digits.length(); ++i) {
            row = row * 10 + (digits.charAt(i) - '0');
        }
        return row - 1;
    }
}
