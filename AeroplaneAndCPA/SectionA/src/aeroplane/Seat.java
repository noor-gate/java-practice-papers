package aeroplane;

import java.util.NoSuchElementException;

public class Seat {

    private int row;
    private char letter;
    private String seatRepresentation;

    public Seat(int row, char letter) {
        this.row = row;
        this.letter = letter;
        seatRepresentation = String.valueOf(row) + letter;
    }

    public boolean isEmergencyExit() {
        return row == 1 || row == 10 || row == 30;
    }

    public boolean hasNext() {
        return !seatRepresentation.equals("50F");
    }

    public Seat next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else if (letter == 'F') {
            return new Seat(row + 1, 'A');
        } else {
            return new Seat(row, (char) (letter + 1));
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Seat)) {
            return false;
        }
        Seat seat = (Seat) obj;
        return seatRepresentation.equals(seat.seatRepresentation) && hashCode() == seat.hashCode();
    }

    @Override
    public int hashCode() {
        return row * letter;
    }

}
