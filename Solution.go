
package main

// columns: A, B, C, D
const TOTAL_COLUMNS = 4
const NOT_BOOKED = 0

type BusBooking struct {
    seats                   [][]int
    totalBookedSeats        int
    totalTimeForBookedSeats int
}

func Constructor(totalRows int) BusBooking {
    busBooking := BusBooking{
        seats:                   make([][]int, totalRows),
        totalBookedSeats:        0,
        totalTimeForBookedSeats: 0,
    }

    for i := range totalRows {
        busBooking.seats[i] = make([]int, TOTAL_COLUMNS)
    }
    return busBooking
}

func (this *BusBooking) Toggle(seat string) {
    row := getRowIndex(seat[1:])
    column := getColumnIndex(seat[0])

    if this.seats[row][column] != NOT_BOOKED {
        this.totalBookedSeats--
        this.totalTimeForBookedSeats -= this.seats[row][column]
        this.seats[row][column] = NOT_BOOKED
        return
    }

    if column == 1 || column == 2 {
        this.seats[row][column]++
        this.totalBookedSeats++
        this.totalTimeForBookedSeats++
        return
    }

    if column == 0 {
        this.seats[row][column] = 1 + 2 * this.seats[row][column + 1]
        this.totalBookedSeats++
        this.totalTimeForBookedSeats += this.seats[row][column]
        return
    }

    if column == 3 {
        this.seats[row][column] = 1 + 2 * this.seats[row][column - 1]
        this.totalBookedSeats++
        this.totalTimeForBookedSeats += this.seats[row][column]
    }
}

func (this *BusBooking) GetTotalTime() int {
    return this.totalTimeForBookedSeats
}

func (this *BusBooking) GetMinTime() int {
    return this.totalBookedSeats
}

func getColumnIndex(letter byte) int {
    return int(letter - 'A')
}

func getRowIndex(digits string) int {
    row := 0
    for i := range digits {
        row = row * 10 + int(digits[i] - '0')
    }
    return row - 1
}
