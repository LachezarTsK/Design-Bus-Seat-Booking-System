
class BusBooking(totalRows: Int) {

    private companion object {
        // columns: A, B, C, D
        const val TOTAL_COLUMNS = 4
        const val NOT_BOOKED = 0
    }

    private var seats = Array<IntArray>(totalRows) { IntArray(TOTAL_COLUMNS) }
    private var totalBookedSeats = 0
    private var totalTimeForBookedSeats = 0

    fun toggle(seat: String) {
        val row = getRowIndex(seat.substring(1))
        val column = getColumnIndex(seat[0])

        if (seats[row][column] != NOT_BOOKED) {
            --totalBookedSeats
            totalTimeForBookedSeats -= seats[row][column]
            seats[row][column] = NOT_BOOKED
            return
        }

        if (column == 1 || column == 2) {
            ++seats[row][column]
            ++totalBookedSeats
            ++totalTimeForBookedSeats
            return
        }

        if (column == 0) {
            seats[row][column] = 1 + 2 * seats[row][column + 1]
            ++totalBookedSeats
            totalTimeForBookedSeats += seats[row][column]
            return
        }

        if (column == 3) {
            seats[row][column] = 1 + 2 * seats[row][column - 1]
            ++totalBookedSeats
            totalTimeForBookedSeats += seats[row][column]
        }
    }

    fun getTotalTime(): Int {
        return totalTimeForBookedSeats
    }

    fun getMinTime(): Int {
        return totalBookedSeats
    }

    private fun getColumnIndex(letter: Char): Int {
        return letter - 'A'
    }

    private fun getRowIndex(digits: String): Int {
        var row = 0
        for (i in digits.indices) {
            row = row * 10 + (digits[i] - '0')
        }
        return row - 1
    }
}
