
class BusBooking {

    // columns: A, B, C, D
    static TOTAL_COLUMNS = 4;
    static NOT_BOOKED = 0;
    static ASCII_ZERO = 48;
    static ASCII_UPPERCASE_A = 65;

    /**
     * @param {number} totalRows
     */
    constructor(totalRows) {
        this.seats = Array.from(new Array(totalRows), () => new Array(BusBooking.TOTAL_COLUMNS).fill(0));
        this.totalBookedSeats = 0;
        this.totalTimeForBookedSeats = 0;
    }

    /** 
     * @param {string} seat
     * @return {void}
     */
    toggle(seat) {
        const row = this.getRowIndex(seat.substring(1));
        const column = this.getColumnIndex(seat.codePointAt(0));

        if (this.seats[row][column] !== BusBooking.NOT_BOOKED) {
            --this.totalBookedSeats;
            this.totalTimeForBookedSeats -= this.seats[row][column];
            this.seats[row][column] = BusBooking.NOT_BOOKED;
            return;
        }

        if (column === 1 || column === 2) {
            ++this.seats[row][column];
            ++this.totalBookedSeats;
            ++this.totalTimeForBookedSeats;
            return;
        }

        if (column === 0) {
            this.seats[row][column] = 1 + 2 * this.seats[row][column + 1];
            ++this.totalBookedSeats;
            this.totalTimeForBookedSeats += this.seats[row][column];
            return;
        }

        if (column === 3) {
            this.seats[row][column] = 1 + 2 * this.seats[row][column - 1];
            ++this.totalBookedSeats;
            this.totalTimeForBookedSeats += this.seats[row][column];
        }
    }

    /**
     * @return {number}
     */
    getTotalTime() {
        return this.totalTimeForBookedSeats;
    }

    /**
     * @return {number}
     */
    getMinTime() {
        return this.totalBookedSeats;
    }

    /** 
     * @param {number} letterInASCII
     * @return {number}
     */
    getColumnIndex(letterInASCII) {
        return letterInASCII - BusBooking.ASCII_UPPERCASE_A;
    }

    /** 
     * @param {string} digits
     * @return {number}
     */
    getRowIndex(digits) {
        let row = 0;
        for (let i = 0; i < digits.length; ++i) {
            row = row * 10 + (digits.codePointAt(i) - BusBooking.ASCII_ZERO);
        }
        return row - 1;
    }
}
