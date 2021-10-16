package model.buildings.flat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.buildings.Space;
import model.exeptions.InvalidRoomsCountException;
import model.exeptions.InvalidSpaceAreaException;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Flat implements Space {
    private static final int DEFAULT_SQUARE = 50;
    private static final int DEFAULT_ROOMS = 2;

    private double space;
    private int rooms;

    public Flat(double space, int rooms) {
        if (space <= 0) throw new InvalidSpaceAreaException();
        if (rooms <= 0) throw new InvalidRoomsCountException();

        this.space = space;
        this.rooms = rooms;
    }

    public Flat(double space) {
        this(space, DEFAULT_ROOMS);
    }

    public Flat() {
        this(DEFAULT_SQUARE, DEFAULT_ROOMS);
    }
}
