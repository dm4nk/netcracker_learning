package model.buildings;

import java.util.Iterator;

public interface Floor {
    int size();

    int sumSquare();

    int sumRoomCount();

    Space[] spaces();

    Space getSpace(int number);

    void setSpace(int number, Space flat);

    void addSpace(int number, Space flat);

    void removeSpace(int number);

    Space getBestSpace();

    Iterator<Space> iterator();
}
