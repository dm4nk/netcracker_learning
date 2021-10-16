package model.utilities;

import model.buildings.Floor;

import java.util.Iterator;


public final class IterableArray implements Iterable<Floor> {
    private final Floor[] array;

    private IterableArray(Floor[] array) {
        this.array = array;
    }

    public static IterableArray create(Floor[] array) {
        return new IterableArray(array);
    }

    @Override
    public Iterator<Floor> iterator() {
        return new Iterator<>() {
            int cur = 0;

            @Override
            public boolean hasNext() {
                return cur != array.length;
            }

            @Override
            public Floor next() {
                return array[cur++];
            }
        };
    }

}
