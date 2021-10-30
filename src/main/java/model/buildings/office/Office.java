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

    public Office(int rooms, double space) {
        if (space <= 0) throw new InvalidSpaceAreaException();
        if (rooms <= 0) throw new InvalidRoomsCountException();

        this.space = space;
        this.rooms = rooms;
    }

    public Office(double space) {
        this(DEFAULT_ROOMS, space);
    }

    public Office() {
        this(DEFAULT_ROOMS, DEFAULT_SQUARE);
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
        return new Office(rooms, space);
    }
}
