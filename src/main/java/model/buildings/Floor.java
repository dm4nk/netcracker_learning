package model.buildings;

import lombok.NonNull;
import model.utilities.MyCloneable;

import java.io.Serializable;
import java.util.Iterator;

public interface Floor extends Serializable, Iterable<Space>, MyCloneable, Comparable<Floor> {
    int size();

    int sumSquare();

    int sumRoomCount();

    Space[] spaces();

    Space getSpace(int number);

    void setSpace(int number, @NonNull Space flat);

    void addSpace(int number, @NonNull Space flat);

    void removeSpace(int number);

    Space getBestSpace();//todo: есть предположение, что можно вынести сюда через Iterable<Space>

    Iterator<Space> iterator();

    Object clone();

    @Override
    default int compareTo(Floor o) {
        return Integer.compare(size(), o.size());
    }
}
