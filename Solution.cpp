
#include <vector>
#include <string>
#include <string_view>
using namespace std;

class BusBooking {

    // columns: A, B, C, D
    static const int TOTAL_COLUMNS = 4;
    static const int NOT_BOOKED = 0;

    vector<vector<int>> seats;
    int totalBookedSeats = 0;
    int totalTimeForBookedSeats = 0;

public:
    BusBooking(int totalRows) {
        seats.assign(totalRows, vector<int>(TOTAL_COLUMNS));
    }

    void toggle(string seat) {
        int row = getRowIndex(seat.substr(1, seat.length() - 1));
        int column = getColumnIndex(seat[0]);

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

    int getTotalTime() {
        return totalTimeForBookedSeats;
    }

    int getMinTime() {
        return totalBookedSeats;
    }

private:
    static int getColumnIndex(char letter) {
        return letter - 'A';
    }

    static int getRowIndex(string_view digits) {
        int row = 0;
        for (int i = 0; i < digits.length(); ++i) {
            row = row * 10 + (digits[i] - '0');
        }
        return row - 1;
    }
};
