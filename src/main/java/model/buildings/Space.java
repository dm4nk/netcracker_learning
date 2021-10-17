package model.buildings;

import java.io.Serializable;

public interface Space extends Serializable {
    double getSpace();

    void setSpace(double space);

    int getRooms();

    void setRooms(int rooms);
}
