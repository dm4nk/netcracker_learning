package model.utilities;

import lombok.NonNull;
import model.buildings.Floor;
import model.buildings.Space;

import java.util.Iterator;

public abstract class SomeBuildingUtilities {
    public static <T extends Iterable<Floor>> int flatsCount(@NonNull T collection) {
        int sum = 0;

        for (Floor floor : collection)
            sum += floor.size();

        return sum;
    }

    public static <T extends Iterable<Floor>> int sumRoomsCount(@NonNull T collection) {
        int sum = 0;

        for (Floor floor : collection)
            sum += floor.sumRoomCount();

        return sum;
    }

    public static <T extends Iterable<Floor>> int sumSquare(@NonNull T collection) {
        int sum = 0;

        for (Floor floor : collection)
            sum += floor.sumSquare();

        return sum;
    }

    public static <T extends Iterable<Floor>> Floor[] getAllFloors(@NonNull T collection, int size) {
        Floor[] floors = new Floor[size];

        int i = 0;
        for (Floor floor : collection)
            floors[i++] = floor;

        return floors;
    }

    public static <T extends Iterable<Floor>> Space[] getSquares(@NonNull T collection) {
        Space[] fl = new Space[flatsCount(collection)];

        int i = 0;
        for (Floor d : collection)
            for (Space f : d.spaces())
                fl[i++] = f;

        SomeBuildingUtilities.sort(fl);

        return fl;
    }

    public static <T extends Iterable<?>> String toString(@NonNull String name, @NonNull T collection, int size) {
        StringBuilder spaces = new StringBuilder();
        Iterator<?> iterator = collection.iterator();
        for (int i = 0; i < size - 1; ++i)
            spaces.append(iterator.next()).append(", ");
        spaces.append(iterator.next());

        return name +
                size + ", " + spaces +
                ')';
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
