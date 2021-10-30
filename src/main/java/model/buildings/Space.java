package model.buildings;

import model.utilities.MyCloneable;

import java.io.Serializable;

public interface Space extends Serializable, MyCloneable, Comparable<Space> {
    double getSpace();

    void setSpace(double space);

    int getRooms();

    void setRooms(int rooms);

    Object clone();

    @Override
    default int compareTo(Space o) {
        return Double.compare(getSpace(), o.getSpace());
    }
}
