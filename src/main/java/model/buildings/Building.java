package model.buildings;

import model.exeptions.FloorIndexOutOfBoundsException;
import model.utilities.Entity;

import static model.utilities.IndexChecker.checkIfNumberIsValid;

public interface Building {
    int size();

    int flatsCount();

    int sumRoomsCount();

    int sumSquare();

    Floor[] getAllFloors();

    Floor getFloor(int number);

    void setFloor(int number, Floor floor);

    default Space getSpace(int number) {
        checkIfNumberIsValid(number, sumRoomsCount() - 1, FloorIndexOutOfBoundsException.class);
        Entity<Floor, Integer> floorAndNumber = countFloorAndFlat(number);

        return floorAndNumber.floor.getFlat(floorAndNumber.number);
    }

    default void setSpace(int number, Space space) {
        checkIfNumberIsValid(number, sumRoomsCount() - 1, FloorIndexOutOfBoundsException.class);
        Entity<Floor, Integer> floorAndNumber = countFloorAndFlat(number);

        floorAndNumber.floor.setFlat(floorAndNumber.number, space);
    }

    default void addSpace(int number, Space space) {
        checkIfNumberIsValid(number, sumRoomsCount(), FloorIndexOutOfBoundsException.class);
        Entity<Floor, Integer> floorAndNumber = countFloorAndFlat(number);

        floorAndNumber.floor.addFlat(floorAndNumber.number, space);
    }

    default void removeSpace(int number) {
        checkIfNumberIsValid(number, sumRoomsCount() - 1, FloorIndexOutOfBoundsException.class);
        Entity<Floor, Integer> floorAndNumber = countFloorAndFlat(number);

        floorAndNumber.floor.removeFlat(floorAndNumber.number);
    }

    private Entity<Floor, Integer> countFloorAndFlat(int number) {
        int i = 0;
        int j = 0;
        while ((i + getFloor(j++).size()) <= number) {
            i += getFloor(j - 1).size();
        }

        return new Entity<>(getFloor(j - 1), number - i);
    }

    Space[] getSquares();
}
