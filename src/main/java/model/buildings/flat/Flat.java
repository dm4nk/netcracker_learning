package model.buildings.flat;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import model.exeptions.InvalidRoomsCountException;
import model.exeptions.InvalidSpaceAreaException;

@EqualsAndHashCode
@ToString
public class Flat {
    private static final int DEFAULT_SQUARE = 250;
    private static final int DEFAULT_ROOMS = 1;

    private double space;
    private int rooms;

    public Flat(double space, int rooms) {
        if(space <=0) throw new InvalidSpaceAreaException();
        if(rooms <=0) throw new InvalidRoomsCountException();

        this.space = space;
        this.rooms = rooms;
    }

    public Flat(double space) {
        this(space, DEFAULT_ROOMS);
    }

    public Flat() {
        this(DEFAULT_SQUARE, DEFAULT_ROOMS);
    }

    public double getSpace() {
        return space;
    }

    public void setSpace(double space) {
        this.space = space;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }
}
