package model.buildings;

import lombok.NonNull;

import java.io.Serializable;
import java.util.Iterator;

public interface Floor extends Serializable, Iterable<Space> {
    int size();

    int sumSquare();

    int sumRoomCount();

    Space[] spaces();

    Space getSpace(int number);

    void setSpace(int number, @NonNull Space flat);

    void addSpace(int number, @NonNull Space flat);

    void removeSpace(int number);

    Space getBestSpace();

    Iterator<Space> iterator();
}
