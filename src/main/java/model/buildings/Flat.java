package model.buildings;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Flat {
    private static final int DEFAULT_SQUARE = 50;
    private static final int DEFAULT_ROOMS = 2;

    private double square;
    private int rooms;

    public Flat(double square, int rooms) {
        this.square = square;
        this.rooms = rooms;
    }

    public Flat(double square) {
        this(square, DEFAULT_ROOMS);
    }

    public Flat() {
        this(DEFAULT_SQUARE, DEFAULT_ROOMS);
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }
}
