package model.buildings.dwelling;

import lombok.NonNull;
import model.buildings.Building;
import model.buildings.Floor;
import model.buildings.Space;
import model.exeptions.FloorIndexOutOfBoundsException;
import model.exeptions.InvalidRoomsCountException;
import model.utilities.SomeBuildingUtilities;
import model.utilities.mycollections.IterableArray;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;

import static model.utilities.IndexChecker.checkIfNumberIsValid;

public class Dwelling implements Building, Serializable {
    @NonNull
    private final Floor[] floors;

    public Dwelling(int floorsCount, @NonNull int... flatsCount) {
        if (floorsCount != flatsCount.length) throw new InvalidRoomsCountException();

        floors = new DwellingFloor[floorsCount];

        int i = 0;
        for (int f : flatsCount) {
            floors[i++] = new DwellingFloor(f);
        }
    }

    public Dwelling(Floor... floors) {
        this.floors = floors;
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
    public void setFloor(int number, @NonNull Floor floor) {
        checkIfNumberIsValid(number, size() - 1, FloorIndexOutOfBoundsException.class);
        floors[number] = floor;
    }

    @Override
    public Space[] getSquares() {
        return SomeBuildingUtilities.getSquares(new IterableArray<>(floors));
    }

    @Override
    public Iterator<Floor> iterator() {
        return new IterableArray<>(floors).iterator();
    }

    @Override
    public String toString() {
        return SomeBuildingUtilities.toString("Dwelling (", new IterableArray<>(floors), size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dwelling)) return false;
        Dwelling dwelling = (Dwelling) o;
        return floors.length == dwelling.floors.length && IntStream.range(0, floors.length - 1).allMatch(i -> floors[i].equals(dwelling.floors[i]));
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(floors);
    }

    @Override
    @SuppressWarnings("all")
    public Object clone() {
        Floor[] floors = new Floor[size()];
        for (int i = 0; i < size(); ++i)
            floors[i] = (Floor) this.floors[i].clone();
        return new Dwelling(floors);
    }
}
