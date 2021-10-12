package model.buildings.flat;

import model.buildings.Building;
import model.buildings.Floor;
import model.buildings.Space;
import model.exeptions.FloorIndexOutOfBoundsException;
import model.exeptions.InvalidRoomsCountException;

import static model.utilities.IndexChecker.checkIfNumberIsValid;

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

    public int size() {
        return floors.length;
    }

    public int flatsCount() {
        int sum = 0;

        for (Floor floor : floors)
            sum += floor.size();

        return sum;
    }

    public int sumRoomsCount() {
        int sum = 0;

        for (Floor floor : floors)
            sum += floor.sumRoomCount();

        return sum;
    }

    public int sumSquare() {
        int sum = 0;

        for (Floor floor : floors)
            sum += floor.sumSquare();

        return sum;
    }

    public Floor[] getAllFloors() {
        return floors;
    }

    public Floor getFloor(int number) {
        checkIfNumberIsValid(number, size() - 1, FloorIndexOutOfBoundsException.class);
        return floors[number];
    }

    public void setFloor(int number, Floor floor) {
        checkIfNumberIsValid(number, size() - 1, FloorIndexOutOfBoundsException.class);
        floors[number] = floor;
    }

    public Space getSpace(int number) {
        checkIfNumberIsValid(number, sumRoomsCount() - 1, FloorIndexOutOfBoundsException.class);
        Entity<Floor, Integer> floorAndNumber = countFloorAndFlat(number);

        return floorAndNumber.floor.getFlat(floorAndNumber.number);
    }

    public void setSpace(int number, Space space) {
        checkIfNumberIsValid(number, sumRoomsCount() - 1, FloorIndexOutOfBoundsException.class);
        Entity<Floor, Integer> floorAndNumber = countFloorAndFlat(number);

        floorAndNumber.floor.setFlat(floorAndNumber.number, space);
    }

    public void addSpace(int number, Space space) {
        checkIfNumberIsValid(number, sumRoomsCount(), FloorIndexOutOfBoundsException.class);
        Entity<Floor, Integer> floorAndNumber = countFloorAndFlat(number);

        floorAndNumber.floor.addFlat(floorAndNumber.number, space);
    }

    public void removeSpace(int number) {
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

    public double[] getSquares() {
        double[] fl = new double[flatsCount()];

        int i = 0;
        for (Floor d : floors)
            for (Space f : d.flats())
                fl[i++] = f.getSpace();

        sort(fl);

        return fl;
    }

    private void sort(double[] fl) {
        boolean isSorted = false;
        double buf;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < fl.length - 1; i++) {
                if (fl[i] < fl[i + 1]) {
                    isSorted = false;

                    buf = fl[i];
                    fl[i] = fl[i + 1];
                    fl[i + 1] = buf;
                }
            }
        }
    }

    private class Entity<_Floor, _Number> {
        _Floor floor;
        _Number number;

        public Entity(_Floor floor, _Number number) {
            this.floor = floor;
            this.number = number;
        }
    }
}
