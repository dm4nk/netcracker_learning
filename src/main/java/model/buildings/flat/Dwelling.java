package model.buildings.flat;

import lombok.EqualsAndHashCode;
import model.buildings.Building;
import model.buildings.Floor;
import model.buildings.Space;
import model.exeptions.FloorIndexOutOfBoundsException;
import model.exeptions.InvalidRoomsCountException;
import model.utilities.IterableArray;
import model.utilities.SomeBuildingUtilities;

import static model.utilities.IndexChecker.checkIfNumberIsValid;

@EqualsAndHashCode
public class Dwelling implements Building {
    private final Floor[] floors;

    public Dwelling(int floorsCount, int[] flatsCount) {
        if (floorsCount != flatsCount.length) throw new InvalidRoomsCountException();

        floors = new DwellingFloor[floorsCount];

        int i = 0;
        for (int f : flatsCount) {
            floors[i++] = new DwellingFloor(f);
        }
    }

    public Dwelling(DwellingFloor[] dwellingFloors) {
        floors = dwellingFloors;
    }

    @Override
    public int size() {
        return floors.length;
    }

    @Override
    public int flatsCount() {
        return SomeBuildingUtilities.flatsCount(new IterableArray<>(floors));
    }

    @Override
    public int sumRoomsCount() {
        return SomeBuildingUtilities.sumRoomsCount(new IterableArray<>(floors));
    }

    @Override

    public int sumSquare() {
        return SomeBuildingUtilities.sumSquare(new IterableArray<>(floors));
    }

    @Override
    public Floor[] getAllFloors() {
        return SomeBuildingUtilities.getAllFloors(new IterableArray<>(floors), size());
    }

    @Override
    public Floor getFloor(int number) {
        checkIfNumberIsValid(number, size() - 1, FloorIndexOutOfBoundsException.class);
        return floors[number];
    }

    @Override
    public void setFloor(int number, Floor floor) {
        checkIfNumberIsValid(number, size() - 1, FloorIndexOutOfBoundsException.class);
        floors[number] = floor;
    }

    @Override
    public Space[] getSquares() {
        return SomeBuildingUtilities.getSquares(new IterableArray<>(floors));
    }

    @Override
    public String toString() {

        StringBuilder floors = new StringBuilder();

        for (Floor f : this.floors) {
            floors.append(f.size()).append("\n");
            for (Space s : f.spaces())
                floors.append(s.getRooms()).append("\n").append(s.getSpace()).append("\n");
        }

        return size() + "\n" + floors.toString();
    }
}
