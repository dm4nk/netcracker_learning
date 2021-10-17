package model.buildings.flat;

import lombok.*;
import model.buildings.Space;
import model.exeptions.InvalidRoomsCountException;
import model.exeptions.InvalidSpaceAreaException;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Flat implements Space, Serializable {
    private static final int DEFAULT_SQUARE = 50;
    private static final int DEFAULT_ROOMS = 2;

    @NonNull
    private double space;
    @NonNull
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
