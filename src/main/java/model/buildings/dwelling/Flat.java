package model.buildings.dwelling;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import model.buildings.Space;
import model.exeptions.InvalidRoomsCountException;
import model.exeptions.InvalidSpaceAreaException;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
public class Flat implements Space, Serializable {
    private static final int DEFAULT_SQUARE = 50;
    private static final int DEFAULT_ROOMS = 2;

    @NonNull
    private double space;
    @NonNull
    private int rooms;

    public Flat(int rooms, double space) {
        if (space <= 0) throw new InvalidSpaceAreaException();
        if (rooms <= 0) throw new InvalidRoomsCountException();

        this.space = space;
        this.rooms = rooms;
    }

    public Flat(double space) {
        this(DEFAULT_ROOMS, space);
    }

    public Flat() {
        this(DEFAULT_ROOMS, DEFAULT_SQUARE);
    }

    @Override
    public String toString() {
        return "Flat (" +
                rooms +
                ", " + space +
                ')';
    }

    @Override
    public Object clone() {
        return new Flat(rooms, space);
    }
}
