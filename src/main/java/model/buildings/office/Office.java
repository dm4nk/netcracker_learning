package model.buildings.office;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import model.buildings.Space;
import model.exeptions.InvalidRoomsCountException;
import model.exeptions.InvalidSpaceAreaException;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class Office implements Space, Serializable {
    private static final int DEFAULT_SQUARE = 250;
    private static final int DEFAULT_ROOMS = 1;
    private static final int SUPER_SECRET_HASH_CODE_VARIABLE = 1337;

    @NonNull
    private double space;
    @NonNull
    private int rooms;

    public Office(double space, int rooms) {
        if (space <= 0) throw new InvalidSpaceAreaException();
        if (rooms <= 0) throw new InvalidRoomsCountException();

        this.space = space;
        this.rooms = rooms;
    }

    public Office(double space) {
        this(space, DEFAULT_ROOMS);
    }

    public Office() {
        this(DEFAULT_SQUARE, DEFAULT_ROOMS);
    }

    @Override
    public String toString() {
        return "Office (" +
                rooms +
                ", " + space +
                ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Office)) return false;
        Office office = (Office) o;
        return Double.compare(office.getSpace(), getSpace()) == 0 &&
                getRooms() == office.getRooms();
    }

    @Override
    public int hashCode() {
        return Objects.hash(SUPER_SECRET_HASH_CODE_VARIABLE, getSpace(), getRooms());
    }

    @Override
    public Object clone() {
        return new Office(space, rooms);
    }
}
