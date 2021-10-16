package model.utilities;

import model.buildings.Floor;
import model.buildings.Space;

public abstract class SomeBuildingUtilities {
    public static <T extends Iterable<Floor>> int flatsCount(T collection) {
        int sum = 0;

        for (Floor floor : collection)
            sum += floor.size();

        return sum;
    }

    public static <T extends Iterable<Floor>> int sumRoomsCount(T collection) {
        int sum = 0;

        for (Floor floor : collection)
            sum += floor.sumRoomCount();

        return sum;
    }

    public static <T extends Iterable<Floor>> int sumSquare(T collection) {
        int sum = 0;

        for (Floor floor : collection)
            sum += floor.sumSquare();

        return sum;
    }

    public static <T extends Iterable<Floor>> Floor[] getAllFloors(T collection, int size) {
        Floor[] floors = new Floor[size];

        int i = 0;
        for (Floor floor : collection)
            floors[i++] = floor;

        return floors;
    }

    public static <T extends Iterable<Floor>> Space[] getSquares(T collection) {
        Space[] fl = new Space[flatsCount(collection)];

        int i = 0;
        for (Floor d : collection)
            for (Space f : d.flats())
                fl[i++] = f;

        SomeBuildingUtilities.sort(fl);

        return fl;
    }

    private static void sort(Space[] fl) {
        boolean isSorted = false;
        Space buf;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < fl.length - 1; i++) {
                if (fl[i].getSpace() < fl[i + 1].getSpace()) {
                    isSorted = false;

                    buf = fl[i];
                    fl[i] = fl[i + 1];
                    fl[i + 1] = buf;
                }
            }
        }
    }
}