package model.buildings;

public interface Floor {
    int size();

    int sumSquare();

    int sumRoomCount();

    Space[] flats();

    Space getFlat(int number);

    void setFlat(int number, Space flat);

    void addFlat(int number, Space flat);

    void removeFlat(int number);

    Space getBestSpace();
}
