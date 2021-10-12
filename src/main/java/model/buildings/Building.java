package model.buildings;

public interface Building {
    int size();

    int flatsCount();

    int sumRoomsCount();

    int sumSquare();

    Floor[] getAllFloors();

    Floor getFloor(int number);

    void setFloor(int number, Floor floor);

    Space getSpace(int number);

    void setSpace(int number, Space space);

    void addSpace(int number, Space space);

    void removeSpace(int number);

    double[] getSquares();
}
