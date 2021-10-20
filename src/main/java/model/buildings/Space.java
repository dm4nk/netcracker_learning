package model.buildings;

import model.utilities.MyCloneable;

import java.io.Serializable;

public interface Space extends Serializable, MyCloneable {
    double getSpace();

    void setSpace(double space);

    int getRooms();

    void setRooms(int rooms);

    Object clone();
}
