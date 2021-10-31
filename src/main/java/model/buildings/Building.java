package model.buildings;

import lombok.NonNull;
import model.exeptions.FloorIndexOutOfBoundsException;
import model.utilities.Entity;

import java.io.Serializable;
import java.util.Iterator;

import static model.utilities.IndexChecker.checkIfNumberIsValid;

public interface Building extends Serializable, Iterable<Floor> {
    int size();

    int flatsCount();

    int sumRoomsCount();

    int sumSquare();

    Floor[] getAllFloors();

    Floor getFloor(int number);

    void setFloor(int number, @NonNull Floor floor);

    default Space getSpace(int number) {
        checkIfNumberIsValid(number, sumRoomsCount() - 1, FloorIndexOutOfBoundsException.class);
        Entity<Floor, Integer> floorAndNumber = countFloorAndFlat(number);

        return floorAndNumber.floor.getSpace(floorAndNumber.number);
    }

    default void setSpace(int number, @NonNull Space space) {
        checkIfNumberIsValid(number, sumRoomsCount() - 1, FloorIndexOutOfBoundsException.class);
        Entity<Floor, Integer> floorAndNumber = countFloorAndFlat(number);

        floorAndNumber.floor.setSpace(floorAndNumber.number, space);
    }

    default void addSpace(int number, @NonNull Space space) {
        checkIfNumberIsValid(number, sumRoomsCount(), FloorIndexOutOfBoundsException.class);
        Entity<Floor, Integer> floorAndNumber = countFloorAndFlat(number);

        floorAndNumber.floor.addSpace(floorAndNumber.number, space);
    }

    default void removeSpace(int number) {
        checkIfNumberIsValid(number, sumRoomsCount() - 1, FloorIndexOutOfBoundsException.class);
        Entity<Floor, Integer> floorAndNumber = countFloorAndFlat(number);

        floorAndNumber.floor.removeSpace(floorAndNumber.number);
    }

    default Space getBestSpace() {
        checkIfNumberIsValid(0, sumRoomsCount() - 1, FloorIndexOutOfBoundsException.class);
        Space bestSpace = getSpace(0);
        Space tmpBestSpace;

        for (Floor spaces : this) {
            tmpBestSpace = spaces.getBestSpace();
            if (tmpBestSpace.getSpace() > bestSpace.getSpace()) bestSpace = tmpBestSpace;
        }

        return bestSpace;
    }

    private Entity<Floor, Integer> countFloorAndFlat(int number) {
        int i = 0;

        Iterator<Floor> iterator = iterator();
        Floor floor = iterator.next();

        while ((i + floor.size()) <= number) {
            i += floor.size();
            floor = iterator.next();
        }

        return new Entity<>(floor, number - i);
    }

    Space[] getSquares();

    Object clone();
}
